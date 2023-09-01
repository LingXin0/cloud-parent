package cn.cloud.dx;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author DongXin
 * @date 2023/7/8 00:03
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("test");


        //指定把图片放到哪个位置
        ClientAnchor anchors = new XSSFClientAnchor();

        int col1 = 0, col2 = 1, row1 = 0, row2 = 1; //四个坐标
        anchors.setCol1(col1);
        anchors.setCol2(col2); //锚点1
        anchors.setRow1(row1);
        anchors.setRow2(row2); //锚点2

        Row row = sheet.createRow(row1);
        row.setHeight((short) 5000);
        sheet.setColumnWidth(0, 10000);
        row.createCell(0);


        double rowHeight = row.getHeightInPoints() / 72 * 96; //行高，此处的值不精确
        double colWidth = sheet.getColumnWidthInPixels(col1); //列宽，此处的值不精确

        double collWidthSum = colWidth * (col2 - col1), rowHeightSum = rowHeight * (row2 - row1);  //区域的实际大小


        //创建一个字节输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //读取图片
        BufferedImage bufferedImage = ImageIO.read(new File("/Users/dongxin/Documents/work/aaa/a.jpg"));
        //把读取到图像放入到输出流中
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        //创建一个绘图控制类，负责画图
        Drawing drawing = sheet.createDrawingPatriarch();

        InputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());  //图片大小
        int imgaeWidth = 0, imageHeight = 0; //图片宽高
        try {
            BufferedImage bufferImage = ImageIO.read(bais);
            imgaeWidth = bufferImage.getWidth();
            imageHeight = bufferImage.getHeight();
        } catch (IOException e) { /** TODO 异常处理**/} finally { /** TODO 关闭流 **/}
        double scale = Math.min(collWidthSum / imgaeWidth, rowHeightSum / imageHeight);  //计算缩放比例
        double realImageWidth = imgaeWidth * scale, realImageHeight = imageHeight * scale;  //处理后图片的大小
        double moveX = realImageWidth == collWidthSum ? 0 : (collWidthSum - realImageWidth) / 2; //计算出需要图片需要移动的坐标 X轴
        double moveY = realImageHeight == rowHeightSum ? 0 : (rowHeightSum - realImageHeight) / 2; //计算出需要图片需要移动的坐标 Y轴
        anchors.setDx1(Units.EMU_PER_PIXEL * (int) (-realImageWidth - moveX));
        anchors.setDx2(Units.EMU_PER_PIXEL * (int) (-moveX));
        anchors.setDy1(Units.EMU_PER_PIXEL * (int) (-realImageHeight - moveY));
        anchors.setDy2(Units.EMU_PER_PIXEL * (int) (-moveY));
        anchors.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        drawing.createPicture(anchors, sheet.getWorkbook().addPicture(byteArrayOutputStream.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));


        try (FileOutputStream fileOutputStream = new FileOutputStream("/Users/dongxin/Documents/work/aaa/test.xlsx")) {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
