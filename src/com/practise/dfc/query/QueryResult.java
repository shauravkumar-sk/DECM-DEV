package com.practise.dfc.query;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import com.practise.utility.Utility;
import org.apache.log4j.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 05-09-2016.
 */
public class QueryResult {

    private static Logger logger = DfLogger.getLogger(QueryResult.class);

    /**
     * @param args
     */
    public static void main(String[] args) throws DfException, IOException {
        Connection conn = new Connection();
        IDfSession session = conn.obtainASession("Repository_1");
        try {
            Map<String, List<String>> result = getQueryResult(session);
//                Utility.generateCSVReport("E:\\report.csv","|",result);
            StringBuffer data = Utility.convertToStringBuffer(result, "|");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(data.toString().getBytes());
            logger.info(baos.toString());
        } catch (DfException e) {
            e.printStackTrace();
        } finally {
            conn.releaseSession();
        }
    }

    public static Map<String, List<String>> getQueryResult(IDfSession session) throws DfException {
        IDfQuery query = new DfQuery();
        query.setDQL("select r_object_id,r_object_type,object_name,title from project_doc");
        IDfCollection col = query.execute(session, IDfQuery.DF_READ_QUERY);
        try {
            return convertCollectionToMap(col);
        } finally {
            if (col != null) col.close();
        }
    }

    public static Map<String, List<String>> convertCollectionToMap(IDfCollection col) throws DfException {
        boolean column = true;
        String attrName = null;
        int attrCount = col.getAttrCount();
        Map<String, List<String>> resultMap = new LinkedHashMap<>(attrCount);
        List<String> attributes = new ArrayList<>();
        while (col.next()) {
            for (int i = 0; i < attrCount; i++) {
                if (column) {
                    attrName = col.getAttr(i).getName();
                    attributes.add(attrName);
                    resultMap.put(attrName, new ArrayList<String>());
                }
                resultMap.get(attributes.get(i)).add(col.getString(attributes.get(i)));
            }
            column = false;
        }
        return resultMap;
    }
}
