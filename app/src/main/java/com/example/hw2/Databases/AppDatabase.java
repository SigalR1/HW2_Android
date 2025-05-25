package com.example.hw2.Databases;

import com.example.hw2.Dao.CourseDao;
import com.example.hw2.Dao.StudentCourseDao;
import com.example.hw2.Dao.StudentDao;
import com.example.hw2.Dao.TeacherCourseDao;
import com.example.hw2.Dao.TeacherDao;
import com.example.hw2.Entity.Course;
import com.example.hw2.Entity.Student;
import com.example.hw2.Entity.StudentCourse;
import com.example.hw2.Entity.Teacher;
import com.example.hw2.Entity.TeacherCourse;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hw2.Dao.*;
import com.example.hw2.Entity.*;

@Database(
        entities = {Student.class, Teacher.class, Course.class, TeacherCourse.class, StudentCourse.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract TeacherDao teacherDao();
    public abstract CourseDao courseDao();
    public abstract TeacherCourseDao teacherCourseDao();
    public abstract StudentCourseDao studentCourseDao();
}
