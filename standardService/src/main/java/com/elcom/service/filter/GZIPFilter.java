package com.elcom.service.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anhdv.ho
 */
public class GZIPFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if( req instanceof HttpServletRequest) {
            
            HttpServletRequest request = (HttpServletRequest)req;
            request.setCharacterEncoding("UTF-8");
            HttpServletResponse response = (HttpServletResponse)res;
            
            String ae = request.getHeader("accept-encoding");
            if( ae!=null && ae.contains("gzip") ) {
                GZIPResponseWrapper wrapperResponse = new GZIPResponseWrapper(response);
                chain.doFilter(req, wrapperResponse);
                wrapperResponse.finishResponse();
                return;
            }
            chain.doFilter(req, res);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) {
        //System.out.println("GZIPFilter.init()...............................");
    }
    
    @Override
    public void destroy() {
        //System.out.println("GZIPFilter.destroy()...............................");
    }
}
