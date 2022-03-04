package com.example.c196assessment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.Entity.Assessments;
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

public class CoursesDetails extends AppCompatActivity {
    Repository repository;
    EditText editCourseTitle;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editMentorName;
    EditText editMentorPhone;
    EditText editMentorEmail;
    EditText editNote;
    int courseID;
    String courseTitle;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String mentorName;
    String mentorPhone;
    String mentorEmail;
    String note;
    int termID;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar calendarStart = Calendar.getInstance();
    final Calendar calendarEnd = Calendar.getInstance();
    Courses currentCourse;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_detail);
        Spinner spinner = findViewById(R.id.spinnerStatusTypes);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.Status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        editCourseTitle = findViewById(R.id.editCourseTitle);
        editCourseStart = findViewById(R.id.editCourseStart);
        editCourseEnd = findViewById(R.id.editCourseEnd);
        editMentorName = findViewById(R.id.editMentorName);
        editMentorPhone = findViewById(R.id.editMentorPhone);
        editMentorEmail = findViewById(R.id.editMentorEmail);
        editNote = findViewById(R.id.editNote);
        termID= getIntent().getIntExtra("termID",-1);
        courseID = getIntent().getIntExtra("courseID",-1);
        courseTitle= getIntent().getStringExtra("courseTitle");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd=getIntent().getStringExtra("courseEnd");
        courseStatus=getIntent().getStringExtra("courseStatus");
        mentorName = getIntent().getStringExtra("mentorName");
        mentorEmail = getIntent().getStringExtra("mentorEmail");
        mentorPhone = getIntent().getStringExtra("mentorPhone");
        note = getIntent().getStringExtra("note");
        editCourseTitle.setText(courseTitle);
        editCourseStart.setText(courseStart);
        editCourseEnd.setText(courseEnd);
        editMentorName.setText(mentorName);
        editMentorPhone.setText(mentorPhone);
        editMentorEmail.setText(mentorEmail);
        editNote.setText(note);
        if(courseStatus!=null){
            int spinnerPosition = adapter.getPosition(courseStatus);
            spinner.setSelection(spinnerPosition);
        }
        repository= new Repository(getApplication());
        String myFormat= "MM/dd/yyyy";
        SimpleDateFormat sdf= new SimpleDateFormat(myFormat, Locale.US);
        RecyclerView recyclerView= findViewById(R.id.assessmentList);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessments> filteredAssessments = new ArrayList<>();
        for (Assessments a : repository.getAllAssessments()) {
            if (a.getCourseID() == courseID) filteredAssessments.add(a);
        }
        assessmentAdapter.setAssessments(filteredAssessments);


        startDate= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, monthOfYear);
                calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelStart();
            }
        };
        editCourseStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editCourseStart.getText().toString();
                if(info.equals(""))info="03/15/2022";
                try{
                    calendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CoursesDetails.this, startDate, calendarStart
                        .get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                calendarEnd.set(Calendar.YEAR, year);
                calendarEnd.set(Calendar.MONTH, monthOfYear);
                calendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelEnd();
            }
        };
        editCourseEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editCourseEnd.getText().toString();
                if(info.equals(""))info="03/15/2022";
                try{
                    calendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CoursesDetails.this, endDate, calendarEnd
                        .get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editCourseStart.setText(sdf.format(calendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editCourseEnd.setText(sdf.format(calendarEnd.getTime()));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.courseRefresh:
                RecyclerView recyclerView = findViewById(R.id.assessmentList);
                repository = new Repository(getApplication());
                final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
                recyclerView.setAdapter(assessmentAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                List<Assessments> filteredAssessments = new ArrayList<>();
                for (Assessments a : repository.getAllAssessments()) {
                    if (a.getCourseID() == courseID) filteredAssessments.add(a);
                }
                assessmentAdapter.setAssessments(filteredAssessments);
                return true;
            case R.id.courseSave:
                Spinner spinner = findViewById(R.id.spinnerStatusTypes);
                //spinner.getSelectedItem().toString();
                Courses courses = null;
                int newID;
                if (courseID == -1) {
                    if(repository.getAllCourses().size() == 0){
                        newID = 1;
                    }
                    else {
                        newID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
                    }
                    System.out.println(newID);
                    courses = new Courses(newID, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),
                            spinner.getSelectedItem().toString(), termID, editMentorName.getText().toString(), editMentorPhone.getText().toString(),
                            editMentorEmail.getText().toString(), editNote.getText().toString());
                    repository.insert(courses);
                } else {
                    courses = new Courses(courseID, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),
                            spinner.getSelectedItem().toString(), termID, editMentorName.getText().toString(), editMentorPhone.getText().toString(),
                            editMentorEmail.getText().toString(), editNote.getText().toString());
                    repository.update(courses);
                }
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Course Note");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

                return true;
            case R.id.courseDelete:
                for (Courses courses1 : repository.getAllCourses()) {

                    if (courses1.getCourseID() == courseID) currentCourse = courses1;
                }

                int numCourses = 0;
                for (Assessments assessments : repository.getAllAssessments()) {
                    if (assessments.getCourseID() == courseID) ++numCourses;
                }

                if (numCourses == 0) {
                    repository.delete(currentCourse);
                    Toast.makeText(CoursesDetails.this, currentCourse.getCourseTitle() + " was deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CoursesDetails.this, "Can't delete a Course with Assessments", Toast.LENGTH_LONG).show();
                }
            case R.id.notifyStart:
                String dateFromScreen=editCourseStart.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=myDate.getTime();
                Intent intent= new Intent(CoursesDetails.this,MyReceiver.class);
                intent.putExtra("key","Course Starts Today");
                PendingIntent sender=PendingIntent.getBroadcast(CoursesDetails.this, ++MainActivity.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyEnd:
                String dateFromScreen1=editCourseEnd.getText().toString();
                String myFormat1 = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                Date myDate1=null;
                try {
                    myDate1=sdf1.parse(dateFromScreen1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger1=myDate1.getTime();
                Intent intent1= new Intent(CoursesDetails.this,MyReceiver.class);
                intent1.putExtra("key","Course Ends Today");
                PendingIntent sender1=PendingIntent.getBroadcast(CoursesDetails.this, ++MainActivity.numAlert,intent1,0);
                AlarmManager alarmManager1=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, trigger1, sender1);
                return true;
        }
        return true;
    }
    public void goToAssessmentAdd(View view) {
        Intent intent= new Intent(CoursesDetails.this, AssessmentDetails.class);
        intent.putExtra("courseID", courseID);
        intent.putExtra("assessmentID", -1);
        startActivity(intent);
    }
}