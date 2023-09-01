package com.es.dx;

/**
 * @author DongXin
 * @date 2023/6/7 14:27
 */
public class Application {

    public static void main(String[] args) {
        String ContentMd5 = Utils.MD5Example("/v3/3rd/users?user_ids=732499152");
        System.out.println("Content-Md5:" + ContentMd5);

        System.out.println("Content-Type: application/json");

        String date = Utils.RFC1123Example();
        System.out.println("DATE:" + date);

        String APPID = "SX20230607SAIWEY";
        String AppSecret = "KJHlTZJbCYXjQJwkFvnUbTOlqcpldLjA";
        String Content_Md5 = ContentMd5;
        String Content_Type = "application/json";
        String Date = date;
        String Authorization = Utils.getAuthorization(APPID, AppSecret, Content_Md5, Content_Type, Date);
        System.out.println("Authorization:" + Authorization);

    }
}
