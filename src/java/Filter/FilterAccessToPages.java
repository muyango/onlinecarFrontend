/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author klion
 */
@WebFilter(filterName = "FilterAccessToPages", urlPatterns = {"/*"}, initParams = {
    @WebInitParam(name = "Name", value = "Value")})
public class FilterAccessToPages implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public FilterAccessToPages() {
    }

   @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();
        if (session.getAttribute("customerSession") == null) {
            if (url.indexOf("CARDASHBOARD.xhtml") >= 0) {
                res.sendRedirect(req.getServletContext().getContextPath() + "/faces/LOGINPAGE.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (url.indexOf("LOGINPAGE.xhtml") >= 0) {
                res.sendRedirect(req.getServletContext().getContextPath() + "/faces/CARDASHBOARD.xhtml");
            } else if (url.indexOf("logout.xhtml") >= 0) {
                req.getSession().removeAttribute("customerSession");
                res.sendRedirect(req.getServletContext().getContextPath() + "/faces/LOGINPAGE.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }

    }

    public void destroy() {
    }
}
