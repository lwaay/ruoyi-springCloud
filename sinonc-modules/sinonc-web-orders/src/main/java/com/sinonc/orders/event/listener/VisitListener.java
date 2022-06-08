package com.sinonc.orders.event.listener;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageHelper;
import com.sinonc.orders.domain.OdMemberVisit;
import com.sinonc.orders.event.event.VisitEvent;
import com.sinonc.orders.event.payload.VisitRecord;
import com.sinonc.orders.mapper.OdMemberVisitMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 15:45
 */
@Component
@Slf4j
public class VisitListener {

    @Autowired
    private OdMemberVisitMapper memberVisitMapper;

    private static final int MAX_VISIT_COUNT = 200;

    /**
     * 访问事件监听
     * @param customEvent
     * @return
     */
    @Async("lazyTraceExecutor")
    @EventListener(value = VisitEvent.class)
    public VisitEvent listener(VisitEvent customEvent){
        if(log.isDebugEnabled()){
            log.debug("添加事件监听：{}",customEvent.getRecord());
        }
        final VisitRecord record = customEvent.getRecord();
        OdMemberVisit visit = new OdMemberVisit();
        visit.setUserId(record.getUserId());
        visit.setTargetId(record.getTargetId());
        List<OdMemberVisit> memberVisitList = memberVisitMapper.selectOdMemberVisitList(visit);
        visit.setVisitTime(record.getVisitDate());
        if(memberVisitList.size() == 0){
            memberVisitMapper.insertOdMemberVisit(visit);
        }else{
            memberVisitList.get(0).setVisitTime(record.getVisitDate());
            memberVisitMapper.updateOdMemberVisit(memberVisitList.get(0));
        }
        //限制最大足迹为200
        if(memberVisitMapper.countOdMemberVisitById(record.getUserId()) > MAX_VISIT_COUNT){
            PageHelper.startPage(2,200,"visit_time desc");
            OdMemberVisit visit1 = new OdMemberVisit();
            visit1.setUserId(record.getUserId());
            List<OdMemberVisit> odMemberVisits = memberVisitMapper.selectOdMemberVisitList(visit1);
            long[] ids = odMemberVisits.stream().mapToLong(x -> {
                return x.getId();
            }).toArray();
            if(ids.length>0){
                memberVisitMapper.deleteOdMemberVisitByIds(Convert.toLongArray(ids));
            }
        }
        return null;
    }
}
