package com.elcom.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//import com.elcom.sharedbiz.cache.BaseStationDataThreadCache;
public class AppInit implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        try {

            // Data về BaseStation được cache lại (memcached), refresh lại theo 1 khoảng thời gian nhất định được cấu hình.
            //new Thread(new BaseStationDataThreadCache()).start();
            this.context = fConfig.getServletContext();

            this.context.log("CacheInit initialized!");

        } catch (Exception e) {
            this.context.log("CacheInit.Ex: " + e.toString());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
