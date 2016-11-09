package com.practise.dfc.module.tbo;

import com.documentum.fc.client.*;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;

public class MoveDocument extends DfDocument implements IDfBusinessObject {

    public static final String strCOPYRIGHT = "Copyright (c) Documentum, Inc., 2002";
    public static final String strVERSION = "1.0";
    IDfClient client = null;

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

    protected void doUnlink(String folderSpec, Object[] extendedArgs) throws DfException {
        IDfSession sess = getSession();
        IDfId foldername = new DfId(folderSpec);
        IDfSysObject obj = (IDfSysObject) sess.getObject(foldername);
        System.out.println("inside doUnlink()");
        System.out.println("source foldername  found : " + obj.getObjectName());
        setString("moved_from", obj.getObjectName());
        super.doUnlink(folderSpec, extendedArgs);
    }

    protected void doLink(String folderSpec, Object[] extendedArgs) throws DfException {
        IDfSession sess = getSession();
        IDfId foldername = new DfId(folderSpec);
        IDfSysObject obj = (IDfSysObject) sess.getObject(foldername);
        System.out.println("inside dolink()");
        System.out.println("destination foldername  found : " + obj.getObjectName());
        setString("moved_to", obj.getObjectName());
        super.doLink(folderSpec, extendedArgs);
    }
}

