package com.example.c196assessment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.Entity.Assessments;
import com.example.c196assessment.Entity.Terms;
import com.example.c196assessment.R;

import java.util.List;

public class AssessmentsMainPage extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_main_page);
        RecyclerView recyclerView= findViewById(R.id.Assessments);
        Repository repository= new Repository(getApplication());
        List<Assessments> assessmentsList=repository.getAllAssessments();
        final AssessmentMainPageAdapter adapter= new AssessmentMainPageAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessmentsMain(assessmentsList);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_term_main_page, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.termListRefresh:
                RecyclerView recyclerView= findViewById(R.id.Assessments);
                Repository repository= new Repository(getApplication());
                List<Assessments> assessmentsList=repository.getAllAssessments();
                final AssessmentMainPageAdapter adapter= new AssessmentMainPageAdapter(this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter.setAssessmentsMain(assessmentsList);
        }
        return super.onOptionsItemSelected(item);
    }

}