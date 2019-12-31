package recognition;

public class Network {

    double[][] outputs;
    double[][][] weights;
    double[][] bias;



    private final int[] NETWORK_LAYERS_SIZES;
    private final int INPUT_SIZE;
    private final int OUTPUT_SIZE;
    private final int NETWORK_SIZE;

    public Network(int... NETWORK_LAYERS_SIZES) {

        this.NETWORK_LAYERS_SIZES = NETWORK_LAYERS_SIZES;
        this.INPUT_SIZE = NETWORK_LAYERS_SIZES[0];
        this.NETWORK_SIZE = NETWORK_LAYERS_SIZES.length;
        this.OUTPUT_SIZE = NETWORK_LAYERS_SIZES[NETWORK_SIZE - 1];

        this.outputs = new double[NETWORK_SIZE][];
        //layer : neuron : weight neuron
        this.weights = new double[NETWORK_SIZE][][];
        this.bias = new double[NETWORK_SIZE][];

        for(int i = 0; i < NETWORK_SIZE; i++){

            this.outputs[i] = new double[NETWORK_LAYERS_SIZES[i]];
            this.bias[i] = new double[NETWORK_LAYERS_SIZES[i]];

            if(i > 0)
                this.weights[i] = new double[NETWORK_LAYERS_SIZES[i]][NETWORK_LAYERS_SIZES[i - 1]];
        }

    }

    public double[] calculate(double... input){

        if(input.length != INPUT_SIZE) return null;

        this.outputs[0] = input;

        return new double[2];

    }

    public static void main(String[] args) {

    }


}
