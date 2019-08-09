package com.zjh.dataservice.component.common;

import java.util.HashMap;
import java.util.Map;

public class CommonSteps {

    private Map map = new HashMap();

    protected void setAttribute(String key,Object value){
        map.put(key,value);
    }

    protected Object getAttribute(String key){
        return map.get(key);
    }
}
