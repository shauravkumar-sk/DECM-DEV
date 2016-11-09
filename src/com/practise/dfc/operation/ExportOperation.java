package com.practise.dfc.operation;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.operations.IDfExportNode;
import com.documentum.operations.IDfExportOperation;
import com.practise.dfc.connection.Connection;
import com.practise.dfc.newobject.DocumentObject;

/**
 * Created by Administrator on 15-06-2016.
 */
public class ExportOperation {
    public static void main(String[] args) {
        Connection connection = new Connection();
        DocumentObject documentObject = new DocumentObject();
        try {
            IDfSession session = connection.obtainASession("Repository_1");
            IDfExportOperation exportOperation = new DfClientX().getExportOperation();
            IDfDocument document1 = documentObject.getDocumentObject(session, "090000018000025f");
            IDfDocument document2 = documentObject.getDocumentObject(session, "0900000180000260");
            IDfExportNode exportNode1 = (IDfExportNode) exportOperation.add(document1);
            IDfExportNode exportNode2 = (IDfExportNode) exportOperation.add(document2);
            //exportOperation.setDestinationDirectory("C:\\Users\\Administrator\\Documents");
            exportNode1.setFilePath("C:\\Users\\Administrator\\Documents\\export1\\" + document1.getObjectName());
            exportNode2.setFilePath("C:\\Users\\Administrator\\Documents\\export2\\" + document2.getObjectName());
            exportOperation.execute();
            System.out.println("succes");
        } catch (DfException df) {
            df.printStackTrace();
        } finally {
            connection.releaseSession();
        }
    }
}
