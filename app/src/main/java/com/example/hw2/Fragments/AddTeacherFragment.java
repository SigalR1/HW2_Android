package com.example.hw2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.hw2.R;
import com.example.hw2.Databases.AppDatabase;
import com.example.hw2.Entity.Teacher;
import com.example.hw2.Recycler.TeacherAdapter;

import java.util.List;

public class AddTeacherFragment extends Fragment {

    EditText etFirst, etLast;
    Button btnAdd;
    RecyclerView recyclerView;
    TeacherAdapter adapter;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_teacher_fragment, container, false);

        etFirst = view.findViewById(R.id.etFirstName);
        etLast = view.findViewById(R.id.etLastName);
        btnAdd = view.findViewById(R.id.btnAddTeacher);
        recyclerView = view.findViewById(R.id.recyclerTeachers);

        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "school-database")
                .allowMainThreadQueries()
                .build();

        btnAdd.setOnClickListener(v -> {
            Teacher t = new Teacher();
            t.firstName = etFirst.getText().toString();
            t.lastName = etLast.getText().toString();
            db.teacherDao().insertTeacher(t);
            Toast.makeText(getContext(), "Teacher added", Toast.LENGTH_SHORT).show();
            etFirst.setText("");
            etLast.setText("");
            refreshList();
        });


        refreshList();

        return view;
    }

    private void refreshList() {
        List<Teacher> teachers = db.teacherDao().getAllTeachers();
        adapter = new TeacherAdapter(teachers);

        adapter.setOnDeleteListener(teacher -> {
            db.teacherDao().deleteTeacher(teacher);
            Toast.makeText(getContext(), "Teacher deleted", Toast.LENGTH_SHORT).show();
            refreshList();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

}
