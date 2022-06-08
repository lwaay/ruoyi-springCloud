package com.sinonc.base.service.impl;

import com.sinonc.base.api.domain.CropDict;
import com.sinonc.base.mapper.CropDictMapper;
import com.sinonc.base.service.ICropDictService;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.system.api.vo.TreeSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作物字典Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class CropDictServiceImpl implements ICropDictService {
    @Autowired
    private CropDictMapper cropDictMapper;

    /**
     * 查询作物字典
     *
     * @param cropId 作物字典ID
     * @return 作物字典
     */
    @Override
    public CropDict selectCropDictById(Long cropId) {
        return cropDictMapper.selectCropDictById(cropId);
    }

    /**
     * 查询作物字典列表
     *
     * @param cropDict 作物字典
     * @return 作物字典
     */
    @Override
    public List<CropDict> selectCropDictList(CropDict cropDict) {
        return cropDictMapper.selectCropDictList(cropDict);
    }

    /**
     * 新增作物字典
     *
     * @param cropDict 作物字典
     * @return 结果
     */
    @Override
    public int insertCropDict(CropDict cropDict) {
        cropDict.setCreateTime(DateUtils.getNowDate());
        return cropDictMapper.insertCropDict(cropDict);
    }

    /**
     * 修改作物字典
     *
     * @param cropDict 作物字典
     * @return 结果
     */
    @Override
    public int updateCropDict(CropDict cropDict) {
        cropDict.setUpdateTime(DateUtils.getNowDate());
        return cropDictMapper.updateCropDict(cropDict);
    }

    /**
     * 批量删除作物字典
     *
     * @param cropIds 需要删除的作物字典ID
     * @return 结果
     */
    @Override
    public int deleteCropDictByIds(Long[] cropIds) {
        return cropDictMapper.deleteCropDictByIds(cropIds);
    }

    /**
     * 删除作物字典信息
     *
     * @param cropId 作物字典ID
     * @return 结果
     */
    @Override
    public int deleteCropDictById(Long cropId) {
        return cropDictMapper.deleteCropDictById(cropId);
    }

    /**
     * 获取作物字典树
     * @return
     */
    @Override
    public List<TreeSelectVo> treeCropDict() {
        //获取所有父节点
        CropDict query = new CropDict();
        query.setParentId(0L);
        List<CropDict> parentList = cropDictMapper.selectCropDictList(query);
        if(CollectionUtils.isEmpty(parentList)){
            return null;
        }
        List<TreeSelectVo> tree = initCropDictTree(parentList);
        return tree;
    }

    /**
     * 组装作物字典树
     * @param parentList
     * @return
     */
    private List<TreeSelectVo> initCropDictTree(List<CropDict> parentList){
        if (CollectionUtils.isEmpty(parentList)){
            return null;
        }
        //获取所有节点数据
        List<CropDict> allCropDict = cropDictMapper.selectCropDictList(new CropDict());
        //定义返回的Tree
        List<TreeSelectVo> tree = new ArrayList<>();
        //根据父节点递归获取所有子节点
        parentList.forEach(parent->{
            TreeSelectVo treeItem = new TreeSelectVo();
            treeItem.setId(parent.getCropId());
            treeItem.setLabel(parent.getCropName());
            treeItem.setChildren(recursionCropDictTree(parent,allCropDict));
            tree.add(treeItem);
        });
        return tree;
    }

    /**
     * 获取父节点所有子节点树
     * @param parent
     * @param allCropDict
     * @return
     */
    private List<TreeSelectVo> recursionCropDictTree(CropDict parent,List<CropDict> allCropDict){
        List<TreeSelectVo> children = new ArrayList<>();
        allCropDict.forEach(child->{
            if (child.getParentId().equals(parent.getCropId())){
                TreeSelectVo note = new TreeSelectVo();
                note.setId(child.getCropId());
                note.setLabel(child.getCropName());
                note.setChildren(recursionCropDictTree(child,allCropDict));
                children.add(note);
            }
        });
        return children;
    }
}
