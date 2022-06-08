package com.sinonc.common.core.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

/**
 * @anthor wang
 */
public class CreateQrCode {

    /**
     *
     * @param url 链接头
     * @param text code
     * @return
     * @throws IOException
     */
    public static String getQrCode(String url,String text) throws IOException {
        String baseUrlString =url+text;
       return createQrCodeImg(baseUrlString);

    }
    /**
     * 创建二维码
     * @param text
     * @return
     * @throws IOException
     */
    private static String createQrCodeImg(String text) throws IOException {
        int width = 190;
        int height = 190;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。
        ImageIO.write(image, format, os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
        byte b[] = os.toByteArray();//从流中获取数据数组。
        String str= Base64.getEncoder().encodeToString(b);
        return str;
    }
}
