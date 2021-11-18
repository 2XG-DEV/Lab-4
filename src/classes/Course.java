package classes;

import classes.repos.CourseRepository;
import classes.repos.EnrollRepository;
import classes.repos.StudentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Course {


    private String name;
    private Teacher teacher;
    private int maxEnrollment;
    private ArrayList<Student> studentsEnrolled;
    private int credits;
    private int courseId;

    public Course(int courseId,String name, Teacher teacher, int maxEnrollment, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.credits = credits;
        this.studentsEnrolled = new ArrayList<Student>();
        this.courseId = courseId;
    }

    public int getCourseId() { return this.courseId;}

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public ArrayList<Student> getStudentsEnrolled() {
        this.studentsEnrolled = new ArrayList<Student>();
        ArrayList<Enrollment> enrolls = EnrollRepository.getInstance().getAllForCourse(this.getCourseId());
        for(Enrollment en : enrolls){
            this.studentsEnrolled.add(StudentRepository.getInstance().getOne(en.getStudentId()));
        }
        return studentsEnrolled;
    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        CourseRepository.getInstance().delete(this);
        this.credits = credits;
        CourseRepository.getInstance().create(this);
    }

    @Override
    public String toString(){
        return this.getName()+ " anleitet bei: " + this.getTeacher().getFirstName() + " " + this.getTeacher().getLastName() + " mit " + this.getCredits() + " credite " + this.getStudentsEnrolled().size() + "/" + this.getMaxEnrollment()+ "\n";

    }


    public static Comparator<Course> CourNameComparator = new Comparator<Course>() {
        @Override
        public int compare(Course c1, Course c2) {
            String StudentName1 = c1.getName().toUpperCase();
            String StudentName2 = c2.getName().toUpperCase();
            return StudentName1.compareTo(StudentName2);
        }
    };

    public String toCSV(){
        return this.getCourseId() + "," + this.getName() + "," + this.getTeacher().getTeacherId() + "," + this.getMaxEnrollment() + "," + this.getCredits() + "\n";
    }
}
