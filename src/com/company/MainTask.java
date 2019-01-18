package com.company;

import java.util.ArrayList;

public class MainTask extends TaskInfo {

    private ArrayList<TaskInfo> mainTask;

    public MainTask(String taskName, Priority priority) {
        super(taskName, priority);
        mainTask = new ArrayList<>();
    }
}
