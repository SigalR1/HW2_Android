package com.example.hw2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hw2.Entity.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM students")
    List<Student> getAllStudents();

    @Query("SELECT students.* FROM students " +
            "INNER JOIN students_courses ON students.student_id = students_courses.student_id " +
            "WHERE students_courses.course_id = :courseId")
    List<Student> getStudentsForCourse(int courseId);

    @Delete
    void deleteStudent(Student student);
}
