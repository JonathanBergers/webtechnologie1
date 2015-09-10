package servlets;

import model.Kamer;
import model.Model;
import model.User;
import model.Verhuurder;
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
 * Created by falco on 10-9-15.
 */
@WebServlet(name = "VerhuurderServlet", urlPatterns = Resources.PAGE_VERHUURDER_MAIN)
public class VerhuurderServlet extends HttpServlet {

    private Model m;


    @Override
    public void init() throws ServletException {
        super.init();
        m = (Model) getServletContext().getAttribute(Resources.MODEL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String htmlRespone = "<html>";
        Verhuurder verhuurder = (Verhuurder) req.getSession(false).getAttribute(Resources.SESSION_USER);



        String sGrootte = req.getParameter("kamerGrootte");
        //check of de parameter is doorgegeven, dit is niet het geval als de verhuurder geen kamer heeft toegevoegd
        if(sGrootte != null){
            if(!sGrootte.isEmpty()){
                int grootte = Integer.parseInt(sGrootte);
                String sHuurprijs = req.getParameter("kamerPrijs");
                if(!sHuurprijs.isEmpty()){
                    int huurprijs = Integer.parseInt(sHuurprijs);
                    String plaats = req.getParameter("kamerPlaats");
                    if(!plaats.isEmpty()){
                        m.addRoom(new Kamer(huurprijs, grootte, plaats, verhuurder));
                        htmlRespone += "<h1>Kamer is toegevoegd!</h1><br><br>";
                    }
                }
            }
        }


        htmlRespone = addRoomOverview(htmlRespone, verhuurder, m);
        htmlRespone = addRoomForm(htmlRespone);


        //send response
        htmlRespone += "</html>";
        out.println(htmlRespone);


    }
    private String addRoomForm(String htmlRespone){
        htmlRespone += "<h1>Voeg hier een kamer toe</h1><br><br>";
        htmlRespone += "<form class=\"form\" method=\"post\" action="+Resources.PAGE_VERHUURDER_MAIN+" id=\"kamerToevoegen\">";
        htmlRespone += "<label>Grootte van de kamer in vierkante meters</label><br>";
        htmlRespone += "<input type=\"number\" name=\"kamerGrootte\"><br>";
        htmlRespone += "<label>Plaats waar de kamer gelegen is</label><br>";
        htmlRespone += "<input type=\"text\" name=\"kamerPlaats\"><br>";
        htmlRespone += "<label>De huurprijs</label><br>";
        htmlRespone += "<input type=\"number\" name=\"kamerPrijs\"><br><br>";
        htmlRespone += "<input type=\"submit\" value=\"Voeg toe\"> ";
        htmlRespone += "</form>";
        return htmlRespone;
    }

    private String addRoomOverview(String htmlRespone, Verhuurder v, Model m){

        htmlRespone += "<br><br><h1>Gevonden Kamers</h1><br><p>";

        ArrayList<Kamer> rooms = (ArrayList<Kamer>) m.getRooms();

        for(Kamer k : rooms){
            if(k.hasVerhuurder(v)){
                htmlRespone += k.toString() + "<br>";
            }
        }

        htmlRespone += "</p><br>";
        return htmlRespone;
    }
}
