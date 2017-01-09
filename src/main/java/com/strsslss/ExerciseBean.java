package com.strsslss;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by saul on 20-12-16.
 */

public class ExerciseBean implements Serializable {

    private String title;
    private long time;

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

    public String toString() {
        return title + "\n\n" + new Date(time).toString() + "\n\n<Extra info or whatever>";
    }
}
