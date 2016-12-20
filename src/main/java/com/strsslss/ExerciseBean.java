package com.strsslss;

import java.io.Serializable;

/**
 * Created by saul on 20-12-16.
 */

public class ExerciseBean implements Serializable {

    String title;
    long time;
    double rating;

    public ExerciseBean(String title, int rating) {
        this.title = title;
        this.time = System.currentTimeMillis();
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }
}
