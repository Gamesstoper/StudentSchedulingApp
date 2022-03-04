package com.example.c196assessment.Database;


import android.app.Application;

import com.example.c196assessment.DAO.AssessmentsDAO;
import com.example.c196assessment.DAO.CoursesDAO;
import com.example.c196assessment.DAO.TermsDAO;
import com.example.c196assessment.Entity.Assessments;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.Entity.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssessmentsDAO mAssessmentDAO;
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private List<Assessments> mAllAssessments;
    private List<Courses> mAllCourses;
    private List<Terms> mAllTerms;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application);
        mAssessmentDAO= applicationDatabase.assessmentsDAO();
        mCoursesDAO = applicationDatabase.coursesDAO();
        mTermsDAO = applicationDatabase.termsDAO();
    }

    //Assessments SQL code
    public void insert(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insertAssessment(assessments);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDAO.updateAssessment(assessments);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDAO.deleteAssessment(assessments);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Assessments> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments= mAssessmentDAO.getAllAssessments();
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }



//Courses SQL code
    public void insert(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.insertCourses(courses);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.updateCourses(courses);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.deleteCourses(courses);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Courses> getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses= mCoursesDAO.getAllCourses();
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }


    //Terms SQL code
    public void insert(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.insertTerm(terms);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.updateTerm(terms);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.deleteTerm(terms);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Terms> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms= mTermsDAO.getAllTerms();
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

}
