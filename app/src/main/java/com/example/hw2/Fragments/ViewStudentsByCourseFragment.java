package com.example.hw2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.hw2.R;
import com.example.hw2.Recycler.CourseAdapter;
import com.example.hw2.Recycler.StudentAdapter;
import com.example.hw2.Databases.AppDatabase;
import com.example.hw2.Entity.Course;
import com.example.hw2.Entity.Student;
import com.example.hw2.Entity.Teacher;

import java.util.List;

public class ViewStudentsByCourseFragment extends Fragment {

    RecyclerView recyclerCourses, recyclerStudents;
    TextView txtStudentCount, txtTeacherName;
    AppDatabase db;
    CourseAdapter courseAdapter;
    StudentAdapter studentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_student_by_course_fragment, container, false);

        recyclerCourses = view.findViewById(R.id.recyclerCourses);
        recyclerStudents = view.findViewById(R.id.recyclerStudents);
        txtStudentCount = view.findViewById(R.id.txtStudentCount);
        txtTeacherName = view.findViewById(R.id.txtTeacherName);

        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "school-database")
                .allowMainThreadQueries()
                .build();

        List<Course> courses = db.courseDao().getAllCourses();
        courseAdapter = new CourseAdapter(courses);
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCourses.setAdapter(courseAdapter);

        courseAdapter.setOnItemClickListener(course -> {
            // Show enrolled students
            List<Student> students = db.studentDao().getStudentsForCourse(course.course_id);
            studentAdapter = new StudentAdapter(students);
            recyclerStudents.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerStudents.setAdapter(studentAdapter);
            txtStudentCount.setText("Enrolled Students (" + students.size() + "):");

            if (students.isEmpty()) {
                Toast.makeText(getContext(), "No students in this course", Toast.LENGTH_SHORT).show();
            }

            // Show assigned teacher
            Teacher teacher = db.teacherDao().getTeacherForCourse(course.course_id);
            if (teacher != null) {
                txtTeacherName.setText("Teacher: " + teacher.firstName + " " + teacher.lastName);
            } else {
                txtTeacherName.setText("Teacher: (None Assigned)");
            }
        });

        return view;
    }
}
