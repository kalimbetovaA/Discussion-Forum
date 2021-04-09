<%@ page import="kz.iitu.javaee.db.DBConnection" %>
<%@ page import="kz.iitu.javaee.beans.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="message"><%=(request.getAttribute("message") == null) ? "" : request.getAttribute("message")%></p>

<h2>All Posts</h2>
<%
    if(session.getAttribute("username")!=null){
%>
<button><a href="writePost.jsp">Write your own post</a></button>
<%}

    List<Post> posts = DBConnection.getPosts();
    if(posts!=null){
        for(Post post : posts){
            int commentsNum = DBConnection.getComments(post.getpId()).size();
%>
        <div class="post">
            <h4><%=post.getUsername()%></h4>
            <h3><a href="post?pId=<%=post.getpId()%>"><%=post.getTitle()%></a></h3>
            <p><%=post.getpContent()%></p>
            <p><%=commentsNum%> comments</p>

            <%
                if(session.getAttribute("username")!=null){
                    if(session.getAttribute("username").equals(post.getUsername())){
            %>
            <button><a href="editPost.jsp?pId=<%=post.getpId()%>">Edit</a></button>
            <%
                    }
                }
            %>
        </div>

<%
        }
    }
%>
