package com.sinonc.orders.service.impl;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.oss.service.UploadUtil;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.orders.domain.Cert;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.OrderItem;
import com.sinonc.orders.mapper.CertMapper;
import com.sinonc.orders.mapper.OrderItemMapper;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.service.CertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 认养证书 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
@Slf4j
public class CertServiceImpl implements CertService, PayObserver {

    @Autowired
    private CertMapper certMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UploadUtil uploadService;

    //认养证书模版地址
    @Value("${adopt.cert.url}")
    private String certTemplateUrl;

    /**
     * 查询认养证书信息
     *
     * @param certId 认养证书ID
     * @return 认养证书信息
     */
    @Override
    public Cert getCertById(Long certId) {
        return certMapper.selectCertById(certId);
    }

    /**
     * 查询认养证书列表
     *
     * @param cert 认养证书信息
     * @return 认养证书集合
     */
    @Override
    public List<Cert> listCert(Cert cert) {
        return certMapper.selectCertList(cert);
    }

    /**
     * 新增认养证书
     *
     * @param cert 认养证书信息
     * @return 结果
     */
    @Override
    public int addCert(Cert cert) {
        log.error("认养证书地址：{}", certTemplateUrl);
        String lastNo = certMapper.selectLastNo();

        if (lastNo == null) {
            lastNo = "farm1000";
        }

        String num = lastNo.substring(4);
        int newNo = Integer.parseInt(num) + 1;

        String imgUrl;

        //生成认养证书
        try {

            URL url = new URL(certTemplateUrl);
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
            g2d.drawString(cert.getMasterName(), 200, 310);
            //绘制认养套餐信息
            g2d.drawString(cert.getSpecsName(), 260, 595);
            //绘制橙树编号信息
            g2d.drawString("farm" + newNo, 165, 622);
            //绘制年月日
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String data = sdf.format(date);
            String[] split = data.split("-");
            String timeString = split[0] + "年" + split[1] + "月" + split[2] + "日";
            g2d.drawString(timeString, 460, 945);
//            g2d.drawString(split[1], 524, 945);
//            g2d.drawString(split[2], 570, 945);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(certTemplate, "JPG", byteArrayOutputStream);

            if (flag) {
                byte[] bytes = byteArrayOutputStream.toByteArray();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                imgUrl = uploadService.upload(byteArrayInputStream, ContentType.IMAGE_JPEG, ".jpg");
            } else {
                throw new BusinessException("生成证书图片出错");
            }

            cert.setServNo("farm" + newNo);
            cert.setCreateTime(new Date());
            cert.setCreateBy("system");
            cert.setImgUrl(imgUrl);
            cert.setAdoptTime(new Date());
            return certMapper.insertCert(cert);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return 图片在服务器的路径
     * @throws
     * @Title: makeImageUpload
     * @Description: 生成认养牌图片上传到服务器
     * @author WXH
     */
    private String makeImageUpload(Integer certType, Cert cert, String adoptTime, String packageName, String templetUrl) {
        String imageUrl = "";
        //读取图片文件，得到BufferedImage对象
        BufferedImage bimg;
        try {
            URL url = null;
            if (certType == 0) {
                url = new URL(templetUrl);//认养牌
            } else if (certType == 1) {
                url = new URL(templetUrl);//认养证书
            }

            InputStream is = url.openConnection().getInputStream();
            bimg = ImageIO.read(is);

            //得到Graphics2D 对象
            Graphics2D g2d = (Graphics2D) bimg.getGraphics();
            //设置颜色和画笔粗细
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));

            if (certType == 0) {//认养牌
                g2d.setFont(new Font("宋体", Font.PLAIN, 30));
                //绘制橙树主人信息
                g2d.drawString(cert.getMasterName(), 400, 180);
                //绘制祝福语信息
                g2d.drawString(cert.getBlessDesc(), 300, 255);
                //绘制橙树编号信息
                g2d.drawString(cert.getServNo(), 450, 325);
                //绘制城市信息
                g2d.drawString(cert.getCity(), 580, 383);
                //绘制认养时间信息
//				Date date = DateUtil.parseStr(adoptTime,"yyyy-MM-dd");
//				String year = DateUtil.getDateFormat(date, "yyyy");
//				g2d.drawString(year, 384, 387);
            } else if (certType == 1) {//认养证书
                g2d.setFont(new Font("宋体", Font.PLAIN, 30));
                //绘制橙树主人信息
                g2d.drawString(cert.getMasterName(), 200, 310);
                g2d.setFont(new Font("宋体", Font.PLAIN, 20));
                //绘制认养套餐信息
                g2d.drawString(packageName, 260, 580);
                //绘制橙树编号信息
                g2d.drawString(cert.getServNo(), 165, 612);
                //绘制年月日
                Date date = new Date();
//				String parseSmallDate = DateUtil.parseSmallDate(date);
//				String[] split = parseSmallDate.split("-");
//				g2d.drawString(split[0], 460, 945);
//				g2d.drawString(split[1], 524, 945);
//				g2d.drawString(split[2], 570, 945);
            }


            ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
            //保存新图片
            Boolean flag = ImageIO.write(bimg, "JPG", byte_out);
            if (flag) {
                //将图片转换成byte[]，以byte[]数组上传到阿里云
                byte[] imageByte = byte_out.toByteArray();
                //上传图片到阿里云服务器
//				Map<String, Object> map= UploadUtil.uploadImage(imageByte);
//				imageUrl = map.get("url").toString();
            } else {
                throw new RuntimeException("生成图片出错！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    /**
     * 修改认养证书
     *
     * @param cert 认养证书信息
     * @return 结果
     */
    @Override
    public int updateCert(Cert cert) {
        return certMapper.updateCert(cert);
    }

    /**
     * 删除认养证书对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCertByIds(String ids) {
        return certMapper.deleteCertByIds(Convert.toStrArray(ids));
    }


    @Override
    public void payNotify(PayMessage message) throws Exception {

        String orderNo = message.getOrderNo();
        log.error("订单编号：{}", orderNo);
        //如果不是预认养单，则不做处理
        if (orderNo.contains("DJ") || orderNo.contains("WK") || orderNo.contains("JP")) {
            return;
        }

        Order order = orderMapper.selectOrderByNo(orderNo);

        //判断是否是认养订单
        if (order != null && order.getOrderType().equals(0)) {

            //支付成功之后创建认养证书
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderIdP(order.getOrderId());

            List<OrderItem> orderItems = orderItemMapper.selectOrderItemList(orderItem);

            //判断认养数量，如果有多个，则创建多分证书
            for (OrderItem item : orderItems) {
                log.error("订单详情id：{}", item.getOrderItemId());
                Cert cert = new Cert();
                cert.setCertType(1);
                cert.setMasterName(order.getReceiver());
                cert.setUserId(order.getMemberIdP());
                cert.setOrderId(order.getOrderId());
                cert.setSpecsName(item.getGoodsSpecsName());
                this.addCert(cert);
            }
        }
    }
}
