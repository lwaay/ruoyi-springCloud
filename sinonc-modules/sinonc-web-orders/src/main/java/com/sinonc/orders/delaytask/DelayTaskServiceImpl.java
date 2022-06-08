package com.sinonc.orders.delaytask;

import com.sinonc.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * 延迟队列服务
 */
@Slf4j
@Service
public class DelayTaskServiceImpl implements DelayTaskService {

    private static final String KEY = "DELAY_TASK_LIST";

    @Autowired
    private DelayQueueComponent component;

    @Autowired
    private DelayQueue<DelayTask> delayQueue;

    @Autowired
    private RedisService redisService;


    /**
     * 容器启动后执行
     * @see RunAfter
     */
    @Override
    public void loadDelayTask() {
        try {
            /**
             * 启动队列消费者，消费队列中过期消息
             */
            component.consumer();

            //初始redis队列，用于保存任务ID
            List<Object> delayTaskList = redisService.redisTemplate.opsForList().range(KEY, 0, -1);

            if (delayTaskList != null) {
                for (Object o : delayTaskList) {
                    delayQueue.add((DelayTask) o);
                }
                log.info("load delay task size：" + delayQueue.size());
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }

    /**
     * 添加延时任务
     *
     * @param delayTask 延时任务
     */
    @Override
    public void addTask(DelayTask delayTask) {
        try {
            delayQueue.add(delayTask);
            redisService.redisTemplate.opsForList().rightPush(KEY, delayTask);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }

    }


}
