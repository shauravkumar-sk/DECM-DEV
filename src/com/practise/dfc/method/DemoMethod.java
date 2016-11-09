package com.practise.dfc.method;

import com.documentum.fc.client.DfSingleDocbaseModule;
import com.documentum.fc.methodserver.IDfMethod;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by shaurav on 02-11-2015.
 */
public class DemoMethod extends DfSingleDocbaseModule implements IDfMethod {


    @Override
    public int execute(Map map, PrintWriter printWriter) throws Exception {
        System.out.println("Hello Inside Demo Method");
        return 0;
    }
}
