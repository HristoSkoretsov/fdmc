package org.softuni.fdmc.servlets.orders;

import org.softuni.fdmc.data.models.User;
import org.softuni.fdmc.data.repos.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders/all")
public final class OrdersAllServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final User user = ((UserRepository) this.getServletContext().getAttribute("users"))
                .getByUsername(req.getSession().getAttribute("username").toString());

        if (user == null || !user.isAdmin()) {
            resp.sendRedirect("/");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/orders/all.jsp").forward(req, resp);
    }
}
