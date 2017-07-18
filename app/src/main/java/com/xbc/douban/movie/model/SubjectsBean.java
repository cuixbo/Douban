package com.xbc.douban.movie.model;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class SubjectsBean {
    public RatingBean rating;
    public String title;
    public int collect_count;
    public String original_title;
    public String subtype;
    public String year;
    public ImagesBean images;
    public String alt;
    public String id;
    public List<String> genres;
    public List<CastsBean> casts;
    public List<DirectorsBean> directors;

    public static class RatingBean {
        public int max;
        public double average;
        public String stars;
        public int min;
    }

    public static class CastsBean {
        public String alt;
        public ImagesBean avatars;
        public String name;
        public String id;
    }

    public static class DirectorsBean {
        public String alt;
        public ImagesBean avatars;
        public String name;
        public String id;
    }

    public static class ImagesBean {
        public String small;
        public String large;
        public String medium;
    }
}
