package xy.standard.service.exception;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;
    private int code = 1;
    private Object[] args;
    private boolean notPrintStack;

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, boolean notPrintStack) {
        this.notPrintStack = notPrintStack;
        this.code = code;
    }

    public BusinessException(int code, Throwable cause) {
        super((String)null, cause);
        this.code = code;
    }

    public BusinessException(int code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    public BusinessException(int errorCode, String message, Object... params) {
        super(MessageFormat.format(message, params));
        this.code = errorCode;
    }

    public BusinessException(Throwable cause, int errorCode, String message, Object... params) {
        super(MessageFormat.format(message, params), cause);
        this.code = errorCode;
    }

    public BusinessException(Throwable cause, String message, Object... params) {
        super(MessageFormat.format(message, params), cause);
    }

    public BusinessException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.code = errorCode;
    }

    protected BusinessException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public boolean isNotPrintStack() {
        return this.notPrintStack;
    }
}
