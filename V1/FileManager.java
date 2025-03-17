package Lab1.V1;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class FileManager {

    // Функція запису к-сті елементів у файл
    public static void generateInputFile(String fileName, int n) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(n);
        } catch (IOException e) {
            System.out.println("Error during writing to file: " + e.getMessage());
        }
    }

    // Функція зчитування елементів з файлу
    public static int readInputFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            return fileScanner.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
        } catch (Exception e) {
            System.out.println("Error during reading the file: " + e.getMessage());
        }
        return -1;
    }

    // Функція збереження елементів у файл
    public static void saveResultToFile(String fileName, BigDecimal sum) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(sum);
        } catch (IOException e) {
            System.out.println("Error during writing to file: " + e.getMessage());
        }
    }
}
