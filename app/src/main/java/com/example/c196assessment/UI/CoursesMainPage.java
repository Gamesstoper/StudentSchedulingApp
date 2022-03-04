package com.example.c196assessment.UI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.c196assessment.Database.Repository;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.R;

import java.util.List;

public class CoursesMainPage extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_courses_main_page);
            RecyclerView recyclerView= findViewById(R.id.Courses);
            Repository repository= new Repository(getApplication());
            List<Courses> courses=repository.getAllCourses();
            final CoursesMainPageAdapter adapter= new CoursesMainPageAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setCoursesMain(courses);
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
                    RecyclerView recyclerView= findViewById(R.id.Courses);
                    Repository repository= new Repository(getApplication());
                    List<Courses> courses=repository.getAllCourses();
                    final CoursesMainPageAdapter adapter= new CoursesMainPageAdapter(this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setCoursesMain(courses);
            }
            return super.onOptionsItemSelected(item);
        }

    }