package recognition;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NetworkTools tools = new NetworkTools();
        Network net = new Network(15, 10);
        Teacher teacher = new Teacher(net, tools);


        double[] inputs;
        int input;
        int check = 1;

        while (check > 0) {
            System.out.println("1. Learn the network\n" +
                    "2. Guess a number");

            switch (input = sc.nextInt()) {
                case 1:
                    System.out.println("Your choice: " + input);
                    System.out.println("Learning...");
                    teacher.learn(10);

                    try {
                        SerializationUtils.serialize(net,"network.out");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Done! Saved to the file.");
                    break;

                case 2:

                    System.out.println("Your choice: " + input);
                    try {
                       net = (Network) SerializationUtils.deserialize("network.out");
                    } catch (Exception e) {
                        System.out.println("File not found ^(");
                    }
                    System.out.println("Input grid");
                    inputs = takeInput();

                    int maxIndex = 0;
                    double maxElem = net.calculate(inputs)[0];
                    for (int i = 1; i < net.calculate(inputs).length; i++) {
                        if (net.calculate(inputs)[i] > maxElem) {
                            maxElem = net.calculate(inputs)[i];
                            maxIndex = i;
                        }
                    }

                    System.out.println("It's number " + maxIndex);
                    break;

                default:
                    check = 0;
                    break;
            }


        }

//        while(true){
//            System.out.println("Input grid");
//            inputs = takeInput();
//
//            int maxIndex = 0;
//            double maxElem = net.calculate(inputs)[0];
//            for (int i = 1; i < net.calculate(inputs).length; i++) {
//                if (net.calculate(inputs)[i] > maxElem) {
//                    maxElem = net.calculate(inputs)[i];
//                    maxIndex = i;
//                }

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
