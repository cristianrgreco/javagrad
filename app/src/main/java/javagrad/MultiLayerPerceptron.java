package javagrad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class MultiLayerPerceptron {

  private final Layer[] layers;

  public MultiLayerPerceptron(int numberOfInputs, int[] numberOfNeuronsPerLayer) {
    var numberOfInputsPerLayer = new ArrayList<Integer>();
    numberOfInputsPerLayer.add(numberOfInputs);
    Arrays.stream(numberOfNeuronsPerLayer).forEach(numberOfInputsPerLayer::add);

    this.layers =
        IntStream.range(0, numberOfNeuronsPerLayer.length)
            .mapToObj(
                i -> new Layer(numberOfInputsPerLayer.get(i), numberOfInputsPerLayer.get(i + 1)))
            .toArray(Layer[]::new);
  }

  public Value[] call(double[] inputs) {
    var activations = Arrays.stream(inputs).mapToObj(Value::new).toArray(Value[]::new);

    for (Layer layer : layers) {
      activations = layer.call(activations);
    }

    return activations;
  }
}
