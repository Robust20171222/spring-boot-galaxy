package com.galaxy.bigdata.sentry;

/**
 * @author pengwang
 * @date 2019/06/19
 */
public class InternalException extends Exception{

    public InternalException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InternalException(String msg) {
        super(msg);
    }
}