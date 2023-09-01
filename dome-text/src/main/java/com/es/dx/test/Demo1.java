package com.es.dx.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author DongXin
 * @date 2023/2/16 16:47
 */
public class Demo1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String objectName = "https://ideafusion.oss-accelerate.aliyuncs.com/dev/admin/supplier/0.1488689084591417/2023429/黑色+R.png";
//        String before = StrUtil.subBefore(objectName, "/", true);
//        String after = StrUtil.subAfter(objectName, "/",true);
//        String encode = URLEncoder.encode(after, "utf-8");
//        String append = StrUtil.builder().append(before).append("/").append(encode).toString();
//        System.out.println(objectName);
//        System.out.println(append);

        String date = "2023-06-25";
        String start = DateUtil.beginOfDay(DateUtil.parse(date)).toString();
        String end = DateUtil.endOfDay(DateUtil.parse(date)).toString();
        String format = StrUtil.format("start:{}，end:{}", start, end);
        System.out.println(format);
    }
}
