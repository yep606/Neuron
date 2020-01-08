package recognition;


import java.sql.SQLOutput;
import java.util.Random;

public class NetworkTools {

    private final double RATE_COEFFICIENT = 0.5;

    private double[][] middle = new double[10][15];

    public double[][] idealValues = new double[][]{{1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}, //0
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, //1
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, //2
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, //3
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1}, //4
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, //5
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1}, //6
            {1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, //7
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, //8
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}}; //9

    double[][][] gaussWeights(int... layerSizes) {

        double[][][] gaussNums = new double[layerSizes.length][][];

        for (int i = 1; i < layerSizes.length; i++) {

            gaussNums[i] = new double[layerSizes[i]][layerSizes[i - 1]];

        }

        for (int layer = 1; layer < gaussNums.length; layer++) {
            for (int neuron = 0; neuron < layerSizes[layer]; neuron++) {

                for (int prevNeuron = 0; prevNeuron < layerSizes[layer - 1]; prevNeuron++) {

                    gaussNums[layer][neuron][prevNeuron] = 0;//new Random().nextGaussian();

                }
            }
        }
        return gaussNums;
    }

    double[][] gaussBias(int... biases) {

        double[][] bias = new double[biases.length][biases[1]];

        for (int i = 0; i < biases.length; i++) {
            for (int j = 0; j < biases[i]; j++) {
                bias[i][j] = 0; //new Random().nextGaussian();

            }
        }

        return bias;

    }

    void deltaCalc(double[] output, int input) {
        double deltaW;
        double[][][] mean = new double[output.length][10][15];

        for (int out = 0; out < output.length; out++) {
            for (int cell = 0; cell < 15; cell++) {

                deltaW = RATE_COEFFICIENT * idealValues[input][cell] * (idealValues[out][cell] - output[out]);

                mean[out][input][cell] = deltaW;

            }
        }

        for (int out = 0; out < output.length; out++) {
            for (int weight = 0; weight < 15; weight++) {
                for (int num = 0; num < 10; num++) {

                    middle[out][weight] += mean[out][num][weight] / 10;

                }
            }
        }
    }

    double[][][] updateWeights(double[][][] weights, int netSize, int[] netLayerSize) {

        for (int layer = 1; layer < netSize; layer++) {
            for (int neuron = 0; neuron < netLayerSize[layer]; neuron++) {

                for (int prevNeuron = 0; prevNeuron < netLayerSize[layer - 1]; prevNeuron++) {

                    weights[layer][neuron][prevNeuron] += middle[neuron][prevNeuron];

                }

            }
        }

        return weights;

    }
}

        // n * prevNeuron * (neuron(ideal) - neuron(current))
        // w = w^n + w^mean





