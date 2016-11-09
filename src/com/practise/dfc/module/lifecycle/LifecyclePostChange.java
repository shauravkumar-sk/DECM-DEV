package com.practise.dfc.module.lifecycle;

import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfModule;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.documentum.fc.lifecycle.IDfLifecycleUserPostProcessing;

/**
 * Created by shaurav on 08-11-2015.
 */
public class LifecyclePostChange implements IDfModule, IDfLifecycleUserPostProcessing {
    public void userPostProcessing(IDfSysObject iDfSysObject, String s, String s1) throws DfException {
        DfLogger.info(this, "Inside userPostProcessing", null, null);
        IDfSession session = iDfSysObject.getSession();
        String state = iDfSysObject.getCurrentStateName();
        IDfFolder sourcefolder = (IDfFolder) session.getObject(iDfSysObject.getFolderId(0));
        iDfSysObject.unlink(sourcefolder.getFolderPath(0));
        String targetFolderpath = "/Projects/" + state;
        DfLogger.info(this, "Target folder for state " + state + " is " + targetFolderpath, null, null);
        iDfSysObject.link(targetFolderpath);
        iDfSysObject.save();
    }
}
