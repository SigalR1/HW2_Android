package com.example.hw2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hw2.Fragments.AddStudentFragment;
import com.example.hw2.Fragments.AddTeacherFragment;
import com.example.hw2.Fragments.AssignStudentToCourseFragment;
import com.example.hw2.Fragments.AssignTeacherToCourseFragment;
import com.example.hw2.Fragments.CreateCourseFragment;
import com.example.hw2.Fragments.ViewStudentsByCourseFragment;


public class MainActivity extends AppCompatActivity {

    Button btnStudents, btnTeachers, btnStudentCourses, btnCreateCourse, btnAssignTeacher, btnViewCourseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all buttons
        btnStudents = findViewById(R.id.btnStudents);
        btnTeachers = findViewById(R.id.btnTeachers);
        btnStudentCourses = findViewById(R.id.btnStudentCourses);
        btnCreateCourse = findViewById(R.id.btnCreateCourse);
        btnAssignTeacher = findViewById(R.id.btnAssignTeacher);
        btnViewCourseStudents = findViewById(R.id.btnViewCourseStudents);

        // Load default fragment (optional)
        loadFragment(new AddStudentFragment());

        // Set click listeners
        btnStudents.setOnClickListener(v -> loadFragment(new AddStudentFragment()));
        btnTeachers.setOnClickListener(v -> loadFragment(new AddTeacherFragment()));
        btnStudentCourses.setOnClickListener(v -> loadFragment(new AssignStudentToCourseFragment()));
        btnCreateCourse.setOnClickListener(v -> loadFragment(new CreateCourseFragment()));
        btnAssignTeacher.setOnClickListener(v -> loadFragment(new AssignTeacherToCourseFragment()));
        btnViewCourseStudents.setOnClickListener(v -> loadFragment(new ViewStudentsByCourseFragment()));
    }

    // Utility function to load fragments
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}