package com.sinonc.orders.utils;

import com.sinonc.common.core.exception.BusinessException;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: lw
 * @date: 2022/5/11 8:53
 * @description:
 */
public class TestMain {

    @SneakyThrows
    public static void main(String[] args) {
        URL url = new URL("http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/8bccbc20-bbe8-40a1-84ec-a7b856ce83c9.png");
        InputStream inputStream = url.openConnection().getInputStream();
        BufferedImage image = ImageIO.read(inputStream);
        BufferedImage certTemplate = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        //得到Graphics2D 对象
        Graphics2D g2d = (Graphics2D) certTemplate.getGraphics();
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        //设置颜色和画笔粗细
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.setFont(new Font("宋体", Font.PLAIN, 20));

        //绘制橙树主人信息
        g2d.drawString("甲大师", 200, 310);
        //绘制认养套餐信息
        g2d.drawString("测试认养套餐", 260, 595);
        //绘制橙树编号信息
        g2d.drawString("farm10086", 165, 622);
        //绘制年月日
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = sdf.format(date);
        String[] split = data.split("-");
        String timeString = split[0] + "年" + split[1] + "月" + split[2] + "日";
        System.out.println(timeString);
        g2d.drawString(timeString, 460, 945);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean flag = ImageIO.write(certTemplate, "JPG", byteArrayOutputStream);

        if (flag) {
            System.out.println("开始生成证书：=====================");
            FileOutputStream fileOutputStream = new FileOutputStream("");
            byte[] bytes = byteArrayOutputStream.toByteArray();
            fileOutputStream.write(bytes);
        } else {
            throw new BusinessException("生成证书图片出错");
        }
    }
}
