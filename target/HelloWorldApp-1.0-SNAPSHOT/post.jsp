<%@ page import="kz.iitu.javaee.beans.Post" %>
<%@ page import="kz.iitu.javaee.db.DBConnection" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.javaee.beans.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Post post = (Post) request.getAttribute("post");
    int likes = (int) request.getAttribute("likes");
    int dislikes = (int) request.getAttribute("dislikes");
%>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title><%=post.getTitle()%></title>
    </head>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="post">
    <h4><%=post.getUsername()%></h4>
    <h3><%=post.getTitle()%></h3>
    <p><%=post.getpContent()%></p>
    <p><span><%=likes%> likes</span>/<span><%=dislikes%> dislikes</span></p>
    <%
        List<Comment> comments = (List<Comment>) request.getAttribute("comments");
        if(comments!=null){
            for(Comment comment : comments){
                int clikes = DBConnection.getCommentLikes(comment.getcId());
                int cdislikes = DBConnection.getCommentDislikes(comment.getcId());
    %>
    <div class="comment">
        <h4><%=comment.getUsername()%></h4>
        <p><%=comment.getContent()%></p>
        <p><span><%=clikes%> likes</span>/<span><%=cdislikes%> dislikes</span></p>
    </div>
    <%
            }
        }
    %>
    <%
        if(session.getAttribute("username")!=null){
    %>
    <form action="/writeComment" method="POST">
        <textarea name="content"></textarea>
        <input type="hidden" name="pId" value="<%=post.getpId()%>">
        <button>Write</button>
    </form>
    <%
            if(session.getAttribute("username").equals(post.getUsername())){
    %>
        <button><a href="editPost.jsp?pId=<%=post.getpId()%>">Edit</a></button>
    <%
            }
        }
    %>
</div>
</body>
</html>
