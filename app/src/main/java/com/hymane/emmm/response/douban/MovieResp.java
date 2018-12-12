package com.hymane.emmm.response.douban;

import java.util.List;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class MovieResp {
    public int count;
    public int start;
    public int total;
    public String title;
    public List<Subject> subjects;

    public static class Subject {
        public String id;
        public Rating rating;
        public String[] genres;
        public String title;
        public List<User> casts;
        public int collect_count;
        public String original_title;
        public String subtype;
        public List<User> directors;
        public String year;
        public Image images;
        public String alt;
    }

    public static class Rating {
        public int max;
        public double average;
        public int stars;
        public int min;

    }

    public static class Image {
        public String small;
        public String large;
        public String medium;
    }
}
