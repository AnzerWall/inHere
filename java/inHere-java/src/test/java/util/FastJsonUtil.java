package util;

import com.alibaba.fastjson.JSONObject;

/**
 */
public class FastJsonUtil {

    public static void main(String[] args) {
        JSONObject ext_data = new JSONObject();
        ext_data.put("name1", "value1");
        ext_data.put("name2", "value2");
        ext_data.put("name3", "value3");
        ext_data.put("name4", "value4");

        Object[] vals = ext_data.values().toArray();
        Object[] keys = ext_data.keySet().toArray();
        for (int i = 0; i < ext_data.size(); i++) {
            System.out.println(keys[i]);
            System.out.println(vals[i]);

        }
    }

}
