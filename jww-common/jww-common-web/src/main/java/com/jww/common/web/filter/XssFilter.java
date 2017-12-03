package com.jww.common.web.filter;

import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 防XSS漏洞过滤器
 *
 * @author shadj
 * @date 2017/11/28 22:21
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    private String[] excludePaths = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init XssFilter");
        String excludePath = filterConfig.getInitParameter("excludePaths");
        if (StrUtil.isNotBlank(excludePath)) {
            excludePaths = excludePath.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("XssFilter.doFilter ...");
        if (excludePaths != null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String uri = request.getRequestURI();
            // 排除部分URL不做过滤。
            for (String str : excludePaths) {
                if (uri.indexOf(str) >= 0) {
                    log.info("该URL不作校验：" + uri);
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        XssHttpRequestWrapper xssHttpServletRequest = new XssHttpRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssHttpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
