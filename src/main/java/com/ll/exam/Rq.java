package com.ll.exam;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String url;
    String path;
    Map<String, String> queryParams;

    public Rq(String url) {
        this.url = url;
        String[] urlBits = url.split("\\?", 2);
        this.path = urlBits[0]; // 등록 / 목록 / 삭제 / 수정

        queryParams = new HashMap<>(); // Parameter : Value 형태

        if (urlBits.length == 2) { // ? 뒤에 Parameter가 있을 경우에만
            String queryStr = urlBits[1]; // ? 뒤 쿼리문

            String[] paramBits = queryStr.split("&"); // 쿼리문을 & 기준으로 split

            for (String paramBit : paramBits) { // parameter 덩어리에 대해서 for
                String[] paramNameAndValue = paramBit.split("=", 2); // parameter와 value를 = 기준으로 나눔

                if (paramNameAndValue.length == 1) { // = 기준으로 나눴는데 1개 덩어리면 value가 없다는 뜻 -> pass
                    continue;
                }

                String paramName = paramNameAndValue[0].trim();
                String paramValue = paramNameAndValue[1].trim();

                queryParams.put(paramName, paramValue);
            }
        }
    }

    public int getIntParam(String paramName, int defaultValue) {
        if (queryParams.containsKey(paramName) == false) {
            return defaultValue;
        }

        String paramValue = queryParams.get(paramName);

        if (paramValue.length() == 0) {
            return defaultValue;
        }

        return Integer.parseInt(paramValue);
    }

    public String getPath() {
        return path;
    }
}