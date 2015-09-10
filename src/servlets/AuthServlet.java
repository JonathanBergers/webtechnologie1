package servlets;

import model.*;
import util.Resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jonathan on 2-9-15.
 */

@WebServlet(name = "auth", urlPatterns = "/Login")
public class AuthServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        // gegevens
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // geen gegevens ingevuld
        if(username.isEmpty() || password.isEmpty()){
            request.getRequestDispatcher(Resources.PAGE_FOUTE_LOGIN).forward(request, response);
        }



        Model m = (Model) getServletContext().getAttribute(Resources.MODEL);


        if(m.verifyUser(username, password)){

            // haal user op
            User u = m.getUserByName(username);

            // maak een sessie aan, waaraan de auth filter kan zien of de gebruiker is ingelogd
            HttpSession session = request.getSession(false);

            // als er al een sessie is
            if(session != null){

                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute(Resources.SESSION_USER, u);

            m.addUser(u);

            // doorsturen naar de pagina die bij de user hoort
            // bij forward wordt de originele methode gebruikt, de post wordt dus aangeroepen.
            if(u instanceof Huurder){
                response.sendRedirect(Resources.PAGE_HUURDER_MAIN);

            }else if(u instanceof Verhuurder){
                getServletContext().getRequestDispatcher(Resources.PAGE_VERHUURDER_MAIN).forward(request, response);

            }else if(u instanceof Beheerder){

                // java overhead code to get a cookie

                for(Cookie c: request.getCookies()){
                    // cookie already exists
                    if(c.getName().equals(Resources.BEHEERDER_COOKIE)){

                        response.sendRedirect(Resources.PAGE_BEHEERDER_MAIN);
                        return;
                    }
                }


                // maak cookie voor beheerder
                Cookie cookie = new Cookie(Resources.BEHEERDER_COOKIE,  "0");
                response.addCookie(cookie);

                response.sendRedirect(Resources.PAGE_BEHEERDER_MAIN);
                //getServletContext().getRequestDispatcher(Resources.PAGE_BEHEERDER_MAIN).forward(request, response);

            }

        }else{
            request.getRequestDispatcher(Resources.PAGE_FOUTE_LOGIN).forward(request, response);
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.html");
    }



}

