package com.sinonc.orders.delaytask;

import com.sinonc.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * 认养系统 延迟队列
 */
@Slf4j
@Component
public class DelayQueueComponent {

    private DelayQueue<DelayTask> delayQueue;

    private static final String KEY = "DELAY_TASK_LIST";

    @Autowired
    private RedisService redisService;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 初始化延迟队列
     *
     * @return 延迟队列
     */
    @Bean
    public DelayQueue<DelayTask> delayQueue() {
        this.delayQueue = new DelayQueue<>();
        return delayQueue;
    }

    private static int times = 0;
    private static final int MAX_TIMES = 3;

    @Async
    public void consumer() {
        // 获取 ExpiredTaskExecutorService , 使用 @Autowired 会导致循环依赖问题
        Map<String, Object> objectMap = applicationContext.getBeansWithAnnotation(Service.class);
        ExpiredTaskExecutorService expiredTaskExecutorService = (ExpiredTaskExecutorService) objectMap.get("expiredTaskExecutorService");

        while (times <= MAX_TIMES) {
            try {
                DelayTask take = delayQueue.take();

                if (take.getBody() != null) {
                    //删除Redis中序列化的任务
                    redisService.redisTemplate.opsForList().remove(KEY, 0, take);

                    //通知执行器执行任务
                    expiredTaskExecutorService.executor(take);
                    //删除Redis中序列化的任务
                    redisService.redisTemplate.opsForList().remove(KEY, 0, take);
                }

            } catch (InterruptedException e) {
                log.error(e.getMessage());
                times++;
            }
        }
    }

}
