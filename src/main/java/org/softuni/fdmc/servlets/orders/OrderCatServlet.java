package org.softuni.fdmc.servlets.orders;

import org.softuni.fdmc.data.models.Cat;
import org.softuni.fdmc.data.models.Order;
import org.softuni.fdmc.data.models.User;
import org.softuni.fdmc.data.repos.CatRepository;
import org.softuni.fdmc.data.repos.OrderRepository;
import org.softuni.fdmc.data.repos.UserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders/order")
public final class OrderCatServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final User orderedBy = ((UserRepository) this.getServletContext().getAttribute("users"))
                .getByUsername((String) req.getSession().getAttribute("username"));

        final Cat cat = ((CatRepository) this.getServletContext().getAttribute("cats"))
                .getByName(req.getParameter("catName"));

        if (orderedBy == null || cat == null) {
            resp.sendRedirect("/");
            return;
        }

        final Order order = new Order(orderedBy, cat);

        ((OrderRepository) this.getServletContext().getAttribute("orders")).addOrder(order);

        if (orderedBy.isAdmin()) {
            resp.sendRedirect("/orders/all");
        } else {
            resp.sendRedirect("/cats/all");
        }
    }
}
