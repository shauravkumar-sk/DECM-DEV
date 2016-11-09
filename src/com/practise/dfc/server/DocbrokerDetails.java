package com.practise.dfc.server;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfDocbrokerClient;
import com.documentum.fc.client.impl.docbroker.DocbrokerClient;
import com.documentum.fc.common.DfException;

/**
 * Created by Administrator on 23-07-2016.
 */
public class DocbrokerDetails {
    public static void main(String[] args) throws DfException {
        IDfDocbrokerClient docbrokerClient=new DocbrokerClient();
        //System.out.println(docbrokerClient.getServerMap("Repository_1").dump());
        System.out.println(docbrokerClient.getDocbaseMap().dump());
    }
}
