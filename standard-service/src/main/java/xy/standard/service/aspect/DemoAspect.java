package xy.standard.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DemoAspect {
    /**
     * 第一个*表示返回值类型（所有类型）
     * 两个..表示当前报和当前包的子包
     * 第二个*表示类名（所有类）
     * 第三个*表示方法名（所有方法名）
     * 最后(..)表示方法的参数
     */
    @Pointcut("execution(* xy.standard.service.service1.*.*(..))")
    public void pointCut() {}

    /**
     * 前置通知
     * 在一个方法执行之前，执行通知
     */
    @Before("pointCut()")
    public void beforeAdvice() {
        log.info("这是before打印");
    }

    /**
     * 后置通知
     * 在一个方法执行之后，不考虑其结果，执行通知
     */
    @After("pointCut()")
    public void afterAdvice() {
        log.info("这是after打印");
    }

    /**
     * 环绕通知
     * 环绕通知的返回会替换原来函数的返回
     * 在建议方法调用之前和之后，执行通知
     * 执行顺序：proceed方法前->前置通知->方法执行->proceed方法后->后置通知->返回后通知
     */
    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        try {
            log.info("这是around打印前");
            joinPoint.proceed(); //通过ProceedingJoinPoint的proceed()方法调用被通知的方法
            log.info("这是around打印后");
            return "这个会替代原来方法的返回";
        } catch (Throwable e) {
            log.info(e.toString());
            return "这个会替代原来方法的返回";
        }
    }

    /**
     * 正常返回后通知：在后置通知之后
     * 在一个方法执行之后，只有在方法成功完成时，才能执行通知
     * 如果有环绕通知，则这个returning会是环绕通知的返回
     */
    @AfterReturning(pointcut = "pointCut()", returning = "retVal")
    public void afterReturnAdvice(Object retVal) {
        log.info("这是after return打印" + retVal.toString());
    }

    /**
     * 抛出异常后通知：在后置通知之后
     * AfterThrowing不能像catch一样处理异常，异常还是会向上抛出
     * 在一个方法执行之后，只有在方法退出抛出异常时，才能执行通知
     * 如果有像上面的around通知捕获异常，则不会触发这个通知，而会触发正常返回后通知
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterThrowingAdvice(IllegalArgumentException ex) {
        log.info("这是after throwing打印" + ex.toString());
    }
}
