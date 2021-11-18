package classes;


import classes.repos.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    List<Course> courses;
    int teacherId;

    public Teacher(int teacherId,String firstName, String lastName) {
        super(firstName, lastName);
        this.courses = new ArrayList<Course>();
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        ArrayList<Course> all = CourseRepository.getInstance().getAll();
        all.removeIf(t-> t.getTeacher().getTeacherId() != this.getTeacherId());
        courses = all;
        return courses;
    }

    public int getTeacherId(){
        return this.teacherId;
    }


    @Override
    public String toString(){
        return "Prof. " + this.getFirstName() + " " + this.getLastName()+ "\n";
    }

    public String toCSV(){
        return this.getTeacherId() +","+ this.getFirstName() + "," + this.getLastName()+ "\n";
    }
}
