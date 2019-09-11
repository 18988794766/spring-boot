package xy.standard.service.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.time.FastDateFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Random;

@Data
public class BaseRequestVo<T> {
    @NotBlank(message = "请求方系统不能为空")
    @JSONField(name = "system")
    private String system;

    @NotBlank(message = "项目编码不能为空")
    @JSONField(name = "project_code")
    private String projectCode;

    @NotBlank(message = "请求ID不能为空")
    @JSONField(name = "req_id")
    private String reqId;

    @NotNull(message = "请求时间戳不能为空")
    @JSONField(name = "timestamp")
    private Long timestamp;

    @Valid
    @JSONField(name = "data")
    private T data;

    public BaseRequestVo() {
    }

    public BaseRequestVo(String system, String projectCode) {
        this.system = system;
        this.projectCode = projectCode;
        this.reqId = FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date()) + randomSixNum();
        this.timestamp = System.currentTimeMillis();
    }

    public BaseRequestVo(String system, String projectCode, T data) {
        this(system, projectCode);
        this.data = data;
    }

    private static String randomSixNum() {
        Random random = new Random();
        int result = random.nextInt(899999);
        result += 100000;
        return String.valueOf(result);
    }

    public String toString() {
        return "BaseRequestVo(system=" + this.getSystem() + ", projectCode=" + this.getProjectCode() + ", reqId=" + this.getReqId() + ", timestamp=" + this.getTimestamp() + ", data=" + this.getData() + ")";
    }
}
