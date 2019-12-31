package recognition;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int increenent = 0;

        Main main = new Main();
        Network net = new Network(15,10);
        double[] inputs;
        double[] inputBias = {-1,6,1,0,2,0,-1,3,-2,-1};

        System.out.println("Input grid: ");

        inputs = main.takeInput();
        System.out.println("-------------");
        //okay
        for (int layer = 1; layer < net.NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < net.NETWORK_LAYERS_SIZES[layer]; neuron++) {
                for (int prevNeuron = 0; prevNeuron < net.NETWORK_LAYERS_SIZES[layer - 1]; prevNeuron++) {
                    if (inputs[prevNeuron] == 0)
                        net.weights[layer][neuron][prevNeuron] = -1;
                    else
                        net.weights[layer][neuron][prevNeuron] = 1;
                }
                }
            }

        for (int layer = 1; layer < net.NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < net.NETWORK_LAYERS_SIZES[layer]; neuron++) {
                net.bias[layer][neuron] = inputBias[neuron];

            }
        }

        System.out.println("This numbers is: " + Arrays.toString(net.calculate(inputs)));

//        System.out.println("yep");
//        for (int i = 1; i < 2; i++) {
//            for (int j = 1; j < 2 ; j++) {
//                for (int m = 0; m < 15; m ++)
//                    System.out.println(net.weights[i][j][m]);
//            }
//        }

        }

    private double[] takeInput(){
        Scanner sc = new Scanner(System.in);
        String result = "";
        double[] inputs = new double[15];
        for (int i = 0; i <5 ; i++) {

            result += sc.nextLine();

        }

        for (int i = 0; i <result.length(); i++) {

            if(result.charAt(i) == 'X')
                inputs[i] = 1;
            else
                inputs[i] = 0;
        }

        return inputs;
    }

}
