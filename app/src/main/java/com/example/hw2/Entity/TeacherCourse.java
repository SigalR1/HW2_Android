package com.example.hw2.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "teachers_courses",
        primaryKeys = {"course_id", "teacher_id"},
        foreignKeys = {
                @ForeignKey(entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Teacher.class,
                        parentColumns = "teacher_id",
                        childColumns = "teacher_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class TeacherCourse {
    @ColumnInfo(name = "teacher_id")
    public int teacher_id;

    @ColumnInfo(name = "course_id")
    public int course_id;
}
