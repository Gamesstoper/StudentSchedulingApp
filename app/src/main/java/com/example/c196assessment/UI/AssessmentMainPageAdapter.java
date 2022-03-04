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
import com.example.c196assessment.R;

import java.util.List;

public class AssessmentMainPageAdapter extends RecyclerView.Adapter<AssessmentMainPageAdapter.AssessmentMainPageViewHolder>{
        class AssessmentMainPageViewHolder extends RecyclerView.ViewHolder{
            private final TextView assessmentItemView;
            private AssessmentMainPageViewHolder  (View itemView){
                super (itemView);
                assessmentItemView=itemView.findViewById(R.id.assessmentView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position= getAdapterPosition();
                        final Assessments current=mAssessments.get(position);
                        Intent intent = new Intent(context, AssessmentDetails.class);
                        intent.putExtra("assessmentID", current.getAssessmentID());
                        intent.putExtra("courseID",current.getCourseID());
                        intent.putExtra("assessmentName", current.getAssessmentName());
                        intent.putExtra("assessmentDate", current.getAssessmentDate());
                        intent.putExtra("assessmentType", current.getAssessmentType());
                        context.startActivity(intent);
                    }
                });
            }
        }
        private List<Assessments> mAssessments;
        private final Context context;
        private final LayoutInflater mInflater;

        public AssessmentMainPageAdapter(Context context){
            mInflater=LayoutInflater.from(context);
            this.context=context;
        }
        @NonNull
        @Override
        public AssessmentMainPageAdapter.AssessmentMainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=mInflater.inflate(R.layout.assessment_list_item,parent,false);
            return new AssessmentMainPageViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AssessmentMainPageAdapter.AssessmentMainPageViewHolder holder, int position) {
            if(mAssessments!=null){
                Assessments current=mAssessments.get(position);
                String name=current.getAssessmentName();
                holder.assessmentItemView.setText(name);
            }
            else{
                holder.assessmentItemView.setText("No Product Name");
            }
        }
        public void setAssessmentsMain(List<Assessments> assessments){
            mAssessments=assessments;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if(mAssessments!=null) {
                return mAssessments.size();
            }
            else return 0;
        }
    }

