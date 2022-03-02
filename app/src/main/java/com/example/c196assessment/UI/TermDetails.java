package com.example.c196assessment.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.R;

public class TermDetails extends AppCompatActivity {
    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    int termID;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        editTermTitle = findViewById(R.id.editTermtTitle);
        editTermStart= findViewById(R.id.editTermStart);
        editTermEnd= findViewById(R.id.editTermEnd);
        termID= getIntent().getIntExtra("termID",-1);

    }

    public void saveButton(View view) {
    }
}