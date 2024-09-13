package lab4;
import lab4.io.DoubleArrayReader;
import lab4.io.Input;
import lab4.logic.ArrayProcessor;
import lab4.logic.Logic;


public class Main {
    public static void main(String[] args) {
        Input io = new Input();
        Logic logic = new Logic();
        double[] oneDimensionalArray = io.readOneDimensionalArray("lab41.txt");
        logic.processArray(oneDimensionalArray);
        System.out.print("The product of each element is: " + logic.calculate(oneDimensionalArray));
        double[][] twoDimensionalArray = io.readTwoDimensionalArray("lab42.txt");
        logic.processArray(twoDimensionalArray);
        System.out.print("The max element of second array is: " + logic.calculate(twoDimensionalArray));
    }
}