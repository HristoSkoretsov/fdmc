package org.softuni.fdmc.listenInit;

import org.softuni.fdmc.data.repos.CatRepository;
import org.softuni.fdmc.data.repos.OrderRepository;
import org.softuni.fdmc.data.repos.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        sce.getServletContext().setAttribute("cats", new CatRepository());
        sce.getServletContext().setAttribute("users", new UserRepository());
        sce.getServletContext().setAttribute("orders", new OrderRepository());
    }
}