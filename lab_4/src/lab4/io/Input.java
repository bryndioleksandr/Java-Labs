package lab4.io;
import java.util.*;
import java.io.*;

public class Input implements DoubleArrayReader {

    @Override
    public double[] readOneDimensionalArray(String fileName) {
        try (Scanner in = new Scanner(new File(fileName))) {
            int n = in.nextInt();
            double[] arr = new double[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = in.nextDouble();
            }
            return arr;
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
    }

    @Override
    public double[] readOneDimensionalArray(File file) {
        try (Scanner in = new Scanner(file)) {
            int n = in.nextInt();
            double[] arr = new double[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = in.nextDouble();
            }
            return arr;
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(File file) {
        // Реалізація методу для зчитування двовимірного масиву з файлу
        // Можна залишити це пустим або додати реальну реалізацію
        return null;
    }

    @Override
    public double[][] readTwoDimensionalArray(String fileName) {
        try (Scanner in = new Scanner(new File(fileName))) {
            int n = in.nextInt();
            double[][] arr = new double[n][n];
            for (int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = in.nextDouble();
                }
            }
            return arr;
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
    }
}
