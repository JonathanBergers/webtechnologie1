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
        htmlRespone += "<h1>Zoek hier naar kamers</h1><br><br>";
        htmlRespone += "<form class=\"form\" method=\"post\" action=\"/Zoeken\" id=\"zoeken\">";
        htmlRespone += "<label>Groote van de kamer<br></label>";
        htmlRespone += "<input type=\"text\"> name=\"groote\">";
        htmlRespone += "";
        htmlRespone += "</form>";


        //send response
        htmlRespone += "</html>";
        out.println(htmlRespone);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
