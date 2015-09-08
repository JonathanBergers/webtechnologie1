package servlets;

import model.Huurder;
import model.User;

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
        String gebruikersnaam = req.getParameter("gebruikersnaamNieuw");
        String wachtwoord = req.getParameter("wachtwoordNieuw1");

        String htmlRespone = "<html>";


        if(!wachtwoord.equals( req.getParameter("wachtwoordNieuw2")) || wachtwoord.isEmpty()||  req.getParameter("wachtwoordNieuw2").isEmpty() || gebruikersnaam.isEmpty()){
            //fout bij het registreren
            htmlRespone += "<h1> Er ging iets fout </h1><br><br>";
            htmlRespone += "<p>Probeer het <a href=\"registreer.html\">opnieuw</a></p>";
        } else {
            //alles gaat goed

            ArrayList<User> u = (ArrayList<User>) getServletContext().getAttribute("users");
            if(u!=null){
                u.add(new Huurder(gebruikersnaam, wachtwoord));
            } else {
                System.out.println("users == null");
                getServletContext().setAttribute("users", u);
            }



            htmlRespone += "<h1> Geregistreerd </h1><br>";
            htmlRespone += "<p>Gebruikersnaam = " + gebruikersnaam +" <br> " + "Wachtwoord = " + wachtwoord + "<br><br>";
            htmlRespone += "Nu wil ik graag <a href=\"index.html\">inloggen</a></p>";
        }




        // return response
        htmlRespone += "</html>";
        out.println(htmlRespone);


    }
}
