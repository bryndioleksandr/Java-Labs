package lab4.logic;

import java.util.Arrays;
import lab4.logic.ArrayProcessor;

public class Logic {
    public void processArray(double[] array){
        System.out.println(Arrays.toString(array));
    }
    public double calculate(double[] array){
        double p = 1;
        for (double v : array) {
            p *= v;
        }
        return p;
    }

    public double calculate(double[][] array){
        double max = array[0][array.length-1];
        for(int i = 0; i < array.length; i++){
            for(int j = array.length-1; j >= i; j--){
                if(array[i][j] > max){
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    public void processArray(double[][] array){
        System.out.println();
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}
