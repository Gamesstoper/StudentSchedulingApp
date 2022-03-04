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

public class AssessmentDetails extends AppCompatActivity {
    EditText editAssessmentTitle;
    EditText editAssessmentDate;
    String title;
    String assessmentDate;
    String assessmentType;
    int courseID;
    int assessmentID;
    DatePickerDialog.OnDateSetListener Date;
    final Calendar myCalendar= Calendar.getInstance();
    Assessments currentAssessment;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        editAssessmentTitle = findViewById(R.id.editAssessmentTitle);
        editAssessmentDate= findViewById(R.id.editAssessmentDate);
        Spinner spinner = findViewById(R.id.spinnerAssessmentType);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        courseID= getIntent().getIntExtra("courseID",-1);
        assessmentID=getIntent().getIntExtra("assessmentID", -1);
        title=getIntent().getStringExtra("assessmentName");
        assessmentDate=getIntent().getStringExtra("assessmentDate");
        assessmentType=getIntent().getStringExtra("assessmentType");
        editAssessmentTitle.setText(title);
        editAssessmentDate.setText(assessmentDate);
        repository=new Repository(getApplication());
        String myFormat= "MM/dd/yyyy";
        SimpleDateFormat sdf= new SimpleDateFormat(myFormat, Locale.US);
        if(assessmentType!=null){
            int spinnerPosition = adapter.getPosition(assessmentType);
            spinner.setSelection(spinnerPosition);
        }


        Date= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel();
            }
        };
        editAssessmentDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editAssessmentDate.getText().toString();
                if(info.equals(""))info="03/15/2022";
                try{
                    myCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, Date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editAssessmentDate.setText(sdf.format(myCalendar.getTime()));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assessment_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.assessmentSave:
                Assessments assessments;
                Spinner spinner= findViewById(R.id.spinnerAssessmentType);
                if (assessmentID == -1) {
                    int newID = 1;//repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
                    assessments = new Assessments(newID,editAssessmentTitle.getText().toString(), editAssessmentDate.getText().toString(), spinner.getSelectedItem().toString(),courseID);
                    repository.insert(assessments);
                } else {
                    assessments = new Assessments(assessmentID,editAssessmentTitle.getText().toString(), editAssessmentDate.getText().toString(), spinner.getSelectedItem().toString(),courseID);
                    repository.update(assessments);
                }
                return true;
            case R.id.assessmentDelete:
                for (Assessments assessments1 : repository.getAllAssessments()) {

                    if (assessments1.getAssessmentID() == assessmentID) currentAssessment = assessments1;
                }
                    repository.delete(currentAssessment);
                    Toast.makeText(AssessmentDetails.this, currentAssessment.getAssessmentName() + " was deleted", Toast.LENGTH_LONG).show();

                return true;
            case R.id.notify:
                String dateFromScreen=editAssessmentDate.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=myDate.getTime();
                Intent intent= new Intent(AssessmentDetails.this,MyReceiver.class);
                intent.putExtra("key","Assessment Today");
                PendingIntent sender=PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return true;
    }
}