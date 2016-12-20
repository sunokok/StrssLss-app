package com.strsslss;

import java.io.Serializable;

/**
 * Created by saul on 20-12-16.
 */

public class ExerciseBean implements Serializable {

    String title;
    long time;

    public ExerciseBean(String title, long time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }
}
