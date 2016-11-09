package com.practise.dfc.objectmapper;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.practise.dfc.connection.Connection;
import com.practise.dfc.objectmapper.model.User;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 10/29/2016.
 */
public class JSONTesting {
    public static void main(String[] args) throws DfException {

        Connection connection=new Connection();
        IDfSession iDfSession=connection.obtainASession();
        try {
            IDfDocument document = (IDfDocument) iDfSession.getObjectByPath("/Practise/config/urls.json");
                    //getObject(new DfId("090000018001ea38"));
            ByteArrayInputStream inputStream = document.getContent();
            ObjectMapper mapper = new ObjectMapper();
            String targetFileStr = IOUtils.toString(inputStream, "UTF-8");
            User user1=mapper.readValue(targetFileStr,User.class);
            System.out.println(user1.getAge());
            System.out.println(user1.getName());
            System.out.println(user1.getAddress().get("line1"));
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.releaseSession();
        }
    }}
