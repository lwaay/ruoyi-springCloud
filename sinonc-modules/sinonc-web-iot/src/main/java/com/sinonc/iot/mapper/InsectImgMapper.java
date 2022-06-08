package com.sinonc.iot.mapper;

import com.sinonc.iot.domain.InsectImg;

import java.util.List;

/**
 * 虫情图片Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-10
 */
public interface InsectImgMapper {
    /**
     * 查询虫情图片
     *
     * @param id 虫情图片ID
     * @return 虫情图片
     */
    InsectImg selectInsectImgById(Long id);

    /**
     * 查询虫情图片列表
     *
     * @param insectImg 虫情图片
     * @return 虫情图片集合
     */
    List<InsectImg> selectInsectImgList(InsectImg insectImg);

    /**
     * 新增虫情图片
     *
     * @param insectImg 虫情图片
     * @return 结果
     */
    int insertInsectImg(InsectImg insectImg);

    /**
     * 修改虫情图片
     *
     * @param insectImg 虫情图片
     * @return 结果
     */
    int updateInsectImg(InsectImg insectImg);

    /**
     * 删除虫情图片
     *
     * @param id 虫情图片ID
     * @return 结果
     */
    int deleteInsectImgById(Long id);

    /**
     * 批量删除虫情图片
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteInsectImgByIds(String[] ids);

}
