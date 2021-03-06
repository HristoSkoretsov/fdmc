package org.softuni.fdmc.servlets.cats;

import org.softuni.fdmc.data.models.Cat;
import org.softuni.fdmc.data.models.User;
import org.softuni.fdmc.data.repos.CatRepository;
import org.softuni.fdmc.data.repos.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cats/create")
public final class CatCreateServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        final User currentUser = this.getCurrentUser(req);

        if (currentUser == null || !currentUser.isAdmin()) {
            resp.sendRedirect("/");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/cats/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final User creator = this.getCurrentUser(req);

        if (creator == null || !creator.isAdmin()) {
            resp.sendRedirect("/");
            return;
        }

        if (!this.isRequestValid(req)) {
            resp.sendRedirect("/cats/create");
            return;
        }

        final Cat cat = new Cat(
                req.getParameter("name"),
                req.getParameter("breed"),
                req.getParameter("color"),
                Integer.parseInt(req.getParameter("legs")),
                creator);

        final boolean isAdded = ((CatRepository) this.getServletContext().getAttribute("cats")).addCat(cat);

        if (isAdded) {
            resp.sendRedirect("/cats/profile?catName=" + cat.getName());
        } else {
            resp.sendRedirect("/");
        }
    }

    private User getCurrentUser(final HttpServletRequest req) {
        return ((UserRepository) this.getServletContext().getAttribute("users"))
                .getByUsername((String) req.getSession().getAttribute("username"));
    }

    private boolean isRequestValid(final HttpServletRequest req) {
        return req.getParameter("name") != null && !req.getParameter("name").isEmpty()
                && req.getParameter("breed") != null && !req.getParameter("breed").isEmpty()
                && req.getParameter("color") != null && !req.getParameter("color").isEmpty()
                && req.getParameter("legs") != null && req.getParameter("legs").matches("\\d+");
    }
}
