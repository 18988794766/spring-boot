package xy.standard.service.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter配置
 * Author: HuangLibin 2019/09/11
 */
@Slf4j
@Component
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("创建filter");
    }

    /**
     * 可以用于修改请求（request）和响应（response）的内容
     * 过滤器链的处理流程是：
     *  1、进入到一个过滤器的doFitler方法中，处理一些逻辑，
     *  2、然后调用chain.doFilter(request, httpServletResponse);进入到过滤器链的下一个过滤器的doFilter方法中
     *  3、当在过滤器链上最后一个过滤器的doFilter方法中调用chain.doFilter(request, httpServletResponse)时
     *  4、将会把请求转发到Servlet中，再分配到对应的Controller的方法中
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter执行前");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /*if (request.getRequestURL().toString().contains("log")) {
            request.getRequestDispatcher("/mock/aspect1").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }*/

        filterChain.doFilter(request, response);
        log.info("doFilter执行后");
    }

    @Override
    public void destroy() {
        log.info("销毁filter");
    }
}
