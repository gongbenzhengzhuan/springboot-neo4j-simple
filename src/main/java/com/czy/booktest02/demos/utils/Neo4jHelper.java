package com.czy.booktest02.demos.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Neo4jHelper {


    private static String httpUri;

    @Value("http://123.60.190.167:7474/")
    public void sethttpuri(String httpuri) {
        httpUri = httpuri;
    }


    private static String userName;
    @Value("admin")
    public void setuserName(String username) {
        userName = username;
    }


    private static String pwd;
    @Value("admin")
    public void setpwd(String password) {
        pwd = password;
    }


    public static JSONObject execCql(String statement, String[] contents) {
        JSONArray resultDataContents = new JSONArray();
        for (String content : contents) {
            resultDataContents.add(content);
        }
        JSONObject statementJson = new JSONObject();
        statementJson.put("statement", statement);
        statementJson.put("resultDataContents", resultDataContents);
        JSONObject data = new JSONObject();
        JSONArray statements = new JSONArray();
        statements.add(statementJson);
        data.put("statements", statements);

        JSONObject res = HttpHelper.httpPost(String.format("%sdb/data/transaction/commit", httpUri), data, userName, pwd, false);

        return res;
    }



    public static JSONObject query(String host, String userName, String pwd, String statement, String[] contents) {
        JSONArray resultDataContents = new JSONArray();
        for (String content : contents) {
            resultDataContents.add(content);
        }
        JSONObject statementJson = new JSONObject();
        statementJson.put("statement", statement);
        statementJson.put("resultDataContents", resultDataContents);
        JSONObject data = new JSONObject();
        JSONArray statements = new JSONArray();
        statements.add(statementJson);
        data.put("statements", statements);
        JSONObject res = HttpHelper.httpPost(String.format("%sdb/data/transaction/commit", host), data, userName, pwd, false);
        return res;
    }

}

