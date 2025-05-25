package com.example.hw2.Recycler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.Entity.Student;
import com.example.hw2.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private final List<Student> students;
    private int selectedId = -1;

    public StudentAdapter(List<Student> students) {
        this.students = students;
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student s = students.get(position);
        holder.txtItem.setText(s.firstName + " " + s.lastName);
        holder.itemView.setBackgroundColor(s.student_id == selectedId ? Color.LTGRAY : Color.WHITE);

        holder.itemView.setOnClickListener(v -> {
            selectedId = s.student_id;
            notifyDataSetChanged();
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (deleteListener != null) deleteListener.onDelete(s);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public interface OnDeleteListener {
        void onDelete(Student s);
    }

    private OnDeleteListener deleteListener;

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.deleteListener = listener;
    }
}
