package com.example.c196assessment.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "terms")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termTitle;
    private String termStart;
    private String termEnd;

    public Terms(String termTitle, String termStart, String termEnd){
        this.termTitle = termTitle;
        this.termStart=termStart;
        this.termEnd=termEnd;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "termID=" + termID +
                ", termTitle='" + termTitle + '\'' +
                ", termStart='" + termStart + '\'' +
                ", termEnd='" + termEnd + '\'' +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }
}
