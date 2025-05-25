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
import com.example.hw2.Entity.Course;
import com.example.hw2.Recycler.CourseAdapter;

import java.util.List;

public class CreateCourseFragment extends Fragment {

    EditText etCourseName, etStart, etEnd;
    Button btnCreate;
    RecyclerView recyclerView;
    CourseAdapter adapter;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_course_fragment, container, false);

        etCourseName = view.findViewById(R.id.etCourseName);
        etStart = view.findViewById(R.id.etStartDate);
        etEnd = view.findViewById(R.id.etEndDate);
        btnCreate = view.findViewById(R.id.btnCreateCourse);
        recyclerView = view.findViewById(R.id.recyclerCourses);

        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "school-database")
                .allowMainThreadQueries()
                .build();

        btnCreate.setOnClickListener(v -> {
            Course c = new Course();
            c.courseName = etCourseName.getText().toString();
            c.startDate = etStart.getText().toString();
            c.endDate = etEnd.getText().toString();
            db.courseDao().insertCourse(c);
            Toast.makeText(getContext(), "Course created", Toast.LENGTH_SHORT).show();
            etCourseName.setText("");
            etStart.setText("");
            etEnd.setText("");
            refreshList();
        });

        refreshList();

        return view;
    }

    private void refreshList() {
        List<Course> courses = db.courseDao().getAllCourses();
        adapter = new CourseAdapter(courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnDeleteListener(course -> {
            db.courseDao().deleteCourse(course);
            Toast.makeText(getContext(), "Course deleted", Toast.LENGTH_SHORT).show();
            refreshList();
        });

    }
}
