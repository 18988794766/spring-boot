package xy.standard.service.annotation;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class AutoLogAspect {
    @Autowired
    private HttpServletRequest request;

    /**
     * annotation用于匹配持有指定注解的方法
     */
    @Pointcut("@annotation(xy.standard.service.annotation.AutoLog)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        this.printLog(joinPoint);
        return result;
    }

    /**
     * 信息打印
     * @param joinPoint
     */
    private void printLog(ProceedingJoinPoint joinPoint) {
        //获取注解信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AutoLog autoLog = method.getAnnotation(AutoLog.class);
        log.info("logType = " + autoLog.logType());
        log.info("logContent = " + autoLog.logContent());

        //获取类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        log.info("className = " + className);
        log.info("methodName = " + methodName);

        //获取请求参数
        Object[] args = joinPoint.getArgs();
        log.info("args = " + JSONObject.toJSONString(args));

        //获取请求url
        String url = request.getRequestURL().toString();
        log.info("url = " + url);
    }
}
