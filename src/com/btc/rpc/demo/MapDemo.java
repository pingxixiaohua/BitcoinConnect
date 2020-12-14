package com.btc.rpc.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap();//底层的实现数据结构是哈希表
        //key => value, key唯一，value无影响
        map.put("name","汪宁");
        map.put("sex","male");
        map.put("age","21");

        //遍历map：map中无序，无法直接遍历，只能先得到key，然后根据key获取value
        Set<String> keySet =  map.keySet();
        for (String key : keySet){
            String value = map.get(key);
            System.out.println(value);
        }
    }
}
