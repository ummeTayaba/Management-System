/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewares;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.DbUtil;

/**
 *
 * @author Shabab
 */
@WebFilter(urlPatterns = "/admin/user/*")
public class AdminFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //init instance vars
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;
        String array[] = rq.getRequestURI().split("/");
        if(array[array.length - 1].equals("month-stat") || rq.getSession().getAttribute(DbUtil.ADMIN_SESSION) != null)
        {
            chain.doFilter(request, response);
            return;
        }
        else
        {
            ((HttpServletResponse)response).sendRedirect("/HotelManagement/admin/login");
            return;
        }
    }

    @Override
    public void destroy() {
        //destroy instance vars
    }
    
}
