package com.example.c196assessment.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.c196assessment.Entity.Courses;
import java.util.List;

@Dao
public interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourses(Courses courses);

    @Update
    void updateCourses(Courses courses);

    @Delete
    void deleteCourses(Courses courses);

    @Query("SELECT * FROM courses ORDER BY courseID ASC")
    List<Courses> getAllCourses();
}
