package com.practise.dfc.module.sbo.implementation;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.DfService;
import com.documentum.fc.client.DfServiceException;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.practise.dfc.module.sbo.interface1.ITitleSetter;

/**
 * Created by Administrator on 16-06-2016.
 */
public class TitleSetter extends DfService implements ITitleSetter {
    public boolean setTitltle(String objectId, String docbase) {
        IDfSession session = null;
        try {
            System.out.println("Inside Title Setter SBO");
            session = getSession(docbase);

            IDfDocument document = (IDfDocument) session.getObject(new DfId(objectId));
            document.setTitle("From SBO");
            document.save();
            return true;
        } catch (DfException e) {
            e.printStackTrace();
        } finally {
            try {
                releaseSession(session);
            } catch (DfServiceException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean setTitltle(String objectId){
        String docbase= null;
        try {
            docbase = new DfClientX().getLocalClient().getDocbaseNameFromId(new DfId(objectId));
            System.out.println("Docbase name extracted is :" +docbase);
        } catch (DfException e) {
            e.printStackTrace();
        }
        return setTitltle(objectId,docbase);
    }
}
