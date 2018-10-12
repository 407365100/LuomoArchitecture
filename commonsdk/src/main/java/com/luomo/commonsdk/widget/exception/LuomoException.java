package com.luomo.commonsdk.widget.exception;

/**
 * @author :renpan
 * @version :v1.0
 * @class :com.luomo.wechatmoments.demo.widget.exception
 * @date :2018-03-03 11:44
 * @description:
 */
public class LuomoException extends Exception {
    //唯一标识
    private static final long serialVersionUID = -7310086978676687044L;
    //错误代码
    private long errorCode;

    public LuomoException(long errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public LuomoException(long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public LuomoException(long errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public LuomoException(long errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public LuomoException() {
        super();
    }

    public LuomoException(String message) {
        super(message);
    }

    public LuomoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LuomoException(Throwable cause) {
        super(cause);
    }
}
