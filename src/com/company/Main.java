package com.company;

import classes.*;
import classes.controllers.CourseRepoController;
import classes.controllers.StudentRepoController;
import classes.controllers.TeacherRepoController;
import classes.repos.CourseRepository;
import classes.repos.StudentRepository;
import classes.repos.TeachersRepository;

public class Main {

    public static void main(String[] args) {
 /*
        Student Radu = new Student(1,"Tarean","Radu");
        Student George = new Student(2,"Popescu","George");
        Student Marcel = new Student(3,"Ionescu","Marcel");
        Student Maria = new Student(4,"Mihailescu","Maria");
        Teacher Modoi = new Teacher(1,"Gheorge","Modoi");
        Teacher Ion = new Teacher(2,"Popescu","Ion");
        Teacher Mihai = new Teacher(3,"Georgescu","Mihai");
        Course Algebra = new Course(3,"Algebra fur informatiker",Modoi,2,6);
        Course OOP = new Course(1,"Object Oriented Programming",Ion,3,6);
        Course DSA = new Course(2,"DatenStrukturen und Algorithmen",Mihai,1,6);
        StudentRepoController.getInstance().addStudent(Radu);
        StudentRepoController.getInstance().addStudent(George);
        StudentRepoController.getInstance().addStudent(Marcel);
        StudentRepoController.getInstance().addStudent(Maria);
        TeacherRepoController.getInstance().addTeacher(Modoi);
        TeacherRepoController.getInstance().addTeacher(Ion);
        TeacherRepoController.getInstance().addTeacher(Mihai);

        CourseRepoController.getInstance().addCourse(Algebra);
        CourseRepoController.getInstance().addCourse(OOP);
        CourseRepoController.getInstance().addCourse(DSA);

        RegistrationSystem.getInstance().register(Algebra,Radu);
        System.out.println("Radu credits " + Radu.getTotalCredits() );
        RegistrationSystem.getInstance().register(Algebra,Maria);
        for(Student s : RegistrationSystem.getInstance().retrieveStudentsEnrolledForACourse(Algebra)){
            System.out.println(s);
        }
        for(Course c : RegistrationSystem.getInstance().retrieveCoursesWithFreePlaces()){
            System.out.println(c);
        }
        for(Course c : RegistrationSystem.getInstance().getAllCourses()){
            System.out.println(c);
        }
        CourseRepository.getInstance().delete(Algebra);
        System.out.println("Radu credits " + Radu.getTotalCredits() );


*/
        UI.getInstance().display();
    }
}
