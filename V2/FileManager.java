package Lab1.V2;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    public void writeToFile(String fileName, String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(content);
        } catch (IOException e) {
            System.out.println("Error during writing to file: " + e.getMessage());
        }
    }

    public int readFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            return fileScanner.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
        } catch (Exception e) {
            System.out.println("Error during reading the file: " + e.getMessage());
        }
        return -1;
    }
}
