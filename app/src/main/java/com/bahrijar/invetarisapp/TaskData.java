package com.bahrijar.invetarisapp;

import com.bahrijar.invetarisapp.model.Task;

import java.util.ArrayList;

public class TaskData {
    private static String[][] taskData = new String[][]{
            {
                    "Perbaikan Barang",
                    "Ruang GSG 207",
                    "#LAP001"
            },
            {
                    "Perbaikan Barang",
                    "Ruang GSG 208",
                    "#LAP002"
            },
            {
                    "Perbaikan Barang",
                    "Ruang GSG 208",
                    "#LAP002"
            },
            {
                    "Perbaikan Barang",
                    "Ruang GSG 208",
                    "#LAP002"
            },
            {
                    "Perbaikan Barang",
                    "Ruang GSG 208",
                    "#LAP002"
            }

    };

    public static ArrayList<Task> getListTaskData(){
        ArrayList<Task> listTask = new ArrayList<>();

        for (String[] mDataTask : taskData) {
            Task task = new Task();
            task.setvTitleTask(mDataTask[0]);
            task.setvLocationTask(mDataTask[1]);
            task.setvIdTask(mDataTask[2]);

            listTask.add(task);
        }
        return listTask;
    }
}