package com.practise.dfc.method.workflow;

import com.documentum.bpm.rtutil.WorkflowMethod;
import com.documentum.fc.client.*;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfProperties;

import java.io.PrintWriter;

/**
 * Created by shaurav on 08-12-2015.
 */
public class MyWorkflowMethod extends WorkflowMethod {
    private static final String PARAM_ACT = "param_activity_name";

    protected int doTask(IDfWorkitem workitem, IDfProperties properties, PrintWriter printWriter)
            throws Exception {
        String paramActivityName = properties.getString(PARAM_ACT);

        IDfSession session = workitem.getSession();
        //IDfWorkitemEx workitemEx = (IDf) workitem;
        String workflowId = workitem.getWorkflowId().getId();

        if (session.isConnected()) {
            try {
                IDfQuery q = new DfQuery();
                IDfCollection col = null;
                String actDefId = "0000000000000000";
                q.setDQL("select r_act_def_id from dm_workflow where r_object_id='" + workflowId + "' and r_act_name='" + paramActivityName + "' ENABLE(ROW_BASED)");
                col = q.execute(session, IDfQuery.DF_READ_QUERY);
                if (col.next()) {
                    actDefId = col.getString("r_act_def_id");
                }
                col.close();

                q.setDQL("select r_object_id from dmi_workitem where r_workflow_id='" + workflowId + "' and r_act_def_id='" + actDefId + "'");

                col = q.execute(session, IDfQuery.DF_READ_QUERY);
                while (col.next()) {

                    IDfId wiId = col.getId("r_object_id");
                    IDfWorkitem oWorkitem = (IDfWorkitem) session.getObject(wiId);

                    if (oWorkitem.getRuntimeState() == IDfWorkitem.DF_WI_STATE_DORMANT) {
                        oWorkitem.acquire();
                        oWorkitem.complete();
                    } else if (oWorkitem.getRuntimeState() == IDfWorkitem.DF_WI_STATE_ACQUIRED) {
                        oWorkitem.complete();
                    }

                }
                col.close();

            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }

        return 0;
    }

}
