package servlets;

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
        String gebruikersnaam = req.getParameter("gebruikersnaamNieuw");
        String wachtwoord = req.getParameter("wachtwoordNieuw1");

        String htmlRespone = "<html>";


        if(!wachtwoord.equals( req.getParameter("wachtwoordNieuw2")) || wachtwoord.isEmpty()||  req.getParameter("wachtwoordNieuw2").isEmpty() || gebruikersnaam.isEmpty()){
            //fout bij het registreren
            htmlRespone += "<h1> Er ging iets fout </h1><br><br>";
            htmlRespone += "<p>Probeer het <a href=\"registreer.html\">opnieuw</a></p>";
        } else {
            //alles gaat goed
            User user;
            if(req.getParameter("rol").equals("huurder")){
                user = new Huurder(gebruikersnaam, wachtwoord);
            } else if(req.getParameter("rol").equals("verhuurder")){
                user = new Verhuurder(gebruikersnaam, wachtwoord);
            } else {
                user = new Beheerder(gebruikersnaam, wachtwoord);
            }

            m.addUser(user);


            htmlRespone += "<h1> Geregistreerd </h1><br>";
            htmlRespone += "<p>Gebruikersnaam = " + gebruikersnaam +" <br> " + "Wachtwoord = " + wachtwoord + "<br><br>";
            htmlRespone += "Nu wil ik graag <a href=\"index.html\">inloggen</a></p>";
        }




        // return response
        htmlRespone += "</html>";
        out.println(htmlRespone);


    }
}