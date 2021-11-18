package classes.views;

import classes.Teacher;


import java.util.ArrayList;

public class TeacherRepoView {
    private static TeacherRepoView instance = null;

    public static TeacherRepoView getInstance(){
        if(instance == null)
            instance = new TeacherRepoView();
        return instance;
    }

    public void printRepoDetails(ArrayList<Teacher> teachers){
        int index = 0;
        for(Teacher t : teachers){
            System.out.println((index)+t.toString());
        }
    }
}
