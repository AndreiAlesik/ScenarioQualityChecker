package pl.put.poznan.transformer.logic.filereading;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile implements Readable{
    public static ArrayList<String> read(String filename){
        ArrayList<String> result=new ArrayList<>();
        try {


            File file=new File("files\\"+filename);

            Scanner scanner=new Scanner(file);

            while (scanner.hasNextLine()){
                String data=scanner.nextLine();
                result.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            return result;
        }
    }
}
