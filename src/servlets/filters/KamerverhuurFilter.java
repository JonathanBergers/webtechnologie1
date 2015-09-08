package servlets.filters;

import model.Beheerder;
import model.Huurder;
import model.User;
import model.Verhuurder;
import util.Resources;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jonathan on 7-9-15.
 * Base filter for kamerverhuur
 *
 */
@WebFilter(filterName = Resources.PAGE_ROOT)
public class KamerverhuurFilter implements Filter {





    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }


    /**checks if the user is logged in.
     *
     * @param request
     * @return
     */
    protected boolean userLoggedIn(HttpServletRequest request) {

        if (request.getSession(false) != null) {
            if (request.getSession(false).getAttribute(Resources.SESSION_USER) != null) {

                return true;
            }
        }
        return false;

    }

    /**redirects the user to a given page, adding a header with the reason
     *
     * @param req
     * @param resp
     * @param chain
     * @param redirectPage the page the user needs to be redirected to
     * @param redirectReason the reason to be added to the header
     * @throws ServletException
     * @throws IOException
     */
    protected void redirect(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, final String redirectPage, final String redirectReason) throws ServletException, IOException{



        resp.addHeader("reason", redirectReason);

        if(!userLoggedIn(req)){

            req.getServletContext().getRequestDispatcher(redirectPage).forward(req, resp);
        }else {


            chain.doFilter(req, resp);
        }


    }


    /**checks if the logged in user is a huurder
     *
     * pre: user logged in
     *
     * @param request
     * @return
     */
    protected boolean userIsHuurder(HttpServletRequest request){

        assert userLoggedIn(request) : "user not logged in";

        User u  = (User) request.getSession(false).getAttribute(Resources.SESSION_USER);


        if(u instanceof Huurder){
            return true;
        }
        return false;

    }

    /**checks if the logged in user is a verhuurder
     *
     * pre: user logged in
     *
     * @param request
     * @return
     */
    protected boolean userIsVerhuurder(HttpServletRequest request){

        assert userLoggedIn(request) : "user not logged in";

        User u  = (User) request.getSession(false).getAttribute(Resources.SESSION_USER);


        if(u instanceof Verhuurder){
            return true;
        }
        return false;

    }

    /**checks if the logged in user is a huurder
     *
     * pre: user logged in
     *
     * @param request
     * @return
     */
    protected boolean userIsBeheerder(HttpServletRequest request){

        assert userLoggedIn(request) : "user not logged in";

        User u  = (User) request.getSession(false).getAttribute(Resources.SESSION_USER);


        if(u instanceof Beheerder){
            return true;
        }
        return false;

    }


}
