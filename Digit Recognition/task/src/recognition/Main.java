package recognition;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static double[] inputs;

    public static void main(String[] args) {

        NetworkTools tools = new NetworkTools();

        Network net = new Network(15, 10);
        net.setBias(tools.gaussBias(0, 10));
        net.setWeights(tools.gaussWeights(15, 10));

        System.out.println("Learning...");

        int index = 0;
        while(index < 50){

           double[] randomZ =  tools.idealValues[0];//[new Random().nextInt(10)];
           double[] outputs =  net.calculate(randomZ);
           tools.deltaCalc(outputs, randomZ);
            net.setWeights(tools.updateWeights(net.getWeights(), net.NETWORK_SIZE, net.NETWORK_LAYERS_SIZES));
            index++;

        }

        System.out.println("Input grid: ");
        inputs = takeInput();

        int maxIndex = 0;
        double maxElem = net.calculate(inputs)[0];
        for (int i = 1; i < net.calculate(inputs).length; i++) {
            if (net.calculate(inputs)[i] > maxElem) {
                maxElem = net.calculate(inputs)[i];
                maxIndex = i;
            }
        }

        System.out.println("This numbers is: " + maxIndex);
        System.out.println("----");
        System.out.println(Arrays.toString(net.calculate(inputs)));


    }

    private static double[] takeInput() {
        Scanner sc = new Scanner(System.in);
        String result = "";
        double[] inputs = new double[15];
        for (int i = 0; i < 5; i++) {

            result += sc.nextLine();

        }

        for (int i = 0; i < result.length(); i++) {

            if (result.charAt(i) == 'X')
                inputs[i] = 1;
            else if (result.charAt(i) == '_')
                inputs[i] = 0;
        }

        return inputs;
    }

}
