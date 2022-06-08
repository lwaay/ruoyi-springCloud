package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Templet;
import com.sinonc.orders.mapper.TempletMapper;
import com.sinonc.orders.service.TempletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 认养证书模版 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class TempletServiceImpl implements TempletService {
    @Autowired
    private TempletMapper templetMapper;

    /**
     * 查询认养证书模版信息
     *
     * @param templetId 认养证书模版ID
     * @return 认养证书模版信息
     */
    @Override
    public Templet getTempletById(Long templetId) {
        return templetMapper.selectTempletById(templetId);
    }

    /**
     * 查询认养证书模版列表
     *
     * @param templet 认养证书模版信息
     * @return 认养证书模版集合
     */
    @Override
    public List<Templet> listTemplet(Templet templet) {
        return templetMapper.selectTempletList(templet);
    }

    /**
     * 新增认养证书模版
     *
     * @param templet 认养证书模版信息
     * @return 结果
     */
    @Override
    public int addTemplet(Templet templet) {
        String lastNo = templetMapper.selectLastNo();
        if (lastNo != null) {
            String newTemletNo = lastNo.substring(7);
            int newNo = Integer.parseInt(newTemletNo) + 1;
            templet.setTempletNo("templet" + newNo);
        }
        templet.setCreateTime(new Date());
        templet.setCreateBy(SecurityUtils.getUsername());
        return templetMapper.insertTemplet(templet);
    }

    /**
     * 修改认养证书模版
     *
     * @param templet 认养证书模版信息
     * @return 结果
     */
    @Override
    public int updateTemplet(Templet templet) {
        return templetMapper.updateTemplet(templet);
    }

    /**
     * 删除认养证书模版对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTempletByIds(String ids) {
        return templetMapper.deleteTempletByIds(Convert.toStrArray(ids));
    }

}
