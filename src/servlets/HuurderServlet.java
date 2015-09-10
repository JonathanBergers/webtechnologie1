package servlets;

import model.Kamer;
import model.Model;
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

        int grootte;
        String sGrootte = req.getParameter("kamerGrootte");
        if(sGrootte.isEmpty()){
            grootte = -1;
        } else {
            grootte = Integer.parseInt(sGrootte);
            if(grootte<0){
                grootte = -1;
            }
        }

        int huurprijs;
        String sHuurprijs = req.getParameter("kamerPrijs");
        if(sHuurprijs.isEmpty()){
            huurprijs = -1;
        } else {
            huurprijs = Integer.parseInt(sHuurprijs);
            if(huurprijs<0){
                huurprijs = 0;
            }
        }

        String plaats = req.getParameter("kamerPlaats");


        String htmlRespone = "<html>";

        htmlRespone = addSearchResults(htmlRespone, grootte, huurprijs, plaats);

        htmlRespone += "<br><br>";
        htmlRespone = addSearch(htmlRespone);
        htmlRespone += "</html>";
        out.println(htmlRespone);


    }

    private String addSearch(String htmlRespone){
        htmlRespone += "<h1>Zoek hier naar kamers</h1><br><br>";
        htmlRespone += "<form class=\"form\" method=\"post\" action="+Resources.PAGE_HUURDER_MAIN+" id=\"zoeken\">";
        htmlRespone += "<label>Minimale grootte van de kamer in vierkante meters</label><br>";
        htmlRespone += "<input type=\"number\" name=\"kamerGrootte\"><br>";
        htmlRespone += "<label>Plaats waar de kamer gelegen is</label><br>";
        htmlRespone += "<input type=\"text\" name=\"kamerPlaats\"><br>";
        htmlRespone += "<label>De maximale huurprijs</label><br>";
        htmlRespone += "<input type=\"number\" name=\"kamerPrijs\"><br><br>";
        htmlRespone += "<input type=\"submit\" value=\"Zoek\"> ";
        htmlRespone += "</form>";
        return htmlRespone;
    }

    private String addSearchResults(String htmlRespone, int grootte, int huurprijs, String plaats ){
        Model m = (Model) getServletContext().getAttribute(Resources.MODEL);
        ArrayList<Kamer> rooms = (ArrayList<Kamer>) m.getRooms();

        htmlRespone += "<br><br><h1>Gevonden Kamers</h1><br><p>";

        for(Kamer k : rooms){
            if(grootte == -1 || k.hasSize(grootte)){
                if(huurprijs == -1 || k.canBeRented(huurprijs)){
                    if(plaats.isEmpty() || k.isLoacatedAt(plaats)){
                        htmlRespone += k.toString() + "<br>";
                    }
                }
            }
        }
        htmlRespone += "</p><br>";

        return htmlRespone;

    }
}
