package classes.repos;


import interfaces.ICrudRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class FileRepository <T> implements ICrudRepository<T> {
    protected File file;

    public FileRepository() {
        this.file = new File("file.csv");
    }

    @Override
    public T create(T obj){
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.append(obj.toString());

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ArrayList<T> getAll(){
        ArrayList<T> toReturn = new ArrayList<T>();
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(scanner.nextLine())){
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()){
                        values.add(rowScanner.next());
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

    @Override
    public void delete(T obj){
        ArrayList<T> allTs = getAll();
        allTs.removeIf(t-> t == obj);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
            for(T s : allTs){
                writer.append(s.toString());
            }

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

}
