package com.practise.dfc.newobject;

import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.practise.dfc.connection.Connection;

/**
 * Created by shaurav on 31-10-2015.
 */
public class DocumentFolder {
    public static void main(String[] args) throws DfException {
        // TODO Auto-generated method stub
        Connection conn = new Connection();
        IDfSession session = conn.obtainASession("Repository_1");
        try {
            IDfFolder dfDocument = (IDfFolder) session.newObject("dm_folder");
            dfDocument.setObjectName("test_doc_folder");
            dfDocument.setContentType("crtext");
            dfDocument.setFile("E:\\test.txt");
            dfDocument.link("/Practise");
            dfDocument.save();
            System.out.println(session.getDocbaseId() + session.getDocbaseName());
        } catch (DfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
