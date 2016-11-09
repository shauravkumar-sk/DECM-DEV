package com.practise.dfc.method;

import com.documentum.fc.client.*;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.methodserver.IDfMethod;
import com.documentum.mthdservlet.IDmMethod;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by shaurav on 04-11-2015
 */
public class PrintDocbaseDetailMethod extends DfSingleDocbaseModule implements IDfMethod {
    // Default parameters passed by invocation of job
    private static final String USER_KEY = "user_name";
    private static final String DOCBASE_KEY = "docbase_name";
    private static final String PASSWORD_KEY = "password";
    private static final String DOMAIN_KEY = "domain";
    private static final String JOBID = "job_id";
    private static final String MTL = "method_trace_level";
    // Additional job parameters in dm_job object
    private static final String JOB_PARAM_1 = "-param1";
    private static final String JOB_PARAM_2 = "-param2";
    // Declaration of various variables needed in the method
    protected IDfSessionManager m_sessionMgr = null;
    protected String m_docbase = null;
    //protected RandomAccessFile m_File = null;
    protected String m_userName = null;
    protected String m_password = null;
    protected String m_domain = null;
    protected String m_jobid = null;
    protected String m_mtl = "0";
    protected String m_strJobName = null;
    protected String m_strServerLog = "";
    protected String m_strFilename = "";

    public int execute(Map map, PrintWriter outputStream) throws Exception {
        initParams(map);
        print("key is = " + USER_KEY, "info");
        System.out.println("key is12 = " + USER_KEY);
        System.out.println("key is12 = " + DOCBASE_KEY);
        System.out.println("key is 12= " + JOBID);
        System.out.println("end");

        print("key is = " + DOCBASE_KEY, "info");
        print("key is = " + JOBID, "info");
        IDfSessionManager sessionManager = login();
        IDfSession session = sessionManager.getSession(m_docbase);
        System.out.println("Docbase name=" + session.getDocbaseName() + " Docbase ID=" + session.getDocbaseId() + "DBMS name= " + session.getDBMSName());
        //outputStream.write('T');
        return 0;
    }

    protected void initParams(Map params) throws Exception {
        Set keys = params.keySet();
        Iterator iter = keys.iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            print("key is = " + key, "info");

            if ((key == null) || (key.length() == 0)) {
                continue;
            }
            String[] value = (String[]) params.get(key);
            print("value is = " + value, "info");

            if (key.equalsIgnoreCase(USER_KEY))
                m_userName = (value.length > 0) ? value[0] : "";
            else if (key.equalsIgnoreCase(DOCBASE_KEY))
                m_docbase = (value.length > 0) ? value[0] : "";
            else if (key.equalsIgnoreCase(PASSWORD_KEY))
                m_password = (value.length > 0) ? value[0] : "";
            else if (key.equalsIgnoreCase(DOMAIN_KEY))
                m_domain = (value.length > 0) ? value[0] : "";
            else if (key.equalsIgnoreCase(JOBID))
                m_jobid = (value.length > 0) ? value[0] : "";
            else if (key.equalsIgnoreCase(MTL))
                m_mtl = (value.length > 0) ? value[0] : "";
        }
    }

    protected IDfSessionManager login() throws DfException {
        if (m_docbase == null || m_userName == null)
            return null;

        // now login
        IDfClient dfClient = DfClient.getLocalClient();

        if (dfClient != null) {
            IDfLoginInfo li = new DfLoginInfo();
            li.setUser(m_userName);
            li.setPassword(m_password);
            li.setDomain(m_domain);

            IDfSessionManager sessionMgr = dfClient.newSessionManager();
            sessionMgr.setIdentity(m_docbase, li);
            return sessionMgr;
        }
        return null;
    }

    void print(String message, String type) {
        if (type.equalsIgnoreCase("info")) {
            DfLogger.info(this, message, null, null);
        } else if (type.equalsIgnoreCase("debug")) {
            DfLogger.debug(this, message, null, null);
        } else
            DfLogger.error(this, message, null, null);
    }
}
