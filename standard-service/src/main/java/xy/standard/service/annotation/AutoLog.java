package xy.standard.service.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLog {
    /**
     * 日志类型：0-其他，1-增加，2-删除，3-修改，4-查询
     */
    int logType() default 0;

    /**
     * 操作内容
     */
    String logContent() default "";
}
