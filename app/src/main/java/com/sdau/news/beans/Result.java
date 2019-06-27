package com.sdau.news.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {
    private  int stat;
    private ArrayList<News> news;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Result{" +
                "stat=" + stat +
                ", news=" + news +
                '}';
    }
}
