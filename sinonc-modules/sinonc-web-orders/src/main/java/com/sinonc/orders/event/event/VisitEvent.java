package com.sinonc.orders.event.event;

import com.sinonc.orders.event.payload.VisitRecord;
import org.springframework.context.ApplicationEvent;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 15:42
 */
public class VisitEvent extends ApplicationEvent {

    private VisitRecord record;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public VisitEvent(Object source,VisitRecord record) {
        super(source);
        this.record = record;
    }

    public VisitRecord getRecord() {
        return record;
    }
}
