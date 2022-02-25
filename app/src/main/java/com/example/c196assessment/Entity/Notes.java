package com.example.c196assessment.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes", foreignKeys = @ForeignKey(entity = Courses.class,
        parentColumns = "courseID",
        childColumns = "courseID", onDelete = ForeignKey.CASCADE))
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String noteTitle;
    private String noteText;
    private int courseID;

    public Notes(String noteTitle, String noteText, int courseID) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteID=" + noteID +
                ", courseID=" + courseID +
                '}';
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
