package com.example.c196assessment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.Entity.Terms;
import com.example.c196assessment.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {
    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    String title;
    String termStartDate;
    String termEndDate;
    int termID;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    Terms currentTerm;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        editTermTitle = findViewById(R.id.editTermTitle);
        editTermStart= findViewById(R.id.editTermStart);
        editTermEnd= findViewById(R.id.editTermEnd);
        termID= getIntent().getIntExtra("termID",-1);
        title=getIntent().getStringExtra("termTitle");
        termStartDate=getIntent().getStringExtra("termStart");
        termEndDate=getIntent().getStringExtra("termEnd");
        editTermTitle.setText(title);
        editTermStart.setText(termStartDate);
        editTermEnd.setText(termEndDate);
        repository=new Repository(getApplication());
        String myFormat= "MM/dd/yyyy";
        SimpleDateFormat sdf= new SimpleDateFormat(myFormat, Locale.US);
        RecyclerView recyclerView= findViewById(R.id.courseList);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Courses> filteredCourses = new ArrayList<>();
        for (Courses c : repository.getAllCourses()) {
            if (c.getTermID() == termID) filteredCourses.add(c);
        }
        courseAdapter.setCourses(filteredCourses);


        startDate= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelStart();
            }
        };
        editTermStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editTermStart.getText().toString();
                if(info.equals(""))info="03/15/2022";
                try{
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelEnd();
            }
        };
        editTermEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editTermEnd.getText().toString();
                if(info.equals(""))info="03/15/2022";
                try{
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabelStart() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTermStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTermEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_term_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.termRefresh:
                RecyclerView recyclerView = findViewById(R.id.courseList);
                repository = new Repository(getApplication());
                final CourseAdapter courseAdapter = new CourseAdapter(this);
                recyclerView.setAdapter(courseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                List<Courses> filteredCourses = new ArrayList<>();
                for (Courses c : repository.getAllCourses()) {
                    if (c.getTermID() == termID) filteredCourses.add(c);
                }
                courseAdapter.setCourses(filteredCourses);
                return true;
            case R.id.termSave:
                Terms terms;
                int newID;
                if (termID == -1) {
                    if(repository.getAllAssessments().size() == 0){
                        newID = 1;
                    }
                    else {
                        newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermID() + 1;
                    }
                    terms = new Terms(newID,editTermTitle.getText().toString(), editTermStart.getText().toString(), editTermEnd.getText().toString());
                    repository.insert(terms);
                } else {
                    terms = new Terms(termID,editTermTitle.getText().toString(), editTermStart.getText().toString(),editTermEnd.getText().toString());
                    repository.update(terms);
                }
                return true;
            case R.id.termDelete:
                for (Terms terms1 : repository.getAllTerms()) {

                    if (terms1.getTermID() == termID) currentTerm = terms1;
                }

                int numCourses = 0;
                for (Courses courses : repository.getAllCourses()) {
                    if (courses.getTermID() == termID) ++numCourses;
                }

                if (numCourses == 0) {
                    repository.delete(currentTerm);
                    Toast.makeText(TermDetails.this, currentTerm.getTermTitle() + " was deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TermDetails.this, "Can't delete a Term with Courses", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return true;
    }

    public void goToCoursesAdd(View view) {
        Intent intent= new Intent(TermDetails.this, CoursesDetails.class);
        intent.putExtra("termID", termID);
        intent.putExtra("courseID", -1);
        intent.putExtra("courseStatus",-1);
        startActivity(intent);
    }
}