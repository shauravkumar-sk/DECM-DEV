package com.practise.test;

import com.documentum.fc.common.DfLogger;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 06-09-2016.
 */
public class ByteArrayOutput {
    public static Logger logger= DfLogger.getLogger(ByteArrayOutput.class);
    public static void main(String[] args) throws IOException {
        String str="Hi ! This is testing";
        ByteArrayOutputStream baous=new ByteArrayOutputStream();
        baous.write(str.getBytes());
        logger.info(baous.toString());
    }
}
