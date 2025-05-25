package com.example.hw2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.hw2.Entity.Course;
import java.util.List;

@Dao
public interface CourseDao {

    // Use this if you don't need the course ID
    @Insert
    void insertCourse(Course course);

    // Use this when you want to get the inserted course_id back
    @Insert
    long insertCourseReturnId(Course course);

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();

    @Delete
    void deleteCourse(Course course);

}
