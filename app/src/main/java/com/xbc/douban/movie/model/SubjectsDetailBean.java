package com.xbc.douban.movie.model;

import java.util.List;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class SubjectsDetailBean extends SubjectsBean {
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

    public static class SimplePhotoBean {
        public String id;//图片id	str	N/A	Y	Y	-
        public String alt;//图片展示页url	str	N/A	Y	Y	-
        public String icon;//图片地址，icon尺寸	str	N/A	Y	Y	-
        public String image;//图片地址，image尺寸	str	N/A	Y	Y	-
        public String thumb;//图片地址，thumb尺寸	str	N/A	Y	Y	-
        public String cover;//图片地址，cover尺寸	str
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
