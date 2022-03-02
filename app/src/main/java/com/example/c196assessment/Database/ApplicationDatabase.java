package com.example.c196assessment.Database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196assessment.DAO.AssessmentsDAO;
import com.example.c196assessment.DAO.CoursesDAO;
import com.example.c196assessment.DAO.MentorDAO;
import com.example.c196assessment.DAO.NotesDAO;
import com.example.c196assessment.DAO.TermsDAO;
import com.example.c196assessment.Entity.Assessments;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.Entity.Mentor;
import com.example.c196assessment.Entity.Notes;
import com.example.c196assessment.Entity.Terms;

@Database(entities = {Assessments.class, Courses.class, Mentor.class, Terms.class, Notes.class}, version = 3, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract AssessmentsDAO assessmentsDAO();
    public abstract CoursesDAO coursesDAO();
    public abstract MentorDAO mentorDAO();
    public abstract TermsDAO termsDAO();
    public abstract NotesDAO notesDAO();

    private static volatile ApplicationDatabase INSTANCE;

    static ApplicationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "Application.db")
                            //.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
