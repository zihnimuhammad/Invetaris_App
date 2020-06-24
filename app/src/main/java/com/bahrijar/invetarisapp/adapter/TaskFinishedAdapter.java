package com.bahrijar.invetarisapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bahrijar.invetarisapp.activity.EmptyRecyclerView;
import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.model.Task;

import java.util.ArrayList;

public class TaskFinishedAdapter extends EmptyRecyclerView.Adapter<TaskFinishedAdapter.TaskViewHolder> {

    private ArrayList<Task> listTask;

    public TaskFinishedAdapter(ArrayList<Task> listTask) {
        this.listTask = listTask;
    }

    public void setTask(ArrayList<Task>task) {
        this.listTask = task;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_finished, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = listTask.get(position);

        holder.tvTaskTitle.setText(task.getvTitleTask());
        holder.tvTaskLocation.setText(task.getvLocationTask());
        holder.tvTaskId.setText(task.getvIdTask());
    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskTitle, tvTaskLocation, tvTaskId;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTaskTitle = itemView.findViewById(R.id.txt_title_task);
            tvTaskLocation = itemView.findViewById(R.id.txt_location_task);
            tvTaskId = itemView.findViewById(R.id.txt_id_task);
        }
    }
}
