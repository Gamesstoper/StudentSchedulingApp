package com.example.c196assessment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.Entity.Assessments;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.Entity.Mentor;
import com.example.c196assessment.Entity.Terms;
import com.example.c196assessment.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void termsPageEnter(View view) {
        Intent intent= new Intent(MainActivity.this, TermsMainPage.class);
        startActivity(intent);

    }

    public void coursesPageEnter(View view) {
        Intent intent= new Intent(MainActivity.this, CoursesMainPage.class);
        startActivity(intent);
    }

    public void assessmentsPageEnter(View view) {
        Intent intent= new Intent(MainActivity.this, AssessmentsMainPage.class);
        startActivity(intent);

    }
}