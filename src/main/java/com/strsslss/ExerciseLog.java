package com.strsslss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saul on 20-12-16.
 */

public class ExerciseLog implements Serializable {

    private List<ExerciseBean> log;
    private String filePath;

    public ExerciseLog(String absolutePath) {
        this.filePath = absolutePath + "/exercise_log";
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ExerciseLog readObject = (ExerciseLog) objectInputStream.readObject();
            this.log = readObject.getLog();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            this.log = new ArrayList<>();
            //fillLog();
            writeLog();
        }
    }

    public void addExercise(ExerciseBean bean) {
        log.add(bean);
        writeLog();
    }

    private void writeLog() {
        try {
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillLog() {
        for (int i = 0; i < 15; i++) {
            log.add(new ExerciseBean("Item " + i, (int) (Math.random() * 10)));
        }
    }

    public List<ExerciseBean> getLog() {
        return log;
    }
}
