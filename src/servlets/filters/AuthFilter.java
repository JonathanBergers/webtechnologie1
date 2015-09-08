package servlets.filters;

import util.Resources;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jonathan on 7-9-15.
 *
 * this filter checks wether the user is logged in.
 * if he is not the user gets redirected to the NoAccess page.
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter extends KamerverhuurFilter {

    private static final String REDIRECT_REASON = "U bent niet ingelogd";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(!userLoggedIn(request)){
            redirect(request, response, chain, Resources.PAGE_NO_ACCESS, REDIRECT_REASON);

        }else{


            chain.doFilter(request, response);
        }
    }









}
