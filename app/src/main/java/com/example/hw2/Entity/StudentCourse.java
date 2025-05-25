package com.example.hw2.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "students_courses",
        primaryKeys = {"student_id", "course_id"},
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "student_id",
                        childColumns = "student_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class StudentCourse {
    @ColumnInfo(name = "student_id")
    public int student_id;

    @ColumnInfo(name = "course_id")
    public int course_id;
}

