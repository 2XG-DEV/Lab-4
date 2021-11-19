package classes.controllers;

import classes.Course;
import classes.Teacher;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepoControllerTest {

    @Test
    void addCourse() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course c = new Course(0,"Algebra",Modoi,6,6);
        CourseRepoController.getInstance().addCourse(c);
        assertEquals(CourseRepoController.getInstance().getAllCourses().size(),1);
        PrintWriter writer = new PrintWriter("courses.csv");
        writer.print("");
    }

    @Test
    void getOne() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course c = new Course(0,"Algebra",Modoi,6,6);
        CourseRepoController.getInstance().addCourse(c);
        assertEquals(CourseRepoController.getInstance().getOne(0).getName(),"Algebra");
        PrintWriter writer = new PrintWriter("courses.csv");
        writer.print("");
    }

    @Test
    void getAllCourses() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course c = new Course(0,"Algebra",Modoi,6,6);
        Teacher Ion = new Teacher(1,"Gheorghe","Modoi");
        Course c2 = new Course(1,"OOP",Ion,6,6);
        CourseRepoController.getInstance().addCourse(c);
        CourseRepoController.getInstance().addCourse(c2);
        assertEquals(CourseRepoController.getInstance().getAllCourses().size(),2);
        PrintWriter writer = new PrintWriter("courses.csv");
        writer.print("");
    }

    @Test
    void removeCourse() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course c = new Course(0,"Algebra",Modoi,6,6);
        CourseRepoController.getInstance().addCourse(c);
        assertEquals(CourseRepoController.getInstance().getAllCourses().size(),1);
        CourseRepoController.getInstance().removeCourse(c);
        assertEquals(CourseRepoController.getInstance().getAllCourses().size(),0);
        PrintWriter writer = new PrintWriter("courses.csv");
        writer.print("");
    }
}