package com.practise.dfc.connection;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import org.apache.log4j.Logger;

public class SessionTest {
    private static Logger logger=DfLogger.getLogger(SessionTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) throws DfException {
        // TODO Auto-generated method stub
        Connection conn = new Connection();
        IDfSession session = conn.obtainASession("Repository_1");
        try {
            logger.info("Testing");
            logger.info(session.getDocbaseId() + session.getDocbaseName());
        } catch (DfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            conn.releaseSession();
        }
    }

}
