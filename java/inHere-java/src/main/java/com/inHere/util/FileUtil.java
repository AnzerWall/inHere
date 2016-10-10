package com.inHere.util;

import com.inHere.constant.FileType;

import java.io.*;

/**
 * 文件操作工具
 *
 * @author lwh
 */
public class FileUtil {

    /**
     * 判断文件类型
     */
    public static FileType getTypeByByte(byte[] tmp) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = new ByteArrayInputStream(tmp);
        inputStream.read(b, 0, 28);
        // 获取文件头
        String fileHead = bytesToHex(b);
        return getType(fileHead);
    }

    /**
     * 判断文件类型
     */
    public static FileType getTypeByPath(String filePath) throws IOException {
        File file = new File(filePath);
        // 获取文件头
        String fileHead = getFileHeader(file);
        return getType(fileHead);
    }

    /**
     * 判断文件类型
     */
    public static FileType getTypeByFile(File file) throws IOException {
        // 获取文件头
        String fileHead = getFileHeader(file);
        return getType(fileHead);
    }

    /**
     * 获取文件头
     *
     * @return
     */
    private static FileType getType(String fileHead) {
        if (fileHead != null && fileHead.length() > 0) {
            fileHead = fileHead.toUpperCase();
            FileType[] fileTypes = FileType.values();

            for (FileType type : fileTypes) {
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }
        }
        return null;
    }

    /**
     * 读取文件头
     */
    private static String getFileHeader(File file) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            inputStream.read(b, 0, 28);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return bytesToHex(b);
    }

    /**
     * 将字节数组转换成16进制字符串
     */
    public static String bytesToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }
}
