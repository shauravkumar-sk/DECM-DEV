package com.practise.dfc.operation;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfLogger;
import com.documentum.operations.*;
import com.documentum.operations.impl.DfFile;
import com.documentum.operations.nodes.inbound.impl.DfCheckinNode;
import com.practise.dfc.connection.Connection;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by Administrator on 12-09-2016.
 */
public class CheckInOperation {
    public static Logger logger= DfLogger.getLogger(CheckInOperation.class);

    public static void main(String[] args) throws DfException {
        Connection connection=new Connection();
        IDfSession session=connection.obtainASession();
        IDfCheckinOperation checkInOperation= (IDfCheckinOperation) new DfClientX().getCheckinOperation();
        IDfDocument document= (IDfDocument) session.getObject(new DfId(""));
        DfCheckinNode checkinNode= (DfCheckinNode) checkInOperation.add(document);
        checkinNode.setFilePath("");
//        File file=new File();
//        IDfFile dfFile=new DfFile(file);
//        checkinNode.setFile(dfFile);
    }
}
