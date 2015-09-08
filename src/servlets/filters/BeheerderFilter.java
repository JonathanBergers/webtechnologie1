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
 */
@WebFilter(filterName = "BeheerderFilter", urlPatterns = Resources.BEHEER_FILTER)
public class BeheerderFilter extends KamerverhuurFilter {

    private static final String REDIRECT_REASON = "Alleen toegang voor beheerders";


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        if(!userIsBeheerder(request)){
            redirect(request, response, chain, Resources.PAGE_NO_ACCESS, REDIRECT_REASON);

        }else{
            //ga door
            chain.doFilter(request, response);

        }




    }



}
