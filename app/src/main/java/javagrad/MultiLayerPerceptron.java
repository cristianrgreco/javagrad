package javagrad;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MultiLayerPerceptron {

  private final List<Layer> layers;

  public MultiLayerPerceptron(int numberOfInputs, int[] numberOfNeuronsPerLayer) {
    var numberOfInputsPerLayer = numberOfInputsPerLayer(numberOfInputs, numberOfNeuronsPerLayer);

    this.layers =
        IntStream.range(0, numberOfNeuronsPerLayer.length)
            .mapToObj(
                i -> {
                  var numberOfInputs1 = numberOfInputsPerLayer.get(i);
                  var numberOfNeurons = numberOfInputsPerLayer.get(i + 1);
                  return new Layer(numberOfInputs1, numberOfNeurons);
                })
            .toList();
  }

  public List<Value> call(double[] inputs) {
    var activations = Arrays.stream(inputs).mapToObj(Value::new).toList();

    for (Layer layer : layers) {
      activations = layer.call(activations);
    }

    return activations;
  }

  private List<Integer> numberOfInputsPerLayer(int numberOfInputs, int[] numberOfNeuronsPerLayer) {
    return IntStream.concat(IntStream.of(numberOfInputs), Arrays.stream(numberOfNeuronsPerLayer))
        .boxed()
        .toList();
  }
}
