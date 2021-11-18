package classes;

import classes.controllers.CourseRepoController;
import classes.repos.EnrollRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {

    public static RegistrationSystem instance = null;

    private RegistrationSystem(){

    }

    /**
     *  Singleton Pattern instance getter
     * @return the instance of the object
     */
    public static RegistrationSystem getInstance(){
        if(instance == null)
            instance = new RegistrationSystem();
        return instance;
    }


    /**
     * Registers a student to a course
     * @param course the course to be registered to
     * @param student the student to be registered
     * @return a boolean representing wether the student was succesfully registered or not
     */
    public boolean register(Course course,Student student){
        for(Student s : course.getStudentsEnrolled()) { // check if student already enrolled
            if (s == student) {
                return false;
            }
        }
        if(student.getTotalCredits() + course.getCredits() > 30){
            return false;
        }else if(course.getStudentsEnrolled().size() >= course.getMaxEnrollment()){
            return false;

        }
        Enrollment newEnroll = new Enrollment(student.getStudentId(),course.getCourseId());
        EnrollRepository.getInstance().create(newEnroll);
        return true;
    }

    /**
     * Retrieves a list of courses which have available spaces
     * @return the list of courses with free spaces
     */
    public ArrayList<Course> retrieveCoursesWithFreePlaces(){
        ArrayList<Course> coursesWithFreePlaces = new ArrayList<Course>();
        for(Course c : CourseRepoController.getInstance().getAllCourses()){
            if(c.getStudentsEnrolled().size() < c.getMaxEnrollment()){
                coursesWithFreePlaces.add(c);
            }
        }
        return coursesWithFreePlaces;
    }

    /**
     * Retrieves the students enrolled in a course
     * @param c course to show enrolled students for
     * @return list of enrolled students
     */
    public ArrayList<Student> retrieveStudentsEnrolledForACourse(Course c){
        return c.getStudentsEnrolled();
    }

    /**
     *  Gets all courses available
     * @return a list of all available courses
     */
    public List<Course> getAllCourses(){
        return CourseRepoController.getInstance().getAllCourses();
    }

    /**
     * A method for a teacher to change a courses credits
     * @param course the course for which the teacher wishes to change the credits
     * @param newCredits the new credits for the course
     */
    public void changeCourseCredits(Course course , int newCredits){
        course.setCredits(newCredits);
    }

    /**
     * a method for a teacher to remove his course
     * @param c the course to be removed
     */
    public void removeCourse(Course c){
        CourseRepoController.getInstance().removeCourse(c);
    }

}
