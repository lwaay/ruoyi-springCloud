package com.sinonc.iot.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.iot.domain.InsectImg;
import com.sinonc.iot.mapper.InsectImgMapper;
import com.sinonc.iot.service.IInsectImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 虫情图片Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-10
 */
@Service
public class InsectImgServiceImpl  implements IInsectImgService {
    @Autowired
    private InsectImgMapper insectImgMapper;

    /**
     * 查询虫情图片
     *
     * @param id 虫情图片ID
     * @return 虫情图片
     */
    @Override
    public InsectImg selectInsectImgById(Long id) {
        return insectImgMapper.selectInsectImgById(id);
    }

    /**
     * 查询虫情图片列表
     *
     * @param insectImg 虫情图片
     * @return 虫情图片
     */
    @Override
    public List<InsectImg> selectInsectImgList(InsectImg insectImg) {
        return insectImgMapper.selectInsectImgList(insectImg);
    }

    /**
     * 新增虫情图片
     *
     * @param insectImg 虫情图片
     * @return 结果
     */
            @Override
    public int insertInsectImg(InsectImg insectImg) {
                                                                                        return insectImgMapper.insertInsectImg(insectImg);
            }

    /**
     * 修改虫情图片
     *
     * @param insectImg 虫情图片
     * @return 结果
     */
            @Override
    public int updateInsectImg(InsectImg insectImg) {
                                                                                    return insectImgMapper.updateInsectImg(insectImg);
    }

    /**
     * 删除虫情图片对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
            @Override
    public int deleteInsectImgByIds(String ids) {
                return insectImgMapper.deleteInsectImgByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除虫情图片信息
     *
     * @param id 虫情图片ID
     * @return 结果
     */
    @Override
    public int deleteInsectImgById(Long id) {
                return insectImgMapper.deleteInsectImgById(id);
    }

}
