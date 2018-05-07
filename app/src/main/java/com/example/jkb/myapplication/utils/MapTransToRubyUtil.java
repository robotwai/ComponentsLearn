package com.example.jkb.myapplication.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by jkb on 18/5/7.
 */

public class MapTransToRubyUtil {

    public static String transToRubyMap(Map<String,String> javaMap){
        Iterator it = javaMap.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry)it.next();
            System.out.println(map .getKey() + " = " + map .getValue());
            if (sb.length()==0){
                sb.append("{");
            }
            sb.append("\""+map .getKey()+"\""+"=>"+"\""+map.getValue()+"\""+",");
        }
        if (sb.length()>0){
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
            return sb.toString();
        }else {
            return "";
        }

    }
}
