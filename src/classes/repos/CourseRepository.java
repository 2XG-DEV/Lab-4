package classes.repos;

import classes.Course;
import classes.Student;
import classes.Course;
import classes.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRepository{

    private static CourseRepository instance = null;
    private File file;

    private CourseRepository(){
         file = new File("courses.csv");
    }

    public static CourseRepository getInstance(){
        if(instance == null){
            instance = new CourseRepository();
        }
        return instance;
    }


    public void create(Course obj){
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

    public ArrayList<Course> getAll(){
        ArrayList<Course> toReturn = new ArrayList<Course>();
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    if(values.size() > 0){
                        Course toAdd = new Course(Integer.parseInt(values.get(0)),values.get(1),TeachersRepository.getInstance().getOne(Integer.parseInt(values.get(2))),Integer.parseInt(values.get(3)),Integer.parseInt(values.get(4)));
                        toReturn.add(toAdd);
                    }

                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

    public Course getOne(int id){
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    Course toReturn = new Course(Integer.parseInt(values.get(0)),values.get(1),TeachersRepository.getInstance().getOne(Integer.parseInt(values.get(2))),Integer.parseInt(values.get(3)),Integer.parseInt(values.get(4)));
                    if(toReturn.getCourseId() == id){
                        return toReturn;
                    }
                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void delete(Course obj){
        ArrayList<Course> allCourses = getAll();
        allCourses.removeIf(t-> t.getCourseId() == obj.getCourseId());
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
            for(Course s : allCourses){
                writer.append(s.toCSV());
            }

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    
}
