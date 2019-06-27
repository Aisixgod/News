package com.sdau.news.beans;

import android.util.ArraySet;

public class User  {
    private int id;
    private String name;
    private String password;
    private String image;//头像
    private ArraySet<Feedback> myFeedback;
    private ArraySet<User> fans;
    private ArraySet<User> attention;
    private Collect myCollect;

    public User(String name, String password, String image, ArraySet<Feedback> myFeedback, ArraySet<User> fans, ArraySet<User> attention, Collect myCollect) {

        this.name = name;
        this.password = password;
        this.image = image;
        this.myFeedback = myFeedback;
        this.fans = fans;
        this.attention = attention;
        this.myCollect = myCollect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArraySet<Feedback> getMyFeedback() {
        return myFeedback;
    }

    public void setMyFeedback(ArraySet<Feedback> myFeedback) {
        this.myFeedback = myFeedback;
    }

    public ArraySet<User> getFans() {
        return fans;
    }

    public void setFans(ArraySet<User> fans) {
        this.fans = fans;
    }

    public ArraySet<User> getAttention() {
        return attention;
    }

    public void setAttention(ArraySet<User> attention) {
        this.attention = attention;
    }

    public Collect getMyCollect() {
        return myCollect;
    }

    public void setMyCollect(Collect myCollect) {
        this.myCollect = myCollect;
    }
}
