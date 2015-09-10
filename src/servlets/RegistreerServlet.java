package servlets;

import com.sun.deploy.net.HttpResponse;
import model.*;
import util.Resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by falco on 8-9-15.
 */
@WebServlet(name = "registreer", urlPatterns = "/Registreer")
public class RegistreerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Model m = (Model) getServletContext().getAttribute(Resources.MODEL);
        String username = req.getParameter("gebruikersnaamNieuw");
        String password = req.getParameter("wachtwoordNieuw1");
        String secondPassword = req.getParameter("wachtwoordNieuw2");


        String htmlRespone = "<html>";




        // foute invoer, of geen gelijk wachtwoord
        if(!password.equals(secondPassword)) {

            out.write(htmlRespone += makeFalseRegResponse());
            out.flush();
            return;

        }
        if(password.isEmpty()) {
            out.write(htmlRespone += makeFalseRegResponse());
            out.flush();
            return;
        }
        if(secondPassword.isEmpty()) {
            out.write(htmlRespone += makeFalseRegResponse());
            out.flush();
            return;
        }

        if(username.isEmpty()){
            out.write(htmlRespone += makeFalseRegResponse());
            out.flush();
            return;

        }



        //alles gaat goed
        User user;
        if(req.getParameter("rol").equals("huurder")){
            user = new Huurder(username, password);
        } else if(req.getParameter("rol").equals("verhuurder")){
            user = new Verhuurder(username, password);
        } else {
            user = new Beheerder(username, password);
        }

        m.addUser(user);

        req.getRequestDispatcher(Resources.PAGE_MAIN).forward(req,resp);

    }










    private String makeFalseRegResponse() {




        String htmlResponse = "<h1> Er ging iets fout </h1><br><br>";
        htmlResponse += "<p>Probeer het <a href=\"registreer.html\">opnieuw</a></p>";
        // return response
        htmlResponse += "</html>";

        return htmlResponse;



    }
}