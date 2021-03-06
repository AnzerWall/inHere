package com.inHere.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据库字段常量
 *
 * @author lwh
 */
public class Field {

    // 已点赞
    public final static Integer Praised_YES = 1;
    // 未点赞
    public final static Integer Praised_NO = 0;

    // 活动内容：URL类型
    public final static Integer URLType_URL = 0;
    // 活动内容：模版类型
    public final static Integer URLType_Model = 1;

    // 已结束
    public final static Integer IsEnd_YES = 1;
    // 未结束
    public final static Integer IsEnd_NO = 0;

    // 性别：女
    public final static Integer Sex_Woman = 0;
    // 性别：男
    public final static Integer Sex_Man = 1;
    // 性别：不知道
    public final static Integer Sex_NotKnow = 2;

    // 标签：自定义
    public final static Integer Label_Custom = 0;

    // 成色
    public final static Map<Integer, String> Quality = new LinkedHashMap<>();

    // 显示
    public final static Integer Is_Show_No = 0;
    // 隐藏
    public final static Integer Is_Show_Yes = 1;

    // 是管理员
    public final static Integer Is_Admin_Yes = 1;
    // 非管理员
    public final static Integer Is_Admin_No = 0;


    /*-------------------栏目模块常量---------------------*/

    // 兼职
    public final static Integer ExtType_Express = 1;
    // 转让
    public final static Integer ExtType_Sell = 2;
    // 帮忙
    public final static Integer ExtType_Help = 3;
    // 丢失
    public final static Integer ExtType_Lost = 4;
    // 找到
    public final static Integer ExtType_Found = 5;
    // 走起
    public final static Integer ExtType_Dating = 6;
    // 目标清单
    public final static Integer ExtType_TargetList = 7;
    // 活动
    public final static Integer ExtType_Activity = 8;
    // 广告
    public final static Integer ExtType_AD = 9;
    // 校内吐槽
    public final static Integer ExtType_InTeasing = 10;
    // 校外吐槽
    public final static Integer ExtType_OutTeasing = 11;
    // 问答
    public final static Integer ExtType_AskAnwser = 12;

    static {
        // 成色
        Quality.put(1, "一成");
        Quality.put(2, "二成");
        Quality.put(3, "三成");
        Quality.put(4, "四成");
        Quality.put(5, "五成");
        Quality.put(6, "六成");
        Quality.put(7, "七成");
        Quality.put(8, "八成");
        Quality.put(9, "九成");
        Quality.put(10, "十成");
    }

}
