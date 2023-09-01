package cn.cloud.dx;

import java.io.*;

public class Main {

    public static void main(String[] args) {



    }


    public static void inputStream(String inputFilePathStr, String outputFilePathStr) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        try (InputStream fis = new FileInputStream(inputFilePathStr);
             FileOutputStream fos = new FileOutputStream(outputFilePathStr);
        ) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}