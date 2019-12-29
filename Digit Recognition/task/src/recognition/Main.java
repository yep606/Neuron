package recognition;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] weights = {2, 1, 2, 4, -4, 4, 2, -1, 2, -5};
        int[] input = new int[9];
        String result = "";

        System.out.println("Input grid: ");
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i <3 ; i++)
            result += sc.nextLine();

        char[] arrRes = result.toCharArray();

        for (int i = 0; i <input.length; i++) {

            if(arrRes[i] == '_')
                input[i] = 0;
            else
                input[i] = 1;
        }

        if(new Main().result(weights, input) > 0 )
            System.out.println("This number is 0" );
        else
            System.out.println("This number is 1");



    }

    int result(int[] weight, int[] input){

        int result = weight[weight.length - 1];

        for (int i = 0; i < input.length; i++) {
            result += weight[i] * input[i];
        }

        return result;

    }


}
