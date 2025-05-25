package com.example.hw2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hw2.Entity.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    void insertTeacher(Teacher teacher);

    @Query("SELECT * FROM teachers")
    List<Teacher> getAllTeachers();

    @Query("SELECT teachers.* FROM teachers " +
            "INNER JOIN teachers_courses ON teachers.teacher_id = teachers_courses.teacher_id " +
            "WHERE teachers_courses.course_id = :courseId LIMIT 1")
    Teacher getTeacherForCourse(int courseId);


    @Delete
    void deleteTeacher(Teacher teacher);

}

