package com.example.c196assessment.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196assessment.Entity.Assessments;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.R;

import java.util.List;

public class CoursesMainPageAdapter extends RecyclerView.Adapter<CoursesMainPageAdapter.CoursesMainPageViewHolder>{
    class CoursesMainPageViewHolder extends RecyclerView.ViewHolder{
        private final TextView coursesItemView;
        private CoursesMainPageViewHolder  (View itemView){
            super (itemView);
            coursesItemView=itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    final Courses current=mCourses.get(position);
                    Intent intent = new Intent(context, CoursesDetails.class);
                    intent.putExtra("courseID",current.getCourseID());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("courseStart", current.getCourseStart());
                    intent.putExtra("courseEnd", current.getCourseEnd());
                    intent.putExtra("courseStatus", current.getCourseStatus());
                    intent.putExtra("mentorName", current.getMentorName());
                    intent.putExtra("mentorEmail", current.getMentorEmail());
                    intent.putExtra("mentorPhone", current.getMentorPhone());
                    intent.putExtra("noteText", current.getNoteText());
                    intent.putExtra("termID", current.getTermID());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CoursesMainPageAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public CoursesMainPageAdapter.CoursesMainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_list_item,parent,false);
        return new CoursesMainPageAdapter.CoursesMainPageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesMainPageAdapter.CoursesMainPageViewHolder holder, int position) {
        if(mCourses!=null){
            Courses current=mCourses.get(position);
            String name=current.getCourseTitle();
            holder.coursesItemView.setText(name);
        }
        else{
            holder.coursesItemView.setText("No Product Name");
        }
    }
    public void setCoursesMain(List<Courses> courses){
        mCourses=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCourses!=null) {
            return mCourses.size();
        }
        else return 0;
    }
}

