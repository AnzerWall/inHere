package com.inHere.constant;

import java.io.File;

/**
 * 路径常量
 *
 * @author lwh
 */
public class Path {

    // 有求必应资源路径
    public static String DemandDir = System.getProperty("inHere.root") + File.separator + "resources" + File.separator + "demand" + File.separator;
    public static String DemandUri = "resources/demand/";

    // 有槽必吐资源路径
    public static String AskReplyDir = System.getProperty("inHere.root") + File.separator + "resources" + File.separator + "ask_reply" + File.separator;
    public static String AskReplyUri = "resources/ask_reply/";

    // 404图
    public static String Photo404 = System.getProperty("inHere.root") + File.separator + "resources" + File.separator + "404.jpg";
    public static String Photo404Uri = "resources/404.jpg";
}
