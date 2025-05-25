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
import com.example.hw2.Dao.StudentCourseDao;
import com.example.hw2.Entity.Course;
import com.example.hw2.Entity.Student;
import com.example.hw2.Entity.StudentCourse;
import com.example.hw2.Recycler.CourseAdapter;
import com.example.hw2.Recycler.StudentAdapter;

import java.util.List;

public class AssignStudentToCourseFragment extends Fragment {

    RecyclerView recyclerStudents, recyclerCourses;
    Button btnAssign;

    StudentAdapter studentAdapter;
    CourseAdapter courseAdapter;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_assign_student_to_course_fragment, container, false);

        recyclerStudents = view.findViewById(R.id.recyclerStudents);
        recyclerCourses = view.findViewById(R.id.recyclerCourses);
        btnAssign = view.findViewById(R.id.btnAssign);

        db = Room.databaseBuilder(requireContext(),
                        AppDatabase.class, "school-database")
                .allowMainThreadQueries()
                .build();

        List<Student> students = db.studentDao().getAllStudents();
        List<Course> courses = db.courseDao().getAllCourses();

        studentAdapter = new StudentAdapter(students);
        courseAdapter = new CourseAdapter(courses);

        recyclerStudents.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerStudents.setAdapter(studentAdapter);
        recyclerCourses.setAdapter(courseAdapter);

        btnAssign.setOnClickListener(v -> {

            int studentId = studentAdapter.getSelectedId();
            int courseId = courseAdapter.getSelectedId();
            if (studentId != -1 && courseId != -1) {
                StudentCourse sc = new StudentCourse();
                sc.student_id = studentId;
                sc.course_id = courseId;
                db.studentCourseDao().insertStudentCourse(sc);
                Toast.makeText(getContext(), "Assigned student to course!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please select both", Toast.LENGTH_SHORT).show();
            }
            int count = db.studentCourseDao().countStudentCourse(studentId, courseId);
            if (count > 0) {
                Toast.makeText(getContext(), "Student is already assigned to this course", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        return view;
    }
}
