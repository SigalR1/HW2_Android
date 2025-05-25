package com.example.hw2.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hw2.Entity.StudentCourse;

import java.util.List;

@Dao
public interface StudentCourseDao {
    @Insert
    void insertStudentCourse(StudentCourse studentCourse);

    @Query("SELECT * FROM students_courses")
    List<StudentCourse> getAllStudentCourses();

    @Query("SELECT COUNT(*) FROM students_courses WHERE student_id = :sid AND course_id = :cid")
    int countStudentCourse(int sid, int cid);
}
