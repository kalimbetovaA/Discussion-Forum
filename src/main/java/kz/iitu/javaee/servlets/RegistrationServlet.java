package kz.iitu.javaee.servlets;

import kz.iitu.javaee.db.DBConnection;
import kz.iitu.javaee.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        int userRegistered = DBConnection.createUser(user);

        if(userRegistered!=0)
        {
            request.setAttribute("message", "You were registered successfully! "+user.getUsername()+", please log in.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
