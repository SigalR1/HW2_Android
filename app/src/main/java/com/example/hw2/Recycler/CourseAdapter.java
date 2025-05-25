package com.example.hw2.Recycler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.Entity.Course;
import com.example.hw2.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final List<Course> courses;
    private int selectedId = -1;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCourse;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCourse = itemView.findViewById(R.id.txtItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
        return new ViewHolder(view);
    }

    // === Click Listener Interface ===
    public interface OnItemClickListener {
        void onItemClick(Course course);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    // === Delete Listener Interface ===
    public interface OnDeleteListener {
        void onDelete(Course course);
    }

    private OnDeleteListener deleteListener;

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.deleteListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course c = courses.get(position);
        holder.txtCourse.setText(c.courseName);
        holder.itemView.setBackgroundColor(c.course_id == selectedId ? Color.LTGRAY : Color.WHITE);

        holder.itemView.setOnClickListener(v -> {
            selectedId = c.course_id;
            notifyDataSetChanged();
            if (clickListener != null) clickListener.onItemClick(c);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (deleteListener != null) deleteListener.onDelete(c);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
