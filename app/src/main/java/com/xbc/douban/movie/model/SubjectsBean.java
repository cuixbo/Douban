package com.xbc.douban.movie.model;

import com.xbc.douban.base.BaseResponse;

import java.util.List;

public class SubjectsBean extends BaseResponse {
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
    public List<CastsBean> directors;

    public String summary;
    public int ratings_count;

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
        public String type;
    }

    public static class ImagesBean {
        public String small;
        public String large;
        public String medium;
    }

    @Override
    public String toString() {
        return "SubjectsBean{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", collect_count=" + collect_count +
                ", original_title='" + original_title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }
}
