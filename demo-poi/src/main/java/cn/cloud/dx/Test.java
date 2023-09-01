package cn.cloud.dx;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author DongXin
 * @date 2023/7/8 00:03
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("test");
        Row row = sheet.createRow(0);
        row.setHeight((short) 5000);
        sheet.setColumnWidth(0, 10000);
        Cell cell = row.createCell(0);
        //创建一个字节输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //读取图片
        BufferedImage bufferedImage = ImageIO.read(new File("/Users/dongxin/Documents/work/aaa/a.jpg"));
        //把读取到图像放入到输出流中
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        //创建一个绘图控制类，负责画图
        Drawing drawingPatriarch = sheet.createDrawingPatriarch();
        //指定把图片放到哪个位置
        //dx1 - the x coordinate within the first cell.//定义了图片在第一个cell内的偏移x坐标，既左上角所在cell的偏移x坐标，一般可设0
        //dy1 - the y coordinate within the first cell.//定义了图片在第一个cell的偏移y坐标，既左上角所在cell的偏移y坐标，一般可设0
        //dx2 - the x coordinate within the second cell.//定义了图片在第二个cell的偏移x坐标，既右下角所在cell的偏移x坐标，一般可设0
        //dy2 - the y coordinate within the second cell.//定义了图片在第二个cell的偏移y坐标，既右下角所在cell的偏移y坐标，一般可设0
        //col1 - the column (0 based) of the first cell.//第一个cell所在列，既图片左上角所在列
        //row1 - the row (0 based) of the first cell.//图片左上角所在行
        //col2 - the column (0 based) of the second cell.//图片右下角所在列
        //row2 - the row (0 based) of the second cell.//图片右下角所在行
        ClientAnchor clientAnchor = new XSSFClientAnchor(10000*10, 10000*10, 10000*10, 10000*10, 0, 0, 1, 1);
        clientAnchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);
        // 开始把图片写入到sheet指定的位置
        Picture picture = drawingPatriarch.createPicture(clientAnchor, workbook.addPicture(
                byteArrayOutputStream.toByteArray(), Workbook.PICTURE_TYPE_JPEG));

        picture.resize(0.99,0.90);

        try (FileOutputStream fileOutputStream = new FileOutputStream("/Users/dongxin/Documents/work/aaa/test.xlsx")) {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
