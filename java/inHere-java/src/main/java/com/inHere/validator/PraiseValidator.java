package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ReturnBaseDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PraiseValidator extends BaseValidator {

    /**
     * 点赞参数校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> praise(Map<String, String> params) {
        // true含有错误标志，否则无错误
        boolean flag = false;
        String item_id = params.get("item_id");
        String ext_type = params.get("ext_type");

        flag = this.isEmpty(item_id);
        flag = flag || this.notDigital(item_id);

        if ( !this.isEmpty(ext_type) ){
            flag = flag || this.notDigital(ext_type);
        }

        if (flag) return this.result("参数格式有错");

        return null;
    }


}
