package xy.standard.service.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xy.standard.service.vo.BaseResponseVo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 使用ControllerAdvice和ExceptionHandler处理全局异常
 * Author：HuangLibin 2019/09/11
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionFilter {
    @ExceptionHandler({Exception.class})
    public BaseResponseVo<Object> commonExceptionHandler(Exception ex) {
        return new BaseResponseVo<>(500, "服务端异常:" + ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponseVo<Object> argumentExceptionHandler(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        List<String> messages = new ArrayList<String>(errors.size());
        Iterator var4 = errors.iterator();

        while (var4.hasNext()) {
            ObjectError oe = (ObjectError) var4.next();
            messages.add(oe.getDefaultMessage());
        }

        return new BaseResponseVo<>(400, "请求参数错误:" + StringUtils.join(messages, ";"));
    }

    @ExceptionHandler({BusinessException.class})
    public BaseResponseVo<Object> businessExceptionHandler(BusinessException ex) {
        BaseResponseVo<Object> rspVo = new BaseResponseVo<Object>();
        rspVo.setCode(ex.getCode());
        rspVo.setMsg(ex.getMessage());
        return rspVo;
    }
}
