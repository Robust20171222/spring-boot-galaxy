package com.galaxy.concurrency.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wangpeng
 * Date: 2018/10/22
 * Time: 09:12
 */
public class LogCostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.err.println(this.getClass().getName() + "启动了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        System.err.println(this.getClass().getName() + "过滤器拦截请求");
    }

    @Override
    public void destroy() {
        System.err.println(this.getClass().getName() + "销毁了");
    }
}
