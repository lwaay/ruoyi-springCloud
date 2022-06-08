package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.domain.ExpertDynamicLike;
import com.sinonc.agriculture.dto.ExpertDynamicDetailDto;
import com.sinonc.agriculture.dto.ExpertDynamicDto;
import com.sinonc.agriculture.mapper.ExpertDynamicDtoMapper;
import com.sinonc.agriculture.mapper.ExpertDynamicLikeMapper;
import com.sinonc.agriculture.mapper.ExpertDynamicMapper;
import com.sinonc.agriculture.service.ExpertDynamicDtoService;
import com.sinonc.common.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Service
public class ExpertDynamicDtoServiceImpl implements ExpertDynamicDtoService {

    @Autowired
    private ExpertDynamicMapper expertDynamicMapper;

    @Autowired
    private ExpertDynamicDtoMapper expertDynamicDtoMapper;

    @Autowired
    private ExpertDynamicLikeMapper expertDynamicLikeMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    /**
     * 查询专家动态Dto列表
     *
     * @return
     */
    @Override
    public List<ExpertDynamicDto> listExpertDynamicDto(Boolean isLogin, ExpertDynamic expertDynamic) {

        List<ExpertDynamicDto> expertDynamicDtos = expertDynamicDtoMapper.selectExpertDynamicDtoList(expertDynamic);

        //未登录直接返回
        if (!isLogin) {
            return expertDynamicDtos;
        }

        //已登录添加点赞相关信息
        List<Long> dynamicIds = new ArrayList<>(expertDynamicDtos.size());

        expertDynamicDtos.forEach((o) -> {
            dynamicIds.add(o.getDynamicId());
        });

        List<ExpertDynamicLike> expertDynamicLikes = expertDynamicLikeMapper.selectByMemberIdAndDynamicIds(tokenService.getLoginUser().getUserid(), dynamicIds);

        for (ExpertDynamicDto expertDynamicDto : expertDynamicDtos) {
            for (ExpertDynamicLike expertDynamicLike : expertDynamicLikes) {
                if (expertDynamicDto.getDynamicId().equals(expertDynamicLike.getDynamicId())) {
                    expertDynamicDto.setIsLike(true);
                    break;
                }
            }
        }

        return expertDynamicDtos;
    }

    /**
     * 查询专家动态详情
     *
     * @param isLogin
     * @param dynamicId
     * @return
     */
    @Override
    public ExpertDynamicDetailDto getExpertDynamicDetailDto(Boolean isLogin, Long dynamicId) {


        ExpertDynamicDetailDto expertDynamicDetailDto = expertDynamicDtoMapper.selectExpertDynamicDetailDtoByDynamicId(dynamicId);

        executor.execute(new TimerTask() {
            @Override
            public void run() {
                expertDynamicMapper.addCount("read_count", expertDynamicDetailDto.getDynamicId());
            }
        });

        //未登录直接返回
        if (!isLogin) {
            return expertDynamicDetailDto;
        }

        //已登录检查点赞
        ExpertDynamicLike expertDynamicLike = expertDynamicLikeMapper.selectByMemberIdAndDynamicId(tokenService.getLoginUser().getUserid(), dynamicId);

        if (expertDynamicLike != null) {
            expertDynamicDetailDto.setIsLike(true);
        }


        return expertDynamicDetailDto;
    }


}
