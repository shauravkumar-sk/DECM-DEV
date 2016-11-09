package com.practise.dfc.newobject;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;

/**
 * Created by shaurav on 04-12-2015.
 */
public class SessionTest {
    public static void main(String[] args) {
        try {
            IDfClient c = new DfClientX().getLocalClient();
            // create login info
            IDfLoginInfo li = new DfClientX().getLoginInfo();
            li.setUser("shaurav");
            li.setPassword("a");

            // create session manager and set identity
            IDfSessionManager sessMgr = c.newSessionManager();
            sessMgr.setIdentity("Repository_1", li);

            // get session and use it
            IDfSession sess = sessMgr.getSession("Repository_1");
            System.out.println("Session id: " + sess.getSessionId());
            //System.out.println("Repository Name: " + sess.getDocbaseName());
            //System.out.println("Database Name: " + sess.getDBMSName());
            sessMgr.release(sess);
            System.out.println("Session released");
            System.out.println(sessMgr.getSession("Repository_1").getSessionId());
            System.out.println(sessMgr.newSession("Repository_1").getSessionId());
        } catch (DfException e) {
            e.printStackTrace();
        }
    }
}
