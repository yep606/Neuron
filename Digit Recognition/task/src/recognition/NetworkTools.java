package recognition;

import java.util.Random;

public class NetworkTools {

    private final double RATE_COEFF = 0.5;
    double[] means;
    double[][][] weights;

    private int[][] idealValues = new int[][]{{1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, 1}, //0
            {-1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1}, //1
            {1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1}, //2
            {1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1}, //3
            {1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1}, //4
            {1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, 1, 1}, //5
            {1, 1, 1, 1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1}, //6
            {1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1}, //7
            {1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1}, //8
            {1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1}}; //9

    public double[][] gaussWeights() {

        Random random = new Random();
        double[][] gaussNums = new double[10][15];

        for (int layer = 0; layer < 10; layer++) {
            for (int neuron = 0; neuron < 15; neuron++) {
                gaussNums[layer][neuron] = random.nextGaussian();
            }
        }

        return gaussNums;
    }

    public double[][][] deltaCalculation(int length, int networkSize, int[] layerSizes, int[] inputs, double[] outputs) {

        double result = 0.0;
        weights = new double[length][][];
        means = new double[10];

        for (int layer = 1; layer < networkSize; layer++) {
            for (int neuron = 0; neuron <layerSizes[layer]; neuron++) {

                for (int prevNeuron = 0; prevNeuron < layerSizes[layer - 1] ; prevNeuron++) {

                    weights[layer][neuron][prevNeuron] = RATE_COEFF * inputs[prevNeuron] * (idealValues[neuron][prevNeuron] - outputs[neuron]);
                    means[neuron] += weights[layer][neuron][prevNeuron];
                }
                means[neuron] /= 10;

            }
        }

        return weights;

    }

    public void updateWeights(){



    }

}

        // n * prevNeuron * (neuron(ideal) - neuron(current))
        // w = w^n + w^mean





