package classes;



public class Enrollment {
    private int studentId;
    private int courseId;

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String toCSV(){
        return this.getStudentId() + "," + this.getCourseId() + "\n";
    }

    public String toString(){
        return "Student mit id : " + this.getStudentId() + " ist eingeschrieben in Kurs : " + this.getCourseId();
    }
}
