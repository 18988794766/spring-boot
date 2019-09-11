package xy.standard.service.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class BaseResponseVo<T> {
    public static final Integer CODE_SUCCESS = 0;
    public static final Integer COMMON_ERROR_CODE = 999999;
    public static final String MSG_SUCCESS = "success";

    @JSONField(name = "timestamp")
    private Long timestamp;

    @JSONField(name = "code")
    private Integer code;

    @JSONField(name = "msg")
    private String msg;

    @JSONField(name = "data")
    private T data;

    public BaseResponseVo() {
        this.timestamp = System.currentTimeMillis();
        this.code = CODE_SUCCESS;
        this.msg = "success";
    }

    public BaseResponseVo(String msg) {
        this();
        this.msg = msg;
    }

    public BaseResponseVo(T data) {
        this();
        this.data = data;
    }

    public BaseResponseVo(int code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public BaseResponseVo(int code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public String toString() {
        return "BaseResponseVo(timestamp=" + this.getTimestamp() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
