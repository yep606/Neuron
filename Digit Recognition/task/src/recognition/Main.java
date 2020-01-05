package recognition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        NetworkTools tools = new NetworkTools();
        Main main = new Main();
        Network net = new Network(15,10);

        double[] inputs;
        double[] inputBias = {-1, 6, 1, 0, 2, 0, -1, 3, -2, -1};
        double[][] weights = tools.gaussWeights();

        System.out.println("Input grid: ");

        inputs = main.takeInput();

        for (int layer = 1; layer < net.NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < net.NETWORK_LAYERS_SIZES[layer]; neuron++) {

                for (int prevNeuron = 0; prevNeuron < net.NETWORK_LAYERS_SIZES[layer - 1]; prevNeuron++) {

                    net.weights[layer][neuron][prevNeuron] = weights[neuron][prevNeuron];

                }
                }
            }

        for (int layer = 1; layer < net.NETWORK_SIZE; layer++) {
            for (int neuron = 0; neuron < net.NETWORK_LAYERS_SIZES[layer]; neuron++) {
                net.bias[layer][neuron] = inputBias[neuron];
            }
        }

        int maxIndex = 0;
        double maxElem = net.calculate(inputs)[0];
        for (int i = 1; i <net.calculate(inputs).length; i++) {
            if(net.calculate(inputs)[i] > maxElem){
                maxElem = net.calculate(inputs)[i];
                maxIndex = i;
            }
        }

        System.out.println("This numbers is: " + maxIndex);
       // System.out.println(Arrays.toString(net.calculate(inputs)));

        }

    private double[] takeInput(){
        Scanner sc = new Scanner(System.in);
        String result = "";
        double[] inputs = new double[15];
        for (int i = 0; i <5 ; i++) {

            result += sc.nextLine();

        }

        for (int i = 0; i < result.length(); i++) {

            if(result.charAt(i) == 'X')
                inputs[i] = 1;
            else if(result.charAt(i) == '_')
                inputs[i] = 0;
        }

        return inputs;
    }

}
