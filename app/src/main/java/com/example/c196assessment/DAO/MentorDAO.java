package com.example.c196assessment.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.c196assessment.Entity.Mentor;
import java.util.List;

@Dao
public interface MentorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMentor(Mentor mentor);

    @Update
    void updateMentor(Mentor mentor);

    @Delete
    void deleteMentor(Mentor mentor);

    @Query("SELECT * FROM mentor ORDER BY mentorID ASC")
    List<Mentor> getAllMentor();
}
