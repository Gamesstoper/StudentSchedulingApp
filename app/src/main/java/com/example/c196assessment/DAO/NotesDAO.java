package com.example.c196assessment.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.c196assessment.Entity.Notes;

import java.util.List;
@Dao
public interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNote(Notes notes);

    @Update
    void updateNotes(Notes notes);

    @Delete
    void deleteNotes(Notes notes);

    @Query("SELECT * FROM notes ORDER BY noteID ASC")
    List<Notes> getAllNotes();
}
