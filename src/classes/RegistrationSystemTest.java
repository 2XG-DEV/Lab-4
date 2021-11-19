package classes;

import classes.repos.CourseRepository;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {

    @Test
    void register() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course Algebra = new Course(0,"Algebra",Modoi,5,6);
        Student Radu = new Student(0,"Radu","Tarean");
        RegistrationSystem.getInstance().register(Algebra,Radu);

        assertEquals(Radu.enrolledCourses.size(),1);
        assertEquals(Algebra.getStudentsEnrolled().size(),1);

        PrintWriter writer = new PrintWriter("courses.csv");
        PrintWriter writer2 = new PrintWriter("students.csv");
        PrintWriter writer3 = new PrintWriter("teachers.csv");
        PrintWriter writer4 = new PrintWriter("enroll.csv");
        writer.print("");
        writer2.print("");
        writer3.print("");
        writer4.print("");

    }

    @org.junit.jupiter.api.Test
    void retrieveCoursesWithFreePlaces() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course Algebra = new Course(0,"Algebra",Modoi,2,6);
        Student Radu = new Student(0,"Radu","Tarean");
        Student Maria = new Student(10,"Maria","Popescu");

        Teacher Ion = new Teacher(1,"ion","popescu");
        Course OOP = new Course(1,"OOP",Ion,2,6);

        RegistrationSystem.getInstance().register(Algebra,Radu);
        RegistrationSystem.getInstance().register(Algebra,Maria);

        RegistrationSystem.getInstance().register(OOP,Radu);
        assertEquals(RegistrationSystem.getInstance().retrieveCoursesWithFreePlaces().size(),1);
        PrintWriter writer = new PrintWriter("courses.csv");
        PrintWriter writer2 = new PrintWriter("students.csv");
        PrintWriter writer3 = new PrintWriter("teachers.csv");
        PrintWriter writer4 = new PrintWriter("enroll.csv");
        writer.print("");
        writer2.print("");
        writer3.print("");
        writer4.print("");

    }

    @org.junit.jupiter.api.Test
    void retrieveStudentsEnrolledForACourse() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course Algebra = new Course(0,"Algebra",Modoi,2,6);
        Student Radu = new Student(0,"Radu","Tarean");
        Student Maria = new Student(1,"Maria","Popescu");

        Teacher Ion = new Teacher(1,"ion","popescu");
        Course OOP = new Course(1,"OOP",Ion,2,6);

        RegistrationSystem.getInstance().register(Algebra,Radu);
        RegistrationSystem.getInstance().register(Algebra,Maria);

        RegistrationSystem.getInstance().register(OOP,Radu);
        assertEquals(RegistrationSystem.getInstance().retrieveStudentsEnrolledForACourse(Algebra).size(),2);

        PrintWriter writer = new PrintWriter("courses.csv");
        PrintWriter writer2 = new PrintWriter("students.csv");
        PrintWriter writer3 = new PrintWriter("teachers.csv");
        PrintWriter writer4 = new PrintWriter("enroll.csv");
        writer.print("");
        writer2.print("");
        writer3.print("");
        writer4.print("");
    }

    @org.junit.jupiter.api.Test
    void getAllCourses() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course Algebra = new Course(0,"Algebra",Modoi,2,6);
        Student Radu = new Student(0,"Radu","Tarean");
        Student Maria = new Student(1,"Maria","Popescu");

        Teacher Ion = new Teacher(1,"ion","popescu");
        Course OOP = new Course(1,"OOP",Ion,2,6);

        RegistrationSystem.getInstance().register(Algebra,Radu);
        RegistrationSystem.getInstance().register(Algebra,Maria);

        RegistrationSystem.getInstance().register(OOP,Radu);
        assertEquals(RegistrationSystem.getInstance().getAllCourses().size(),2);

        PrintWriter writer = new PrintWriter("courses.csv");
        PrintWriter writer2 = new PrintWriter("students.csv");
        PrintWriter writer3 = new PrintWriter("teachers.csv");
        PrintWriter writer4 = new PrintWriter("enroll.csv");
        writer.print("");
        writer2.print("");
        writer3.print("");
        writer4.print("");
    }

    @org.junit.jupiter.api.Test
    void changeCourseCredits() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course Algebra = new Course(0,"Algebra",Modoi,2,6);
        Student Radu = new Student(0,"Radu","Tarean");
        Student Maria = new Student(1,"Maria","Popescu");

        Teacher Ion = new Teacher(1,"ion","popescu");
        Course OOP = new Course(1,"OOP",Ion,2,6);

        RegistrationSystem.getInstance().register(Algebra,Radu);
        RegistrationSystem.getInstance().register(Algebra,Maria);

        RegistrationSystem.getInstance().register(OOP,Radu);
        assertEquals(Radu.getTotalCredits(),12);
        Algebra.setCredits(8);
        assertEquals(Radu.getTotalCredits(),14);

        PrintWriter writer = new PrintWriter("courses.csv");
        PrintWriter writer2 = new PrintWriter("students.csv");
        PrintWriter writer3 = new PrintWriter("teachers.csv");
        PrintWriter writer4 = new PrintWriter("enroll.csv");
        writer.print("");
        writer2.print("");
        writer3.print("");
        writer4.print("");
    }

    @org.junit.jupiter.api.Test
    void removeCourse() throws FileNotFoundException {
        Teacher Modoi = new Teacher(0,"Gheorghe","Modoi");
        Course Algebra = new Course(0,"Algebra",Modoi,2,6);
        Student Radu = new Student(0,"Radu","Tarean");
        Student Maria = new Student(1,"Maria","Popescu");

        Teacher Ion = new Teacher(0,"ion","popescu");
        Course OOP = new Course(1,"OOP",Ion,2,6);

        RegistrationSystem.getInstance().register(Algebra,Radu);
        RegistrationSystem.getInstance().register(Algebra,Maria);
        CourseRepository.getInstance().create(Algebra);
        CourseRepository.getInstance().create(OOP);

        RegistrationSystem.getInstance().register(OOP,Radu);
        RegistrationSystem.getInstance().removeCourse(Algebra);
        assertEquals(Radu.getTotalCredits(),6);

        PrintWriter writer = new PrintWriter("courses.csv");
        PrintWriter writer2 = new PrintWriter("students.csv");
        PrintWriter writer3 = new PrintWriter("teachers.csv");
        PrintWriter writer4 = new PrintWriter("enroll.csv");
        writer.print("");
        writer2.print("");
        writer3.print("");
        writer4.print("");
    }
}