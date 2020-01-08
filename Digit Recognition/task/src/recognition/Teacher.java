package recognition;

public class Teacher {

    Network net;
    NetworkTools tools;

    public Teacher(Network net, NetworkTools tools){

        this.net = net;
        this.tools = tools;
        setup();

    }

    public void setup(){

        net.setBias(tools.gaussBias(0, 10));
        net.setWeights(tools.gaussWeights(15, 10));

    }

    public void learn(int generation){

        int index = 0;
        while(index < generation){

            for(int input = 0; input < 10; input++){

                double[] outputs =  net.calculate(tools.idealValues[input]);
                tools.deltaCalc(outputs, input);

            }

            net.setWeights(tools.updateWeights(net.getWeights(), net.NETWORK_SIZE, net.NETWORK_LAYERS_SIZES));
            index++;

        }


    }







}
