package com.sinonc.orders.delaytask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * spring 容器启动之后执行，确保所有 bean 都被加载到容器
 *
 * @author huanghao
 */
@Component
@Slf4j
public class RunAfter implements ApplicationRunner {

    @Autowired
    private DelayTaskService delayTaskService;

    @Override
    public void run(ApplicationArguments args){
        log.info("Spring After Run");
        delayTaskService.loadDelayTask();
    }
}
