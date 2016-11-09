package com.practise.dfc.newobject;

import com.documentum.fc.client.*;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import com.practise.dfc.query.QueryResult;
import com.practise.utility.Utility;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 05-09-2016.
 */
public class ObjectWithContent {
    private static Logger logger= DfLogger.getLogger(ObjectWithContent.class);

    /**
     * @param args
     */
    public static void main(String[] args) throws DfException, IOException {
        // TODO Auto-generated method stub
        Connection conn = new Connection();
        IDfSession session = conn.obtainASession("Repository_1");
        try {
            IDfDocument dfDocument=(IDfDocument) session.newObject("dm_document");
			dfDocument.setObjectName("content_query_result_doc_4");
			dfDocument.setContentType("crtext");
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            baos.write(Utility.convertToStringBuffer(QueryResult.getQueryResult(session),"|").toString().getBytes());
//            dfDocument.setContent(Utility.readFile("E:\\test.txt"));
            dfDocument.setContent(baos);
			dfDocument.link("/Practise");
			dfDocument.save();
            logger.info("New Document with content :"+dfDocument.getObjectId());
        } catch (DfException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            conn.releaseSession();
        }
    }

}
