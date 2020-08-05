package com.feeder.flashsale.logging;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

/**
 * In async situations, LogbackMdcTaskDecorator is used to pass logback MDC from one thread to another.
 */
public class LogMdcTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                    MDC.put(RequestIdMdcFilter.THREAD_ID, String.valueOf(Thread.currentThread().getId()));
                }
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
