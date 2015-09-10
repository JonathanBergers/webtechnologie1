package servlets;

import model.*;
import util.Resources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 9-9-15.
 * initiates the application
 */
@WebListener
public class ContextListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {



        System.out.println("ServletContextListener init()");

        List<User> users = new ArrayList<>();
        users.add(new Huurder("huurder", "pass"));
        User verhuurder = new Verhuurder("verhuurder", "pass");
        users.add(verhuurder);
        users.add(new Beheerder("beheerder", "pass"));

        List<Kamer> rooms = new ArrayList<>();
        rooms.add(new Kamer(100,12, "Deventer", (Verhuurder) verhuurder));

        Model model = new Model(users, rooms);

        servletContextEvent.getServletContext().setAttribute(Resources.MODEL, model);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }




}