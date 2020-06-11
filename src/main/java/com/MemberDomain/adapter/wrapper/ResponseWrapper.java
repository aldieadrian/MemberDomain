package com.MemberDomain.adapter.wrapper;

import org.json.simple.JSONObject;

public class ResponseWrapper {
    public static JSONObject wrap(String message, Integer status, Object data){
        JSONObject json = new JSONObject();
        json.put("status", Integer.valueOf(status));
        json.put("message", message);
        json.put("data", data);

        return json;
    }
}