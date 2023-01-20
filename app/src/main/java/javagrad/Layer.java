package javagrad;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Layer {

  private final Neuron[] neurons;

  public Layer(int numberOfInputs, int numberOfNeurons) {
    this.neurons =
        IntStream.range(0, numberOfNeurons)
            .mapToObj(i -> new Neuron(numberOfInputs))
            .toArray(Neuron[]::new);
  }

  public Value[] call(Value[] inputs) {
    return Arrays.stream(this.neurons).map(neuron -> neuron.call(inputs)).toArray(Value[]::new);
  }
}
