package xy.standard.service.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor配置
 * Author: HuangLibin 2019/09/11
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     *进入拦截器，执行controller之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("这是preHandle");
        return true;
    }

    /**
     * 执行完controller,在controller的return ModelAndView之前（页面渲染前）执行
     * 可以操控ModelAndView的值
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("这是postHandle");
    }

    /**
     * 在controller的return ModelAndView之后执行，但在filter返回给客户端之前执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("这是afterCompletion");
    }
}
