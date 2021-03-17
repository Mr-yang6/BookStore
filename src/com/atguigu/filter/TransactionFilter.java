package com.atguigu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Mr.yang
 * @create 2020-11-16 21:29
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse );
            //JDBCUtils.commitAndClose();
        } catch (Exception e) {
            //e.printStackTrace();
        //} catch (ServletException e) {
            //e.printStackTrace();
            //JDBCUtils.rollbackAndClose();
        }
    }

    @Override
    public void destroy() {

    }
}
