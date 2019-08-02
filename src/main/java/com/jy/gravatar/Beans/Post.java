package com.jy.gravatar.Beans;

import javax.persistence.*;

@Entity
@Table(name="Post_Data")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String posteddate;
    private String postpicture;
    private String postmessage;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(String posteddate) {
        this.posteddate = posteddate;
    }

    public String getPostpicture() {
        return postpicture;
    }

    public void setPostpicture(String postpicture) {
        this.postpicture = postpicture;
    }

    public String getPostmessage() {
        return postmessage;
    }

    public void setPostmessage(String postmessage) {
        this.postmessage = postmessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}