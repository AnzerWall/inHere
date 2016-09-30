package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ActivityValidator extends BaseValidator {

    Logger log = Logger.getLogger(getClass());

    /**
     * 列表参数校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> getActivityList(ParamsListDto params) {
        return super.listValidator(params);
    }

}
