package servlets.filters;

import util.Resources;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jonathan on 7-9-15.
 */
@WebFilter(filterName = "VerhuurderFilter", urlPatterns = Resources.VERHUURDER_FILTER)
public class VerhuurderFilter extends KamerverhuurFilter {

    private static final String REDIRECT_REASON = "Alleen toegang voor verhuurders";


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(userIsHuurder(request)){
            redirect(request, response, chain, Resources.PAGE_NO_ACCESS, REDIRECT_REASON);

        }else{
            chain.doFilter(request, response);
        }



    }



}
