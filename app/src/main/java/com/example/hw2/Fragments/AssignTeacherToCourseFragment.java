package com.example.hw2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.hw2.R;
import com.example.hw2.Databases.AppDatabase;
import com.example.hw2.Entity.Course;
import com.example.hw2.Entity.Teacher;
import com.example.hw2.Entity.TeacherCourse;
import com.example.hw2.Recycler.CourseAdapter;
import com.example.hw2.Recycler.TeacherAdapter;

import java.util.List;

public class AssignTeacherToCourseFragment extends Fragment {

    RecyclerView recyclerTeachers, recyclerCourses;
    Button btnAssign;
    TeacherAdapter teacherAdapter;
    CourseAdapter courseAdapter;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_assign_teacher_to_course_fragment, container, false);

        recyclerTeachers = view.findViewById(R.id.recyclerTeachers);
        recyclerCourses = view.findViewById(R.id.recyclerCourses);
        btnAssign = view.findViewById(R.id.btnAssignTeacher);

        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "school-database")
                .allowMainThreadQueries()
                .build();

        List<Teacher> teachers = db.teacherDao().getAllTeachers();
        List<Course> courses = db.courseDao().getAllCourses();

        teacherAdapter = new TeacherAdapter(teachers);
        courseAdapter = new CourseAdapter(courses);

        recyclerTeachers.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerTeachers.setAdapter(teacherAdapter);
        recyclerCourses.setAdapter(courseAdapter);

        btnAssign.setOnClickListener(v -> {
            int teacherId = teacherAdapter.getSelectedId();
            int courseId = courseAdapter.getSelectedId();

            if (teacherId != -1 && courseId != -1) {
                TeacherCourse tc = new TeacherCourse();
                tc.teacher_id = teacherId;
                tc.course_id = courseId;
                db.teacherCourseDao().insertTeacherCourse(tc);
                Toast.makeText(getContext(), "Teacher assigned to course!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please select both a teacher and a course", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
