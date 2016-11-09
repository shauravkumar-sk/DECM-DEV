package com.practise.dfc.newobject;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.practise.dfc.connection.Connection;

/**
 * Created by shaurav on 31-10-2015.
 */
public class DocumentSysobject {
    public static void main(String[] args) throws DfException {
        // TODO Auto-generated method stub
        Connection conn = new Connection();
        IDfSession session = conn.obtainASession("Repository_1");
        try {
            IDfSysObject dfDocument = (IDfSysObject) session.newObject("dm_sysobject");
            dfDocument.setObjectName("test_doc_sysobject");
            dfDocument.setContentType("crtext");
            dfDocument.setFile("E:\\test.txt");
            dfDocument.link("/Practise");
            dfDocument.save();
            System.out.println(dfDocument.getObjectId());
        } catch (DfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
