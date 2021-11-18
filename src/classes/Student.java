package classes;

import classes.repos.CourseRepository;
import classes.repos.EnrollRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student extends Person {
    private int studentId;
    private int totalCredits;
    List<Course> enrolledCourses;

    public Student(int studentId,String firstName, String lastName ) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = 0;
        this.enrolledCourses = new ArrayList<Course>();
    }

    public int getStudentId() {
        return studentId;
    }

    public int getTotalCredits() {
        int total = 0;
        ArrayList<Course> courses = getEnrolledCourses();
        for(Course c : courses){
            total += c.getCredits();
        }

        return totalCredits;
    }

    public ArrayList<Course> getEnrolledCourses() {
        ArrayList<Enrollment> enrolls = EnrollRepository.getInstance().getAllForStudent(this.getStudentId());
        ArrayList<Course> enrolledCourses = new ArrayList<Course>();//CourseRepository.getInstance().getOne()
        for(Enrollment en : enrolls){
            enrolledCourses.add(CourseRepository.getInstance().getOne(en.getCourseId()));
        }
        return enrolledCourses;
    }

    @Override
    public String toString(){
        return this.getFirstName() + " " + this.getLastName() + " mit id: " + this.getStudentId() + " und " + this.getTotalCredits() + " credite"+ "\n";
    }

    public static Comparator<Student> StuNameComparator = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            String StudentName1 = s1.getLastName().toUpperCase();
            String StudentName2 = s2.getLastName().toUpperCase();
            return StudentName1.compareTo(StudentName2);
        }
    };

    public String toCSV(){
        return this.getStudentId()+","+this.getFirstName() + "," + this.getLastName() + "\n";
    }


}
