package com.practise.dfc.module.sbo.interface1;

import com.documentum.fc.client.IDfService;

/**
 * Created by Administrator on 16-06-2016.
 */
public interface ITitleSetter extends IDfService{
    public boolean setTitltle(String objectId, String docbase);
    public boolean setTitltle(String objectId);
}
