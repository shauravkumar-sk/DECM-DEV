package com.practise.dfc.newobject;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.practise.dfc.connection.Connection;

/**
 * Created by Administrator on 17-06-2016.
 */
public class ProjectDocument {

    public static void main(String[] args) throws DfException {
        Connection conn = new Connection();
        IDfSession session = conn.obtainASession("Repository_1");
        try {
            IDfDocument dfDocument = (IDfDocument) session.newObject("project_doc");
            dfDocument.setObjectName("test_document2");
            dfDocument.setContentType("crtext");
            dfDocument.setFile("E:\\test.txt");
            dfDocument.link("/Practise");
            dfDocument.setString("project_name","Test");
            dfDocument.setString("project_code","1");
            dfDocument.setString("manager","testuser");
            dfDocument.setString("doc_type","Design");
            dfDocument.setString("doc_status","Draft");
            dfDocument.save();
            System.out.println(dfDocument.getObjectId());
            //System.out.println(session.getDocbaseId() + session.getDocbaseName());
        } catch (DfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            conn.releaseSession();
        }
    }
    }
