package com.example.c196assessment.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.Entity.Terms;
import com.example.c196assessment.R;

public class MainActivity extends AppCompatActivity {
    Repository repository;
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repository= new Repository(getApplication());
        repository.insert(new Terms(1,"Fall","03/01/2022","03/02/2022")
        );
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