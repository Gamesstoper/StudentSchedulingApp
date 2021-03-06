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
import com.example.c196assessment.Entity.Terms;
import com.example.c196assessment.R;
import java.util.List;

public class TermsMainPage extends AppCompatActivity {
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_main_page);
        RecyclerView recyclerView= findViewById(R.id.Term);
        Repository repository= new Repository(getApplication());
        List<Terms> termsList=repository.getAllTerms();
        final TermAdapter adapter= new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(termsList);
        adapter.notifyDataSetChanged();
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
                repository=new Repository(getApplication());
                List<Terms> allTerms=repository.getAllTerms();
                RecyclerView recyclerView=findViewById(R.id.Term);
                final TermAdapter termAdapter=new TermAdapter(this);
                recyclerView.setAdapter(termAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termAdapter.setTerms(allTerms);

        }
        return super.onOptionsItemSelected(item);
    }
    public void goToTermDetail(View view) {
        Intent intent = new Intent(TermsMainPage.this,TermDetails.class);
        startActivity(intent);
    }

}