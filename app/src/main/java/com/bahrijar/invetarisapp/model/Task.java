package com.bahrijar.invetarisapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private String vTitleTask, vLocationTask, vIdTask;

    public Task() {
        this.vTitleTask = vTitleTask;
        this.vLocationTask = vLocationTask;
        this.vIdTask = vIdTask;
    }

    protected Task(Parcel in) {
        vTitleTask = in.readString();
        vLocationTask = in.readString();
        vIdTask = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getvTitleTask() {
        return vTitleTask;
    }

    public void setvTitleTask(String vTitleTask) {
        this.vTitleTask = vTitleTask;
    }

    public String getvLocationTask() {
        return vLocationTask;
    }

    public void setvLocationTask(String vLocationTask) {
        this.vLocationTask = vLocationTask;
    }

    public String getvIdTask() {
        return vIdTask;
    }

    public void setvIdTask(String vIdTask) {
        this.vIdTask = vIdTask;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.vTitleTask);
        parcel.writeString(this.vIdTask);
        parcel.writeString(this.vLocationTask);
    }
}
