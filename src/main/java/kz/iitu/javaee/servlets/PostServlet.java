package kz.iitu.javaee.servlets;

import kz.iitu.javaee.beans.Comment;
import kz.iitu.javaee.beans.Post;
import kz.iitu.javaee.db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/post")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            username=(String) request.getSession(false).getAttribute("username");
        }

        int pId = Integer.parseInt(request.getParameter("pId"));
        Post post= DBConnection.getPost(pId);
        request.setAttribute("userPost", false);
        if (username != null) {
            if (username==post.getUsername()){
                request.setAttribute("userPost", true);
            }
        }
        List<Comment> postComments = DBConnection.getComments(pId);
        int likes=DBConnection.getPostLikes(pId);
        int dislikes=DBConnection.getPostDislikes(pId);

        request.setAttribute("post", post);
        request.setAttribute("comments", postComments);
        request.setAttribute("likes", likes);
        request.setAttribute("dislikes", dislikes);

        request.getRequestDispatcher("/post.jsp").forward(request, response);
    }

}
