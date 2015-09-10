package servlets.filters;

import util.Resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by falco on 9-9-15.
 */

@WebServlet(name = "HuurderServlet", urlPatterns = Resources.PAGE_HUURDER_MAIN)
public class HuurderServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //pagina laden om kamers te zoeken

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        String htmlRespone = "<html>";

        htmlRespone = addSearch(htmlRespone);


        //send response
        htmlRespone += "</html>";
        out.println(htmlRespone);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // laat alle kamers zien

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int kamerGrootte = Integer.parseInt(req.getParameter("kamerGrootte"));
        int kamerHuurprijs = Integer.parseInt(req.getParameter("kamerPrijs"));
        String kamerPlaats = req.getParameter("kamerPlaats");


        String htmlRespone = "<html>";
        htmlRespone += "<h1>Hier komen straks alle kamers waar je op hebt gezocht</h1><br>";
        htmlRespone += "<p>maar voor nu laat ik even je ingevude zoek opdacht zien: <br>";
        htmlRespone += "Grootte = "+kamerGrootte+"<br>Huurprijs = "+kamerHuurprijs+"<br>Plaats = "+kamerPlaats+"</p><br>";

        htmlRespone += "<br><br>";
        htmlRespone = addSearch(htmlRespone);
        htmlRespone += "</html>";
        out.println(htmlRespone);


    }

    private String addSearch(String htmlRespone){
        htmlRespone += "<h1>Zoek hier naar kamers</h1><br><br>";
        htmlRespone += "<form class=\"form\" method=\"post\" action="+Resources.PAGE_HUURDER_MAIN+" id=\"zoeken\">";
        htmlRespone += "<label>Grootte van de kamer in vierkante meters</label><br>";
        htmlRespone += "<input type=\"number\" name=\"kamerGrootte\"><br>";
        htmlRespone += "<label>Plaats waar de kamer gelegen is</label><br>";
        htmlRespone += "<input type=\"text\" name=\"kamerPlaats\"><br>";
        htmlRespone += "<label>De maximale huurprijs</label><br>";
        htmlRespone += "<input type=\"number\" name=\"kamerPrijs\"><br><br>";
        htmlRespone += "<input type=\"submit\" value=\"Zoek\"> ";
        htmlRespone += "</form>";
        return htmlRespone;
    }
}
