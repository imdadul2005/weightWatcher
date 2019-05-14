package Question;

import java.io.File;
import java.util.Scanner;

/**
 * @author Imdadul Hoq
 */
public class Question1 {

    static String path = System.getProperty("user.dir")+"\\src\\test\\Resource\\Dictionary.txt";
    static Scanner in;

    public static void main( String[] args ){
        dictionaryReader();
    }

    /**
     * This method reads the file and printout the result
     */
    public static void dictionaryReader() {
        if (doesFileExist(path)){
            while (in.hasNextLine()){
                String line = in.nextLine();
                // Split the word and based on either '–' and a followed by space or ',' and a followed by space
                String [] lineSplitter = line.split("\\–\\s|\\,\\s");
                print(lineSplitter);
            }
        }
        else
            System.out.println("File not found");

    }

    /**
     * Print an array of String
     * @param lineSplitter Read a string array and print to the display
     */
    public static void print(String[] lineSplitter){
        for (String word:lineSplitter) {
            System.out.println(word);
        }
    }

    /**
     * Check if a file exists
     * @param path path of the file
     * @return return true if the file found otherview return false
     */
    public static boolean doesFileExist(String path){
        try {
            in = new Scanner(new File(path));
            return true;
        }catch (Exception ez) {
            return false;
        }
    }
}
