package com.sdau.news.utils;

import com.sdau.news.beans.Feedback;
import com.sdau.news.beans.News;

import java.util.List;

public interface ISQLiteOperation {
    String DB_NAME = "news.db";
    String TABLE_NAME_COLLECT = "collect";
    String TABLE_NAME_HISTORY="history";
    String TABLE_NAME_FEEDBACK="FEEDBACK";

    /**
     * 添加喜欢的项目到SQLite
     * @param data 喜欢的数据
     * @return 是否添加成功
     */
    boolean addCollect(News data);

    /**
     * 从SQLite中删除喜欢的项目
     * @param data 已喜欢的项目
     * @return 是否删除成功
     */
    boolean deleteCollect(News data);

    void createTable();

    /**
     *
     * @return 数据库中所有喜欢表的条目
     */
    List<News> queryCollectAll();

    /**
     * 查询是否已收藏该新闻
     * @return
     */
    News queryCollectNews(News data);

    boolean deleteALLHistory();

    boolean addHistory(News data);

    List<News> queryHistoryAll();

    News queryHistoryByNews(News data);

    boolean addFeedback(Feedback feedback);

    boolean deleteHistory(News data);

    boolean deleteFeedback(Feedback feedback);

    boolean addFeedbackSupport(Feedback feedback);

    boolean addFeedbackStamp(Feedback feedback);

    int queryFeedbackByNews(News data);

    List<Feedback> queryFeedbackAll();




}
