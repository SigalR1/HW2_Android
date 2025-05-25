package com.example.hw2.Recycler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.Entity.Teacher;
import com.example.hw2.R;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    private final List<Teacher> teachers;
    private int selectedId = -1;

    public TeacherAdapter(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItem;

        public ViewHolder(View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Teacher t = teachers.get(position);
        holder.txtItem.setText(t.firstName + " " + t.lastName);
        holder.itemView.setBackgroundColor(t.teacher_id == selectedId ? Color.LTGRAY : Color.WHITE);

        holder.itemView.setOnClickListener(v -> {
            selectedId = t.teacher_id;
            notifyDataSetChanged();
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (deleteListener != null) deleteListener.onDelete(t);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    // 🔥 Deletion support
    public interface OnDeleteListener {
        void onDelete(Teacher t);
    }

    private OnDeleteListener deleteListener;

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.deleteListener = listener;
    }
}
