package classes.repos;

import classes.Course;
import classes.Enrollment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollRepository {

    private static EnrollRepository instance = null;
    private File file;

    private EnrollRepository(){
         file = new File("enroll.csv");
    }

    public static EnrollRepository getInstance() {
        if(instance == null){
            instance = new EnrollRepository();
        }
        return instance;
    }

    public void create(Enrollment enr){
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.append(enr.toCSV());
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Enrollment> getAll(){
        ArrayList<Enrollment> toReturn = new ArrayList<Enrollment>();
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    if(values.size() == 2){
                        Enrollment toAdd = new Enrollment(Integer.parseInt(values.get(0)),Integer.parseInt(values.get(1)));
                        toReturn.add(toAdd);
                    }

                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

    public ArrayList<Enrollment> getAllForStudent(int studentId){
        ArrayList<Enrollment> all = this.getAll();
        all.removeIf(t->t.getStudentId()!=studentId);
        return all;
    }

    public ArrayList<Enrollment> getAllForCourse(int courseId){
        ArrayList<Enrollment> all = this.getAll();
        all.removeIf(t->t.getCourseId()!=courseId);
        return all;
    }

    public void delete(Enrollment obj){
        ArrayList<Enrollment> allEnrolls = new ArrayList<Enrollment>();
        allEnrolls.removeIf(t-> t.getCourseId() == obj.getCourseId() && t.getStudentId() == obj.getStudentId());
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
            for(Enrollment e : allEnrolls){
                writer.append(e.toCSV());
            }

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }


}
