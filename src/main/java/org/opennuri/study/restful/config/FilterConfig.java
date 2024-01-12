package org.opennuri.study.restful.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@WebFilter(urlPatterns = "/*")
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
