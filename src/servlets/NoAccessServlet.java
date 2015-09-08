package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jonathan on 7-9-15.
 */
@WebServlet(name = "NoAccessServlet")
public class NoAccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String reason = request.getParameter("reason");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<HTML>");

        out.println("<HEAD><TITLE>ALL USERS</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("<H1>"+ "U heeft geen toegang" + "</H1>");
        out.println("<H2> Rede: " + reason + "</H2>");

        out.println("</BODY></HTML>");

        out.flush();

    }
}
