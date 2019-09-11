package xy.standard.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class QueryCardReqVo {
    /**
     * 账户ID：账户ID跟身份证号码两者必须有一个
     */
    @JSONField(name = "acct_id")
    private String acctId;

    /**
     * 身份证号：账户ID跟身份证号码两者必须有一个
     */
    @JSONField(name = "cert_No")
    private String certNo;

    /**
     * 卡类型：debit-借记卡，credit-贷记卡
     */
    @NotBlank(message = "卡类型不能为空")
    @Pattern(regexp = "^(debit)|(credit)$", message = "卡类型只能为debit或credit")
    @JSONField(name = "type")
    private String  type;
}
