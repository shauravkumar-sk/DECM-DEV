package com.practise.dfc.newobject;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import com.practise.dfc.query.QueryResult;
import com.practise.utility.Utility;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 06-09-2016.
 */
public class ObjectWithFile {
public static Logger logger= DfLogger.getLogger(ObjectWithFile.class);

    public static void main(String[] args) throws DfException, IOException {
        Connection connection = new Connection();
        try {
            IDfSession iDfSession = connection.obtainASession("Repository_1");
            File file = new File("file_on_fly.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Hi! This is testing on the fly");
            fileWriter.close();
            IDfDocument iDfDocument = (IDfDocument) iDfSession.newObject("dm_document");
            iDfDocument.setObjectName("content_query_result_doc_4");
            iDfDocument.setContentType("crtext");
            iDfDocument.setContent(Utility.readFile(file));
            iDfDocument.link("/Practise");
            iDfDocument.save();
            logger.info("New Document with content :" + iDfDocument.getObjectId());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.releaseSession();
        }
    }
}
