package javagrad;

import java.util.List;
import java.util.stream.IntStream;

public class Layer {

  private final List<Neuron> neurons;

  public Layer(int numberOfInputs, int numberOfNeurons) {
    this.neurons =
        IntStream.range(0, numberOfNeurons).mapToObj(i -> new Neuron(numberOfInputs)).toList();
  }

  public List<Value> call(List<Value> inputs) {
    return this.neurons.stream().map(neuron -> neuron.call(inputs)).toList();
  }
}
