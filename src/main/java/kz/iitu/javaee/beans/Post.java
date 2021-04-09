package kz.iitu.javaee.beans;

public class Post implements java.io.Serializable{
    private int pId;
    private String title;
    private String pContent;
    private String username;

    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitles(String title) {
        this.title = title;
    }
    public String getpContent() {
        return pContent;
    }
    public void setpContent(String pContent) {
        this.pContent = pContent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Post{" +
                "pId=" + pId +
                ", title='" + title + '\'' +
                ", pContent='" + pContent + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
