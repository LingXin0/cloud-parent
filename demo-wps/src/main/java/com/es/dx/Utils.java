package com.es.dx;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author DongXin
 * @date 2023/6/7 14:27
 */
public class Utils {


    public static String RFC1123Example() {
        // 获取当前的日期和时间
        ZonedDateTime now = ZonedDateTime.now();

        // 创建一个RFC1123格式的日期和时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        // 将日期和时间格式化为RFC1123格式的字符串
        String rfc1123Date = now.format(formatter);

        // 输出RFC1123格式的日期和时间
        return rfc1123Date;
    }

    @SneakyThrows
    public static String MD5Example(String str) {
        // 创建一个MD5加密器
        MessageDigest md = MessageDigest.getInstance("MD5");

        // 计算字符串的MD5摘要
        byte[] digest = md.digest(str.getBytes());

        // 将MD5摘要转换为16位小写的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 4; i < 12; i++) { // 取摘要的第5-12个字节进行转换
            sb.append(String.format("%02x", digest[i] & 0xff));
        }
        return sb.toString();
    }

    @SneakyThrows
    public static String getAuthorization(String APPID, String AppSecret, String Content_Md5, String Content_Type, String Date) {

        StringBuilder Authorization = StrUtil.builder("WPS-2:").append(APPID).append(":");
        String str = StrUtil.builder().append(AppSecret).append(Content_Md5).append(Content_Type).append(Date).toString();
        // 创建一个SHA加密器（SHA-256、SHA-384或SHA-512）
        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        // 计算字符串的SHA摘要
        byte[] digest = sha.digest(str.getBytes());

        // 将SHA摘要转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        String shaHex = sb.toString();
        Authorization.append(shaHex);
        return Authorization.toString();
    }
}
