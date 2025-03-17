package Lab1.V1;

import java.math.BigDecimal;
import java.util.Scanner;
import java.io.File;

public class MenuHandler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "D:" + File.separator + "Унік" + File.separator + "3 Курс" + File.separator + "2 сем" + File.separator + "Паралельні обчислення і розподілені системи" + File.separator + "Коди" + File.separator + "Lab1" + File.separator + "input_lab1.txt";
        int lastReadN = -1;
        BigDecimal lastComputedSum = null;
        int lastPrecision = -1;
        
        while(true) {
            // Вивід меню користувача
            System.out.println("Menu");
            System.out.println("1. Generate input data");
            System.out.println("2. Read data from file");
            System.out.println("3. Calculate the sum of the elements and display it on the screen");
            System.out.println("4. Save result to a file");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            if (choice == 5) break;

            switch(choice){
                case 1:
                    System.out.print("Enter the number of elements of the row: ");
                    int n = scanner.nextInt();
                    FileManager.generateInputFile(filename, n);
                    System.out.println("Data is saved in " + filename);
                    System.out.println("\n");
                    lastReadN = -1; // Очистити кеш
                    lastComputedSum = null;
                    break;
                case 2:
                    System.out.print("Enter file name to read data from: ");
                    scanner.nextLine();
                    String readFile = scanner.nextLine();
                    String filePath = "D:" + File.separator + "Унік" + File.separator + "3 Курс" + File.separator + "2 сем" + File.separator + "Паралельні обчислення і розподілені системи" + File.separator + "Коди" + File.separator + "Lab1" + File.separator + readFile;
                    int number = FileManager.readInputFile(filePath);
                    if(number > 0){
                        lastReadN = number;
                        lastComputedSum = null;
                        System.out.println("Read: " + number);
                        System.out.println("\n");
                    }
                    break;
                case 3:
                    if(lastReadN == -1) {
                        lastReadN = FileManager.readInputFile(filename);
                    }
                    if(lastReadN > 0) {
                        System.out.print("Enter precision (number of decimal places): ");
                        int precision = scanner.nextInt();
                        if (lastComputedSum == null || lastPrecision != precision) {
                            long start = System.nanoTime();
                            lastComputedSum = HarmonicSumCalculator.calculateHarmonicSum(lastReadN, precision);
                            lastPrecision = precision;
                            long end = System.nanoTime();
                            System.out.println("Elements sum: " + lastComputedSum);
                            System.out.println("Execution time (ns): " + (end - start));
                            System.out.println("\n");
                        } else {
                            System.out.println("Elements sum: " + lastComputedSum);
                            System.out.println("\n");
                        }
                    }
                    break;
                case 4:
                    if(lastComputedSum != null) {
                        System.out.print("Enter a file name to save the results: ");
                        scanner.nextLine();
                        String resultFile = scanner.nextLine();
                        String filePath_result = "D:" + File.separator + "Унік" + File.separator + "3 Курс" + File.separator + "2 сем" + File.separator + "Паралельні обчислення і розподілені системи" + File.separator + "Коди" + File.separator + "Lab1" + File.separator + resultFile;
                        FileManager.saveResultToFile(filePath_result, lastComputedSum);
                        System.out.println("Result was saved in: " + resultFile);
                        System.out.println("\n");
                    } else {
                        System.out.println("Do the calculation first");
                        System.out.println("\n");
                    }
                    break;
                default:
                    System.out.println("Wrong choice! Try again.");
                    System.out.println("\n");
            }
        }
        scanner.close();
    }
}
