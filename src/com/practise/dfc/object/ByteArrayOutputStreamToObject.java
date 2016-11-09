package com.practise.dfc.object;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 09-09-2016.
 */
public class ByteArrayOutputStreamToObject {
    public static Logger logger= DfLogger.getLogger(ByteArrayOutputStreamToObject.class);
    public static void main(String[] args) throws DfException {
        Connection connection=new Connection();
        IDfSession iDfSession=connection.obtainASession();
        try {
            IDfDocument document = (IDfDocument) iDfSession.getObject(new DfId("0900000180005ae9"));
            ByteArrayInputStream inputStream = document.getContent();
            logger.info(inputStream);
            int a;
            StringBuffer temp=new StringBuffer();
            while((a=inputStream.read())!=-1){
                temp.append((char)a);
            }
            logger.info(temp);
        }finally {
            connection.releaseSession();
        }
    }
}
