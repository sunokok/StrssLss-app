package com.strsslss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saul on 20-12-16.
 */

public class ExerciseLog implements Serializable {

    List<ExerciseBean> log;

    public ExerciseLog() {
        this.log = new ArrayList<>();
    }

    public List<ExerciseBean> getLog() {
        return log;
    }
}
