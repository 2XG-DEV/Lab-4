package classes.views;

import classes.Course;
import classes.controllers.CourseRepoController;


import java.util.ArrayList;


public class CourseRepoView {

    private static CourseRepoView instance = null;

    public static CourseRepoView getInstance()  {
        if(instance == null)
            instance = new CourseRepoView();
        return instance;
    }

    public void printRepoDetails(ArrayList<Course> all){

        int index = 0;
        for(Course c : all){
            System.out.println((index++) + c.toString());
        }
    }
}
