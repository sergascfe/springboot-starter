package com.ca.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override//初始化，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override//销毁方法，只调用一次
    public void destroy() {
        Filter.super.destroy();
    }
}
