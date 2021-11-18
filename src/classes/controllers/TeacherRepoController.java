package classes.controllers;

import classes.Teacher;
import classes.repos.StudentRepository;
import classes.repos.TeachersRepository;
import classes.views.TeacherRepoView;

import java.util.ArrayList;

public class TeacherRepoController {

    private static TeacherRepoController instance = null;
    private TeachersRepository model;
    private TeacherRepoView view;

    private TeacherRepoController(){
        this.model = TeachersRepository.getInstance();
        this.view = TeacherRepoView.getInstance();
    }

    public static TeacherRepoController getInstance() {
        if(instance == null)
            instance = new TeacherRepoController();
        return instance;
    }

    public void addTeacher(Teacher obj){
        model.create(obj);
    }

    public Teacher getOne(int id){
        return model.getOne(id);
    }

    public ArrayList<Teacher> getAllTeachers(){
        return model.getAll();
    }

    public void removeTeacher(Teacher obj){
        model.delete(obj);
    }

    public void updateView(){
        view.printRepoDetails(model.getAll());
    }


}
