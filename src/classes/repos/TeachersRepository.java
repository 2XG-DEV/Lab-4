package classes.repos;


import classes.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeachersRepository {

    private static TeachersRepository instance = null;
    private File file;

    private TeachersRepository(){
        file =  new File("teachers.csv");
    }

    public static TeachersRepository getInstance(){
        if(instance == null)
            instance = new TeachersRepository();
        return instance;
    }

    public void create(Teacher obj){
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

    public Teacher getOne(int id){
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    Teacher toReturn = new Teacher(Integer.parseInt(values.get(0)),values.get(1),values.get(2));
                    if(toReturn.getTeacherId() == id){
                        return toReturn;
                    }
                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public ArrayList<Teacher> getAll(){
        ArrayList<Teacher> toReturn = new ArrayList<Teacher>();
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                    Teacher toAdd = new Teacher(Integer.parseInt(values.get(0)),values.get(1),values.get(2));
                    toReturn.add(toAdd);
                }

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

    public void delete(Teacher obj){
        ArrayList<Teacher> allTeachers = getAll();
        allTeachers.removeIf(t-> t.getTeacherId() == obj.getTeacherId());
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
            for(Teacher s : allTeachers){
                writer.append(s.toCSV());
            }

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

}
