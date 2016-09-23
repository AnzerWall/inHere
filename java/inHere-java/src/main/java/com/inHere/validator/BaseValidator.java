package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.constant.FileType;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 基础校验器，实现类通过继承获取基础操作
 *
 * @author lwh
 */
public abstract class BaseValidator {

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public boolean isEmpty(Object obj) {
        return obj == null || obj.toString().trim().equals("");
    }

    /**
     * 判断是否为数字
     *
     * @param text
     * @return
     */
    public boolean notDigital(String text) {
        return !Pattern.matches("^\\d+$", text);
    }

    /**
     * 判断是否超长
     *
     * @return
     */
    public boolean isTooLong(String text, int len) {
        return text.trim().length() > len;
    }

    /**
     * 图片检测
     *
     * @param files 图片集
     * @param maxSize 最多的图片数
     * @param oneLen 每张图片的大小
     * @return
     * @throws IOException
     */
    public boolean errPhoto(List<MultipartFile> files, int maxSize, int oneLen) throws IOException {
        // 为true代表有错
        if (files.size() > maxSize) return true; // 图片数量超出
        for (MultipartFile file : files) {
            FileType fileType = FileUtil.getTypeByByte(file.getBytes());
            if ( fileType == null ) return true; // 图片类型不满足
            if (file.getSize() > oneLen) return true; // 单图片大小超出
            System.out.println("单图片大小: "+ file.getSize());
        }
        return false;
    }

    /**
     * 校验结果返回
     *
     * @return
     */
    public ReturnBaseDto<JSONObject> result(String msg) {
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());
        result.setMessage(msg);
        return result;
    }

    public static void main(String[] args) {
        BaseValidator test = new BaseValidator() {
        };
        System.out.println("你们好".length());
    }

}
