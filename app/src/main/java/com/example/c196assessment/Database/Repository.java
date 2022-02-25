package com.example.c196assessment.Database;


import android.app.Application;

import com.example.c196assessment.DAO.AssessmentsDAO;
import com.example.c196assessment.DAO.CoursesDAO;
import com.example.c196assessment.DAO.MentorDAO;
import com.example.c196assessment.DAO.NotesDAO;
import com.example.c196assessment.DAO.TermsDAO;
import com.example.c196assessment.Entity.Assessments;
import com.example.c196assessment.Entity.Courses;
import com.example.c196assessment.Entity.Mentor;
import com.example.c196assessment.Entity.Notes;
import com.example.c196assessment.Entity.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssessmentsDAO mAssessmentDAO;
    private CoursesDAO mCoursesDAO;
    private MentorDAO mMentorDAO;
    private TermsDAO mTermsDAO;
    private NotesDAO mNotesDAO;
    private List<Assessments> mAllAssessments;
    private List<Courses> mAllCourses;
    private List<Mentor> mAllMentor;
    private List<Terms> mAllTerms;
    private List<Notes> mAllNotes;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application);
        mAssessmentDAO= applicationDatabase.assessmentsDAO();
        mCoursesDAO = applicationDatabase.coursesDAO();
        mMentorDAO = applicationDatabase.mentorDAO();
        mTermsDAO = applicationDatabase.termsDAO();
        mNotesDAO = applicationDatabase.notesDAO();
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
    public List<Assessments> getAllAssessments(Assessments assessments){
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
    public List<Courses> getAllCourses(Courses courses){
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



    //Mentor SQL code
    public void insert(Mentor mentor){
        databaseExecutor.execute(()->{
            mMentorDAO.insertMentor(mentor);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Mentor mentor){
        databaseExecutor.execute(()->{
            mMentorDAO.updateMentor(mentor);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Mentor mentor){
        databaseExecutor.execute(()->{
            mMentorDAO.deleteMentor(mentor);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Mentor> getAllMentor(Mentor mentor){
        databaseExecutor.execute(()->{
            mAllMentor= mMentorDAO.getAllMentor();
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllMentor;
    }



    //Notes SQL code
    public void insert(Notes notes){
        databaseExecutor.execute(()->{
            mNotesDAO.insertNote(notes);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Notes notes){
        databaseExecutor.execute(()->{
            mNotesDAO.updateNotes(notes);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Notes notes){
        databaseExecutor.execute(()->{
            mNotesDAO.deleteNotes(notes);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Notes> getAllNotes(Notes notes){
        databaseExecutor.execute(()->{
            mAllNotes= mNotesDAO.getAllNotes();
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllNotes;
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
    public List<Terms> getAllTerms(Terms terms){
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
