package pl.put.poznan.transformer.logic.filereading;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile implements Readable{

    public static ArrayList<String> read(String file)
    {
        ArrayList<String> result = new ArrayList<>();
        try
        {
            File myFile = new File("files\\"+file);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                result.add(data);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
