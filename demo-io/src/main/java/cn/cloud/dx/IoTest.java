package cn.cloud.dx;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author DongXin
 * @date 2023/7/20 14:32
 */
public class IoTest {

    public static void main(String[] args) throws FileNotFoundException {

        String type = args[0];
        String inputFilePath = args[1];
        String outputFilePath = args[2];

        if ("io".equalsIgnoreCase(type)) {
            inputStreamCopyFile(inputFilePath, outputFilePath);

        } else if ("buffer".equalsIgnoreCase(type)) {
            bufferInputStreamCopyFile(inputFilePath, outputFilePath);

        } else if ("mmap".equalsIgnoreCase(type)) {
            mappedFile(inputFilePath, outputFilePath);

        } else if ("sendfile".equalsIgnoreCase(type)) {
            sendfile(inputFilePath, outputFilePath);

        }
    }

    public static void mappedFile(String inputFilePathStr, String outputFilePathStr) {
        long start = System.currentTimeMillis();

        try (
                FileChannel channelIn = new FileInputStream(inputFilePathStr).getChannel();
                FileChannel channelOut = new RandomAccessFile(outputFilePathStr, "rw").getChannel();

        ) {
            long size = channelIn.size();
            System.out.println("mappedFile:" + size);
            MappedByteBuffer mbbi = channelIn.map(FileChannel.MapMode.READ_ONLY, 0, size);
            MappedByteBuffer mbbo = channelOut.map(FileChannel.MapMode.READ_WRITE, 0, size);
            for (int i = 0; i < size; i++) {
                byte b = mbbi.get(i);
                mbbo.put(i, b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void sendfile(String inputFilePathStr, String outputFilePathStr) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        try (
                FileChannel channelIn = new FileInputStream(inputFilePathStr).getChannel();
                FileChannel channelOut = new FileOutputStream(outputFilePathStr).getChannel();
        ) {
            // 代码一：针对小于2GB的问题，返回值为真实拷贝的size,最大拷贝2G，超出2G的部分将丢弃，最终拷贝文件大小只有2GB多点
            // channelIn.transferTo(0, channelIn.size(), channelOut);

            //代码二：针对大于2GB的文件，方案
            //获取文件总大小
            long size = channelIn.size();

            for (long left = size; left > 0; ) {
                //transferSize所拷贝过去的真实长度，size - left计算出下次要拷贝的位置
                long transferSize = channelIn.transferTo((size - left), left, channelOut);
                System.out.println("总大小：" + size + "，拷贝大小：" + transferSize);
                //left剩余字节多少
                left = left - transferSize;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void bufferInputStreamCopyFile(String inputFilePath, String outputFilePath) {
        long start = System.currentTimeMillis();
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFilePath));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFilePath));
        ) {

            byte[] buf = new byte[64];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    private static void inputStreamCopyFile(String inputFilePath, String outputFilePath) {

        long start = System.currentTimeMillis();
        try (
                FileInputStream fis = new FileInputStream(inputFilePath);
                FileOutputStream fos = new FileOutputStream(outputFilePath)
        ) {

            byte[] buf = new byte[64];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

    }

}
