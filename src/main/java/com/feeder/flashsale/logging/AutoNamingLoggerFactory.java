package com.feeder.flashsale.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SLF4J LoggerFactory that creates loggers with automatically generated names following full class name pattern.
 */
public final class AutoNamingLoggerFactory {
    private AutoNamingLoggerFactory() {
    }

    public static Logger getLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callersClassName = stackTrace[2].getClassName();
        return LoggerFactory.getLogger(callersClassName);
    }
}
