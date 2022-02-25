package com.example.c196assessment.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "mentor", foreignKeys = @ForeignKey(entity = Courses.class, parentColumns = "courseID",
childColumns = "courseID", onDelete = ForeignKey.CASCADE))
public class Mentor {

    @PrimaryKey(autoGenerate = true)
    private int mentorID;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private int courseID;

    public Mentor(String mentorName, String mentorPhone, String mentorEmail, int courseID) {
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorID=" + mentorID +
                ", mentorName='" + mentorName + '\'' +
                ", mentorPhone='" + mentorPhone + '\'' +
                ", mentorEmail='" + mentorEmail + '\'' +
                ", courseID=" + courseID +
                '}';
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}


