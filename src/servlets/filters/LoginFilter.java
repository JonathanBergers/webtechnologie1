package servlets.filters;

import util.Resources;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jonathan on 7-9-15.
 *
 * this filter checks wether the user is logged in.
 * if he is the user gets redirected to the page he has access to
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = Resources.LOGIN_FILTER)
public class LoginFilter extends KamerverhuurFilter {

    private static final String REDIRECT_REASON = "U bent ingelogd";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(userLoggedIn(request)){





        }else{
            chain.doFilter(request, response);
        }
    }









}
