package classes.views;


import classes.Student;
import classes.controllers.StudentRepoController;
import classes.repos.StudentRepository;

import java.util.ArrayList;

public class StudentRepoView {
    private static StudentRepoView instance = null;

    public static StudentRepoView getInstance(){
        if(instance == null)
            instance = new StudentRepoView();
        return instance;
    }

    public void printRepoDetails(  ArrayList<Student> all ){

        int index = 0;
        for(Student s : all){
            System.out.println((index++) + s.toString());
        }
    }
}
