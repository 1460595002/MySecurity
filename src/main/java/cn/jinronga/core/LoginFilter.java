package cn.jinronga.core;


import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginFilter
 * @Author 郭金荣
 * @Date 2021/3/16 20:41
 * @Description LoginFilter
 * @Version 1.0
 */
public class LoginFilter implements Filter {
    private String[] splitUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String ignore = filterConfig.getInitParameter("ignore");
            splitUrl = ignore.split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //判断请求的uri是否允许通过
        boolean ignore = isIgnore(request);
        if (ignore) {
            chain.doFilter(request, response);
        } else {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            HttpSession session = servletRequest.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                servletResponse.sendRedirect("/");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    public boolean isIgnore(ServletRequest request) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        for (String ignore : splitUrl) {
            if (servletRequest.getRequestURI().equals(ignore)) {
                return true;
            }
        }
        return false;
    }
}
