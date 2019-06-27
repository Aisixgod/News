package com.sdau.news.beans;


public class Feedback  {
    private String substance;
    private int support;
    private int stamp;
    private User user;
    private News feedbackNews;

    public Feedback(){}
    public Feedback(String substance, int support, int stamp, User user, News feedbackNews) {
        this.substance = substance;
        this.support = support;
        this.stamp = stamp;
        this.user = user;
        this.feedbackNews = feedbackNews;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public int getStamp() {
        return stamp;
    }

    public void setStamp(int stamp) {
        this.stamp = stamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getFeedbackNews() {
        return feedbackNews;
    }

    public void setFeedbackNews(News feedbackNews) {
        this.feedbackNews = feedbackNews;
    }
}
