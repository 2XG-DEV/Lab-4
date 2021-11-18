package classes;

import classes.controllers.CourseRepoController;
import classes.controllers.StudentRepoController;
import classes.controllers.TeacherRepoController;
import classes.views.CourseRepoView;
import classes.views.StudentRepoView;
import classes.views.TeacherRepoView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UI {

    private static UI instance = null;
    private UI(){

    }

    public static UI getInstance() {
        if(instance == null){
            instance = new UI();
        }
        return instance;
    }

    public void display(){
        int choice = -1;
        do{
            System.out.println("1. Register To Course");
            System.out.println("2. Show Courses With Free Places");
            System.out.println("3. Show Students in Course");
            System.out.println("4. Show Classes");
            System.out.println("5. Delete a Course");
            System.out.println("6. Add a Student");
            System.out.println("7. Add a Teacher");
            System.out.println("8. Add a Course");
            System.out.println("9. Sort Students Alphabetical");
            System.out.println("10. Sort Courses Alphabetical");
            System.out.println("11. Filter Students with 30 Credits");
            System.out.println("12. Filter Courses with 6 Credits");
            System.out.println("13. Exit");
            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextInt();
            switch(choice){
                case 1:
                    register(myObj);
                    break;
                case 2:
                    freePlaces();
                    break;
                case 3:
                    showStudentsInCourse(myObj);
                    break;
                case 4:
                    CourseRepoController.getInstance().updateView();
                    break;
                case 5:
                    deleteCourse(myObj);
                    break;
                case 6:
                    addStudent();
                    break;
                case 7:
                    addTeacher();
                    break;
                case 8:
                    addCourse();
                    break;
                case 9:
                    ArrayList<Student> students2 = StudentRepoController.getInstance().getAllStudents();
                    Collections.sort(students2,Student.StuNameComparator);
                    break;
                case 10:
                    ArrayList<Course> courses5 = CourseRepoController.getInstance().getAllCourses();
                    Collections.sort(courses5,Course.CourNameComparator);
                    CourseRepoView.getInstance().printRepoDetails(courses5);
                case 11:
                    ArrayList<Student> students3 = StudentRepoController.getInstance().getAllStudents();
                    students3.removeIf(st -> st.getTotalCredits() != 30);
                    StudentRepoView.getInstance().printRepoDetails(students3);
                case 12:
                    ArrayList<Course> courses6 = CourseRepoController.getInstance().getAllCourses();
                    courses6.removeIf(cor->cor.getCredits() != 6);
                    CourseRepoView.getInstance().printRepoDetails(courses6);
            }
        }while(choice < 12);
    }

    private void addCourse() {
        System.out.println("Enter Course Name , Enter Teacher , Enter CourseID , Enter Credits, Enter Max Enrollment");
        Scanner myObj = new Scanner(System.in);
        String name = myObj.nextLine();
        TeacherRepoController.getInstance().updateView();
        int ch = myObj.nextInt();
        ArrayList<Teacher> teachers = TeacherRepoController.getInstance().getAllTeachers();
        Teacher t = teachers.get(ch);
        int id3 = myObj.nextInt();

        int credits = myObj.nextInt();

        int maxE = myObj.nextInt();
        CourseRepoController.getInstance().addCourse(new Course(id3,name,t,maxE,credits));
    }

    private void addTeacher() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Teacher First Name, last name and id");
        String firstName2 = myObj.nextLine();

        String lastName2 = myObj.nextLine();

        int id2 = myObj.nextInt();
        TeacherRepoController.getInstance().addTeacher(new Teacher(id2,firstName2,lastName2));
    }

    private void addStudent() {
        System.out.println("Enter Student First Name,Last Name and id");
        Scanner myObj = new Scanner(System.in);
        String firstName = myObj.nextLine();

        String lastName = myObj.nextLine();

        int id = myObj.nextInt();
        StudentRepoController.getInstance().addStudent(new Student(id,firstName,lastName));
    }

    private void deleteCourse(Scanner myObj) {
        CourseRepoController.getInstance().updateView();
        Course c3;
        ArrayList<Course> courses3 = CourseRepoController.getInstance().getAllCourses();
        int choice5 = -1;
        choice5 = myObj.nextInt();
        c3=courses3.get(choice5);
        CourseRepoController.getInstance().removeCourse(c3);
    }

    private void showStudentsInCourse(Scanner myObj) {
        CourseRepoController.getInstance().updateView();
        Course c2;
        ArrayList<Course> courses2 = CourseRepoController.getInstance().getAllCourses();
        int choice4 = -1;
        choice4 = myObj.nextInt();
        c2=courses2.get(choice4);
        ArrayList<Student> studentsInCourse = RegistrationSystem.getInstance().retrieveStudentsEnrolledForACourse(c2);
    }

    private void freePlaces() {
        ArrayList<Course> freeCourses =  RegistrationSystem.getInstance().retrieveCoursesWithFreePlaces();
        CourseRepoView.getInstance().printRepoDetails(freeCourses);
        System.out.println("Anzahl : " + freeCourses.size());
    }

    private void register(Scanner myObj) {
        StudentRepoController.getInstance().updateView();
        Student s;
        ArrayList<Student> students = StudentRepoController.getInstance().getAllStudents();
        int choice2 = -1;
        choice2 = myObj.nextInt();
        s=students.get(choice2);
        CourseRepoController.getInstance().updateView();
        Course c;
        ArrayList<Course> courses = CourseRepoController.getInstance().getAllCourses();
        int choice3 = -1;
        choice3 = myObj.nextInt();
        c=courses.get(choice3);
        RegistrationSystem.getInstance().register(c,s);
    }
}
