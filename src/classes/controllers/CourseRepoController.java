package classes.controllers;

import classes.Course;
import classes.Enrollment;
import classes.repos.CourseRepository;
import classes.repos.EnrollRepository;
import classes.repos.TeachersRepository;
import classes.views.CourseRepoView;

import java.util.ArrayList;

public class CourseRepoController {

    private static CourseRepoController instance;
    private CourseRepository model;
    private CourseRepoView view;


    private CourseRepoController(){
        this.model = CourseRepository.getInstance();
        this.view = CourseRepoView.getInstance();
    }

    public static CourseRepoController getInstance(){
        if(instance == null)
            instance = new CourseRepoController();
        return instance;
    }

    public void addCourse(Course obj){
        model.create(obj);
    }

    public Course getOne(int id){
        return model.getOne(id);
    }

    public ArrayList<Course> getAllCourses(){
        return model.getAll();
    }

    public void removeCourse(Course obj){
        model.delete(obj);
        ArrayList<Enrollment> enrolls = EnrollRepository.getInstance().getAllForCourse(obj.getCourseId());
        for(Enrollment e : enrolls){
            EnrollRepository.getInstance().delete(e);
        }
    }

    public void updateView(){
        view.printRepoDetails(model.getAll());
    }


}
