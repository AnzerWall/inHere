package com.inHere.constant;

import java.io.File;

/**
 * 路径常量
 *
 * @author lwh
 */
public class Path {

    // 有求必应资源路径
    public static String PhotoDir = System.getProperty("inHere.root") + File.separator + "resources" + File.separator + "photos" + File.separator;
    public static String PhotoUri = "resources/photos/";

    // 404图
    public static String Photo404 = System.getProperty("inHere.root") + File.separator + "resources" + File.separator + "404.jpg";
    public static String Photo404Uri = "resources/404.jpg";
}
