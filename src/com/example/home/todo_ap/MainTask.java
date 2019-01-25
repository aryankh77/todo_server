package com.example.home.todo_ap;

import java.util.ArrayList;

public class MainTask extends TaskInfo {
    private static final long serialVersionUID=1234;
    private ArrayList<TaskInfo> mainTask;

    public MainTask(String taskName, Priority priority) {
        super(taskName, priority);
        mainTask = new ArrayList<>();
    }
}
