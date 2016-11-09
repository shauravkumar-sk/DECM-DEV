package com.practise.dfc.module.tbo;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.*;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;
import com.practise.dfc.module.sbo.interface1.ITitleSetter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AttachLifecycle extends DfDocument implements IDfBusinessObject {

    public static final String strCOPYRIGHT = "Copyright (c) Documentum, Inc., 2002";
    public static final String strVERSION = "1.0";
    IDfClient client = null;

    public static IDfTime getDocbaseTime(IDfSession session)  throws DfException {
        IDfQuery query = new DfQuery();
        query.setDQL("select dateadd(day,1,date(now))as systime from dm_server_config");
        IDfCollection col = query.execute(session, IDfQuery.DF_READ_QUERY);
        SimpleDateFormat sqlDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        IDfTime serverTime = null;
        String timeStamp = null;
        try {
            if (col.next()) {
                timeStamp = col.getTime("systime").asString(String.valueOf(sqlDateFormat));
                serverTime = col.getTime("systime");
                // System.out.println(timeStamp);
                long milliseconds = sqlDateFormat.parse(timeStamp).getTime() + 1000 * 10 * 60;
                // serverTime= new DfTime(new Date(milliseconds));
                // System.out.println(serverTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (col != null) col.close();
        }
        return serverTime;
    }

    public String getVersion() {
        return strVERSION;
    }

    public String getVendorString() {
        return strCOPYRIGHT;
    }

    public boolean isCompatible(String s) {
        return s.equals("1.0");
    }

    public boolean supportsFeature(String s) {
        String strFeatures = "dm_document, subclassing";
        if (strFeatures.indexOf(s) == -1)
            return false;
        return true;
    }

    protected void doSave(boolean saveLock, String versionLabel, Object[] extendedArgs) throws DfException {
        boolean bret = isNew();
        String sId = "", sVersion = "";
        String docbase;
        String objectId;

        // First call - allow save
        super.doSave(saveLock, versionLabel, extendedArgs);
        if (bret) {
            System.out.println("Inside TBO ");
            // optional pre-processing
            try {
                // Get a session; it will be released automatically.
                IDfSession sess = getSession();
                docbase = sess.getDocbaseName();
                objectId = getObjectId().toString();
                IDfPolicy policyObject = (IDfPolicy) sess.getObjectByQualification("dm_policy where object_name = 'project_doc'");
                if (policyObject != null) {
                    attachPolicy(policyObject.getObjectId(), "DRAFT", null);
                    schedulePromote("REVIEW", getDocbaseTime(sess), true);
                } else {//log for lifecycle not found/custom handling
                    System.out.println("Lifecycle not found");
                }

            } catch (DfException dfe) {
                throw new DfException(dfe);
            }
            super.doSave(saveLock, versionLabel, extendedArgs);
            // optional post-processing
            System.out.println("Calling SBO");
            ITitleSetter titleSetter = (ITitleSetter) new DfClientX().getLocalClient().newService(ITitleSetter.class.getName(), getSessionManager());
            titleSetter.setTitltle(objectId);
            System.out.println("SBO Released");
        }
    }

}

