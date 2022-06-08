package com.sinonc.orders.delaytask;

import com.sinonc.common.core.lang.UUID;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务
 */

public class DelayTask implements Delayed, Serializable {


    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 过期时间
     */
    private Long expire;

    /**
     * 创建时间
     */
    private Long creatTime;

    /**
     * 任务体
     */
    private Object body;

    private DelayTask() {

    }

    /**
     * 采用工厂方法，创建任务对象
     * @param taskType 任务类型
     * @param expire 过期时间
     * @param creatTime 创建时间
     * @param body 任务消息
     * @return 任务对象
     */
    public static DelayTask getInstance(Integer taskType, Long expire, Long creatTime, Object body) {

        DelayTask delayTask = new DelayTask();

        delayTask.setBody(body);
        delayTask.setTaskType(taskType);
        delayTask.setExpire(expire);
        delayTask.setCreatTime(creatTime);
        delayTask.setTaskId(UUID.fastUUID().toString());

        return delayTask;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getTaskId() {
        return taskId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public Long getExpire() {
        return expire;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public Object getBody() {
        return body;
    }

    /**
     * 任务过期逻辑
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return this.expire - System.currentTimeMillis();
    }

    /**
     * 根据过期时间排序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        DelayTask delayTask = (DelayTask) o;
        return this.expire.compareTo(delayTask.getExpire());
    }



}
