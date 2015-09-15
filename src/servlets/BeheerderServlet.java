package servlets;

import model.User;
import util.Resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jonathan on 7-9-15.
 * Pagina van de beheerder
 * Geeft de gebruikers weer van het systeem.
 * Ook wordt de aantal keren weergegeven dat deze pagina is bezocht.
 *
 *
 */
@WebServlet(name = "BeheerderServlet", urlPatterns = Resources.PAGE_BEHEERDER_MAIN)
public class BeheerderServlet extends HttpServlet {

//
//    private int timesVisited = 0;

    @Override
    public void init() throws ServletException {
        super.init();

    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Cookie ck = null;
        // cookie ophalen
        for(Cookie c: request.getCookies()){
            // cookie already exists
            if(c.getName().equals(Resources.BEHEERDER_COOKIE)){
            	c.setValue("" + (Integer.parseInt(c.getValue()) + 1));
            	response.addCookie(c);
            	break;                       
            }
        }





//
//        timesVisited ++;
        // users ophalen uit de context.
        List<User> users = (List<User>) getServletContext().getAttribute(Resources.APPLICATION_USERS);

        assert users != null: "geen users ingelezen";
        assert !users.isEmpty(): "geen gebruikers in het systeem, maar toch op deze pagina ??";



        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<HTML>");

        out.println("<HEAD><TITLE>ALL USERS</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("<H1>"+ "U heeft deze pagina " + ck.getValue() + " keer bezocht" + "</H1>");
        out.println("<H2> ALL USERS </H2>");

        for(User u: users){
            out.println("<Li>"+ u + "</Li>");

        }
        out.println("</BODY></HTML>");

        out.flush();



    }
}
