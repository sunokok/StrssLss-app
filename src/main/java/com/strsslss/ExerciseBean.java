package com.strsslss;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public String toString() {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.ENGLISH);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        return title + "\n\n" + dateFormat.format(date) + "\n" + timeFormat.format(date) + "\n\n<Extra info or whatever>";
    }
}
