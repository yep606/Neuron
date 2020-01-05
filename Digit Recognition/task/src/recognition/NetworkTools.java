package recognition;

import java.util.Random;

public class NetworkTools {

    public double[][] gaussWeights(){

        Random random = new Random();
        double[][] gaussNums = new double[10][15];

        for (int layer = 0; layer < 10; layer++) {
            for (int neuron = 0; neuron < 15; neuron++) {
                gaussNums[layer][neuron] = random.nextGaussian();
            }
        }

        return gaussNums;
    }

    public void deltaCalculation(){

        // n * prevNeuron * (neuron(ideal) - neuron(current))


    }

}
