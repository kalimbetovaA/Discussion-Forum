package kz.iitu.javaee.servlets;

import kz.iitu.javaee.beans.Comment;
import kz.iitu.javaee.beans.Post;
import kz.iitu.javaee.db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/writeComment")
public class CreateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=(String) request.getSession(false).getAttribute("username");

        Comment comment = new Comment();
        comment.setContent(request.getParameter("content"));
        comment.setUsername(username);
        int pId=Integer.parseInt(request.getParameter("pId"));
        comment.setpId(pId);
        int result = DBConnection.createComment(comment);

        response.sendRedirect("/post?pId="+pId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
