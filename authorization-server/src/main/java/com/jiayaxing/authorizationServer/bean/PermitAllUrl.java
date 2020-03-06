package com.jiayaxing.authorizationServer.bean;

import java.util.ArrayList;
import java.util.List;

public final class PermitAllUrl {
    private  static  final String[] ENDPOINTS={"/clientAndSecretController/get1","/orderController/get2"};
    public static String[] permitAllUrl(String... urls){

        if(urls==null||urls.length==0){
            return ENDPOINTS;
        }
        List<String> list = new ArrayList<>();
        for (String url: ENDPOINTS) {
            list.add(url);
        }
        for (String url: urls) {
            list.add(url);
        }
        return  list.toArray(new String[list.size()]);
    }
}
