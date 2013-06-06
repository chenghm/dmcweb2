package com.cinsec.dmc.base.util;

import org.apache.commons.logging.Log;

public class LogUtils {

    public static void debug(Log log, String message) {
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }

    public static void info(Log log, String message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public static void warn(Log log, String message) {
        if (log.isWarnEnabled()) {
            log.warn(message);
        }
    }

    public static void error(Log log, String message) {
        if (log.isErrorEnabled()) {
            log.error(message);
        }
    }
}
