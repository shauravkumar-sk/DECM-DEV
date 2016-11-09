package com.practise.dfc.object;

import com.documentum.fc.client.IDfBusinessObject;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfEnumeration;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.search.IDfSearchSource;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 07-09-2016.
 */
public class RetrieveObjects {
    public static Logger logger = DfLogger.getLogger(RetrieveObjects.class);

    public static void main(String[] args) throws DfException {
        Connection connection = new Connection();
        try {
            IDfSession session = connection.obtainASession();
            IDfEnumeration enumeration = session.getObjectsByQuery("select r_object_id,i_vstamp,i_is_replica,i_is_reference,r_aspect_name,r_object_type from project_doc", null);
            while (enumeration.hasMoreElements()) {
                IDfDocument object = (IDfDocument) enumeration.nextElement();
                logger.info(object.getObjectId()+"|"+object.getObjectName());
//                logger.info(object.getContent().);
//                logger.info("done+123");
            }
            logger.info("done");
        } finally {
            connection.releaseSession();
        }
    }
}
