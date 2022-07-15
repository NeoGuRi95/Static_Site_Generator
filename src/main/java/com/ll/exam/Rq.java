package com.ll.exam;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String url;
    private String Path;
    private String queryStr;
    private Map<String, Object> map;

    public Rq(String cmd) {
        this.url = cmd;
        String[] urlBits = cmd.split("\\?", 2);
        this.Path = urlBits[0];

        // 뒤에 쿼리문이 있다면
        if (urlBits.length == 2) {
            this.queryStr = urlBits[1];
            map = new HashMap<>();
            String[] parameters = queryStr.split("&");

            for (String param : parameters) {
                if (param.split("=").length == 1) {
                    continue;
                } else {
                    String paramName = param.split("=")[0].trim();
                    int paramValue = Integer.parseInt(param.split("=")[1].trim());
                    map.put(paramName, paramValue);
                }
            }
        }
    }

    public int getValue(String paramName, int defaultValue) {
        if (map.containsKey(paramName)) return (Integer)map.get(paramName);
        else return defaultValue;
    }

    public String getPath() {
        return this.Path;
    }
}
