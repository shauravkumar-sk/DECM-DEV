package com.practise.dfc.connection;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.documentum.fc.common.IDfLoginInfo;
import org.apache.log4j.Logger;

/**
 * Created by shaurav on 31-10-2015.
 */
public class Connection {
    IDfSessionManager sessMgr = null;
    IDfSession sess = null;
    IDfClientX clientX = null;
    IDfLoginInfo li = null;
    IDfClient localClient = null;
    String repoName = "";
    String username = "";
    String password = "";
    private static Logger logger=DfLogger.getLogger(Connection.class);

    public Connection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Connection() {
        this.username = "Administrator";
        this.password = "a";
    }

    public IDfSession obtainASession(String repoName) throws DfException {
        clientX = new DfClientX();
        li = clientX.getLoginInfo();
        li.setUser(username);
        li.setPassword(password);
        localClient = DfClient.getLocalClient();
        sessMgr = localClient.newSessionManager();
        sessMgr.setIdentity(repoName, li);
        sess = sessMgr.getSession(repoName);
        logger.info("Session id: " + sess.getSessionId());
        return sess;
    }
    public IDfSession obtainASession() throws DfException {
        return obtainASession("Repository_1") ;
    }

    public void releaseSession() {
        if (sess != null)
            sessMgr.release(sess);
        logger.info("Session released");
    }

}


