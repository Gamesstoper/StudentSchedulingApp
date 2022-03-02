package com.example.c196assessment.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.c196assessment.Entity.Terms;

import java.util.List;
@Dao
public interface TermsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTerm(Terms terms);

    @Update
    void updateTerm(Terms terms);

    @Delete
    void deleteTerm(Terms terms);

    @Query("SELECT * FROM terms ORDER BY termID ASC")
    List<Terms> getAllTerms();

}
