package com.practise.dfc.module.lifecycle;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.practise.dfc.connection.Connection;

/**
 * Created by Administrator on 17-06-2016.
 */
public class LifecyclePromote {
    public static void main(String[] args) throws DfException{
        Connection connection=new Connection();
        IDfSession session=connection.obtainASession("Repository_1");
        IDfDocument document=(IDfDocument) session.getObject(new DfId("09000001800049e6"));
        document.promote(null,true,false);
        connection.releaseSession();
    }
}
