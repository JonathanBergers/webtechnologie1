package servlets;

import model.Beheerder;
import model.Huurder;
import model.User;
import model.Verhuurder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.Resources;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jonathan on 2-9-15.
 */

@WebServlet(name = "auth", urlPatterns = "/Login")
public class AuthServlet extends javax.servlet.http.HttpServlet {


    private List<User> users = new ArrayList<User>();


   
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            readUsers();
            getServletContext().setAttribute("users", users);

        } catch (Exception e) {

            //TODO catch

            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO login afhandelen



        // gegevens
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // geen gegevens ingevuld
        if(username == null || password == null){
            response.sendRedirect(Resources.PAGE_FOUTE_LOGIN);
        }

        if(validUser(username, password)){

            // haal user op
            User u = getUser(username, password);

            // maak een sessie aan, waaraan de auth filter kan zien of de gebruiker is ingelogd
            HttpSession session = request.getSession(true);
            session.setAttribute(Resources.SESSION_USER, u);

            System.out.println(session.getAttribute(Resources.SESSION_USER));


            // doorsturen naar de pagina die bij de user hoort
            // bij forward wordt de originele methode gebruikt, de post wordt dus aangeroepen.

            if(u instanceof Huurder){
                getServletContext().getRequestDispatcher(Resources.PAGE_HUURDER_MAIN).forward(request, response);

            }else if(u instanceof Verhuurder){
                getServletContext().getRequestDispatcher(Resources.PAGE_VERHUURDER_MAIN).forward(request, response);

            }else if(u instanceof Beheerder){
                response.sendRedirect(Resources.PAGE_BEHEERDER_MAIN);
                //getServletContext().getRequestDispatcher(Resources.PAGE_BEHEERDER_MAIN).forward(request, response);

            }

        }else{
            response.sendRedirect(Resources.PAGE_FOUTE_LOGIN);
        }

    }






    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.html");
    }


    /**Leest het users xml bestand in
     *
     * @throws Exception
     */
    private void readUsers() throws Exception {


    	
    	//FUCKING ECLIPSE CANT READ SHIT
    	
    	
    	users.add(new Beheerder("jonathan", "niks"));
    	users.add(new Verhuurder("verhuurder", "pass"));
    	users.add(new Huurder("huurder", "pass"));
    	
    	return;
    	
    	// FUCKING ECLIPSE
//    	
//        File f = new File(Resources.PATH_ACCOUNTS);
//        assert f != null : "file is null";
//        
//        System.out.println(f);
//
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(f);
//        doc.getDocumentElement().normalize();
//
//        Element rootElement = doc.getDocumentElement();
//
//        NodeList l = rootElement.getElementsByTagName("user");
//
//
//        for (int i = 0; i < l.getLength(); i++) {
//
//            Element n = (Element) l.item(i);
//
//            String username = n.getElementsByTagName("name").item(0).getTextContent();
//            String password = n.getElementsByTagName("password").item(0).getTextContent();
//            String role = n.getElementsByTagName("role").item(0).getTextContent();
//
//            System.out.println("name = " + username);
//            System.out.println("pass = " + password);
//            System.out.println("role = " + role);
//
//
//
//            if(role.equals("huurder")){
//
//                users.add(new Huurder(username, password));
//
//            }else if(role.equals("verhuurder")){
//                users.add(new Verhuurder(username, password));
//
//            }else if(role.equals("beheerder")){
//
//                users.add(new Beheerder(username, password));
//
//            }else{
//                throw new Exception("invalid role in xml file");
//            }
//
//        }
//
//
//
//
//
//

    }

    public boolean validUser(String username, String password){


        for(User u : users){

            if(u.hasNameAndPass(username, password)){

                return true;
            }

        }
        return false;

    }


    public User getUser(String username, String password){

        assert validUser(username, password);
        for(User u : users){

            if(u.hasNameAndPass(username, password)){

                return u;
            }

        }


        return null;
    }



}
