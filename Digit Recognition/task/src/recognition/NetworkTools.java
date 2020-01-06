package recognition;

import java.util.Random;

public class NetworkTools {

    private final double RATE_COEFFICIENT = 0.5;
    double[] mean;
    Random random = new Random();

    public double[][] idealValues = new double[][]{{1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, 1}, //0
            {-1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1}, //1
            {1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1}, //2
            {1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1}, //3
            {1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1}, //4
            {1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, 1, 1}, //5
            {1, 1, 1, 1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1}, //6
            {1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1}, //7
            {1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1}, //8
            {1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1}}; //9

    public double[][][] gaussWeights(int... layerSizes) {

        double[][][] gaussNums = new double[layerSizes.length][][];

        for (int i = 1; i < layerSizes.length; i++) {

            gaussNums[i] = new double[layerSizes[i]][layerSizes[i - 1]];

        }

        for (int layer = 1; layer < gaussNums.length; layer++) {
            for (int neuron = 0; neuron < layerSizes[layer]; neuron++) {

                for (int prevNeuron = 0; prevNeuron < layerSizes[layer - 1]; prevNeuron++) {

                    gaussNums[layer][neuron][prevNeuron] = random.nextGaussian();

                }
            }
        }
        return gaussNums;
    }

    public double[][] gaussBias(int... biases) {

        double[][] bias = new double[biases.length][biases[1]];

        for (int i = 0; i < biases.length; i++) {
            for (int j = 0; j < biases[i]; j++) {
                bias[i][j] = random.nextGaussian();
            }
        }

        return bias;

    }

    public void deltaCalc(double[] output, double[] randomz) {

        double result = 0;
        mean = new double[output.length];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < randomz.length; j++) {

                result += RATE_COEFFICIENT * randomz[j] * (idealValues[i][j] - output[i]);

            }
            result /= 10;
            mean[i] = result;
        }


    }

    public double[][][] updateWeights(double[][][] weights) {

                                                    // layer neuron prevNeuron
//        System.out.println("Lenghts:");
//        System.out.println(weights.length);
//        System.out.println(weights[1][1].length);

        for (int layer = 1; layer < weights.length; layer++) {
            for (int neuron = 0; neuron < weights[layer].length; neuron++) {

                for (int prevNeuron = 0; prevNeuron < weights[layer][layer - 1].length; prevNeuron++) {
                    weights[layer][neuron][prevNeuron] += mean[neuron];
                }

            }

        }

        return weights;

    }
}

        // n * prevNeuron * (neuron(ideal) - neuron(current))
        // w = w^n + w^mean





