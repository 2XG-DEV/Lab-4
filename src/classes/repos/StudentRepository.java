package classes.repos;

import classes.Student;
import classes.Teacher;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRepository {

    private static StudentRepository instance = null;
    private File file;

    private StudentRepository(){
        file =  new File("students.csv");
    }

    public static StudentRepository getInstance(){
        if(instance == null)
            instance = new StudentRepository();
        return instance;
    }

    public void create(Student obj){
        try (FileWriter writer = new FileWriter(file,true)) {
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(obj.toCSV());
            bw.close();

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student getOne(int id){
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    Student toReturn = new Student(Integer.parseInt(values.get(0)),values.get(1),values.get(2));
                    if(toReturn.getStudentId() == id){
                        return toReturn;
                    }
                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public ArrayList<Student> getAll(){
        ArrayList<Student> toReturn = new ArrayList<Student>();
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    Student toAdd = new Student(Integer.parseInt(values.get(0)),values.get(1),values.get(2));
                    toReturn.add(toAdd);
                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

   public void delete(Student obj){
        ArrayList<Student> allStudents = getAll();
        allStudents.removeIf(t-> t.getStudentId() == obj.getStudentId());
       try (PrintWriter writer = new PrintWriter(file)) {
           writer.print("");
           for(Student s : allStudents){
               writer.append(s.toCSV());
           }

       }catch(FileNotFoundException e){
           System.out.println(e.getMessage());
       }

   }


}
