package classes.controllers;

import classes.Student;
import classes.repos.StudentRepository;
import classes.views.StudentRepoView;

import java.util.ArrayList;

public class StudentRepoController {

    private static StudentRepoController instance= null;
    private StudentRepository model;
    private StudentRepoView view;

    private StudentRepoController(){
        this.model = StudentRepository.getInstance();
        this.view = StudentRepoView.getInstance();
    }

    public static StudentRepoController getInstance(){
        if(instance == null){
            instance = new StudentRepoController();
        }
        return instance;
    }

    public void addStudent(Student obj){
        model.create(obj);
    }

    public Student getOne(int id){
        return model.getOne(id);
    }

    public ArrayList<Student> getAllStudents(){
        return model.getAll();
    }

    public void removeStudent(Student obj){
        model.delete(obj);
    }

    public void updateView(){
        view.printRepoDetails(model.getAll());
    }


}