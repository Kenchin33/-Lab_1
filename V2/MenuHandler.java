
package Lab1.V2;

import java.math.BigDecimal;
import java.util.Scanner;
import java.io.File;

public class MenuHandler {
    private Scanner scanner;
    private String filename;
    private int lastReadN;
    private BigDecimal lastComputedSum;
    private int lastPrecision;
    private FileManager fileManager;
    private HarmonicCalculator calculator;

    public MenuHandler() {
        scanner = new Scanner(System.in);
        filename = "D:" + File.separator + "Унік" + File.separator + "3 Курс" + File.separator + "2 сем" + File.separator + "Паралельні обчислення і розподілені системи" + File.separator + "Коди" + File.separator + "Lab1" + File.separator + "input_lab1.txt";
        lastReadN = -1;
        lastComputedSum = null;
        lastPrecision = -1;
        fileManager = new FileManager();
        calculator = new HarmonicCalculator();
    }

    public void start() {
        while (true) {
            System.out.println("Menu");
            System.out.println("1. Generate input data");
            System.out.println("2. Read data from file");
            System.out.println("3. Calculate the sum of the elements and display it on the screen");
            System.out.println("4. Save result to a file");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            if (choice == 5) break;

            switch (choice) {
                case 1:
                    generateInputData();
                    break;
                case 2:
                    readDataFromFile();
                    break;
                case 3:
                    calculateAndDisplaySum();
                    break;
                case 4:
                    saveResultToFile();
                    break;
                default:
                    System.out.println("Wrong choice! Try again.");
            }
        }
        scanner.close();
    }

    private void generateInputData() {
        System.out.print("Enter the number of elements of the row: ");
        int n = scanner.nextInt();
        fileManager.writeToFile(filename, String.valueOf(n));
        System.out.println("Data is saved in " + filename);
        System.out.println("\n");
        lastReadN = -1;
        lastComputedSum = null;
    }

    private void readDataFromFile() {
        System.out.print("Enter file name to read data from: ");
        scanner.nextLine();
        String readFile = scanner.nextLine();
        String filePath = "D:" + File.separator + "Унік" + File.separator + "3 Курс" + File.separator + "2 сем" + File.separator + "Паралельні обчислення і розподілені системи" + File.separator + "Коди" + File.separator + "Lab1" + File.separator + readFile;
        int number = fileManager.readFromFile(filePath);
        if (number > 0) {
            lastReadN = number;
            lastComputedSum = null;
            System.out.println("Read: " + number);
            System.out.println("\n");
        }
    }

    private void calculateAndDisplaySum() {
        if (lastReadN == -1) {
            lastReadN = fileManager.readFromFile(filename);
        }
        if (lastReadN > 0) {
            System.out.print("Enter precision (number of decimal places): ");
            int precision = scanner.nextInt();
            if (lastComputedSum == null || lastPrecision != precision) {
                long start = System.nanoTime();
                lastComputedSum = calculator.computeHarmonicSum(lastReadN, precision);
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
    }

    private void saveResultToFile() {
        if (lastComputedSum != null) {
            System.out.print("Enter a file name to save the results: ");
            scanner.nextLine();
            String resultFile = scanner.nextLine();
            String filePath = "D:" + File.separator + "Унік" + File.separator + "3 Курс" + File.separator + "2 сем" + File.separator + "Паралельні обчислення і розподілені системи" + File.separator + "Коди" + File.separator + "Lab1" + File.separator + resultFile;
            fileManager.writeToFile(filePath, lastComputedSum.toString());
            System.out.println("Result was saved in: " + resultFile);
            System.out.println("\n");
        } else {
            System.out.println("Do the calculation first");
            System.out.println("\n");
        }
    }
}

