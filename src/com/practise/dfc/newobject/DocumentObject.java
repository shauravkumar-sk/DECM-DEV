package com.practise.dfc.newobject;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.practise.dfc.connection.Connection;

/**
 * Created by shaurav on 31-10-2015.
 */
public class DocumentObject {
    public static void main(String[] args) throws DfException {
        // TODO Auto-generated method stub
        Connection conn = new Connection();
    IDfSession session = conn.obtainASession("Repository_1");
    try {
        IDfDocument dfDocument = (IDfDocument) session.newObject("dm_document");
        dfDocument.setObjectName("urls.json");
        dfDocument.setContentType("crtext");
        dfDocument.setFile("E:\\urls.json");
        dfDocument.link("/Practise/config");
        dfDocument.save();
        System.out.println("Daaga "+dfDocument.getObjectId());
    } catch (DfException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }finally {
        conn.releaseSession();
    }
}

    public IDfDocument getDocumentObject(IDfSession session, String objectId) throws DfException {
        IDfDocument document = (IDfDocument) session.getObject(new DfId(objectId));
        return document;
    }
}
