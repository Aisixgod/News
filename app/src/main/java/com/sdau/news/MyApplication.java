package com.sdau.news;

import android.app.Application;

import com.sdau.news.beans.Result;

import java.util.ArrayList;

public class MyApplication extends Application {
    //news type constants
    public final String TOP = "top";
    public final String SOCIAL = "shehui";
    public final String NATIONAL = "guonei";
    public final String INTERNATIONAL = "guoji";
    public final String ENTERTAINMENT = "yule";
    public final String SPORT = "tiyu";
    public final String MILITARY = "junshi";
    public final String TECHNOLOGY = "keji";
    public final String ECONOMY = "caijing";
    public final String FASHION = "shishang";

    public ArrayList<String> type;
    public ArrayList<String> typeCN;
    public ArrayList<Result> resultArrayList;

    public ArrayList<Result> getResultArrayList() {
        return resultArrayList;
    }

    public void setResultArrayList(ArrayList<Result> resultArrayList) {
        this.resultArrayList = resultArrayList;
    }

    public MyApplication() {

        initTypes();
    }

    private void initTypes(){
      //  String[] types=getResources().getStringArray(R.array.types);
        String[] types={TOP,SOCIAL,NATIONAL,INTERNATIONAL,ENTERTAINMENT,SPORT,MILITARY,TECHNOLOGY,ECONOMY,FASHION};

        type=new ArrayList<String>();
        for(int i=0;i<types.length;i++){
            type.add(types[i]);
        }
        typeCN = new ArrayList<>();



        //types键值
        for (int i = 0; i < type.size(); i ++) {
            typeCN.add("热点");
            typeCN.add("社会");
            typeCN.add("国内");
            typeCN.add("国际");
            typeCN.add("娱乐");
            typeCN.add("体育");
            typeCN.add("军事");
            typeCN.add("科技");
            typeCN.add("财经");
            typeCN.add("时尚");

        }
    }


}
