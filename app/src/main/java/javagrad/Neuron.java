package javagrad;

import java.util.stream.IntStream;

public class Neuron {

  private final int numberOfInputs;
  private final Value bias;
  private final Value[] weights;

  public Neuron(int numberOfInputs) {
    this.numberOfInputs = numberOfInputs;
    this.bias = new Value(Math.random() * 2 - 1);
    this.weights =
        IntStream.range(0, numberOfInputs)
            .mapToObj(i -> new Value(Math.random() * 2 - 1))
            .toArray(Value[]::new);
  }

  public Value call(Value[] inputs) {
    var activation =
        IntStream.range(0, this.numberOfInputs)
            .mapToDouble(
                i -> {
                  var input = inputs[i];
                  var weight = this.weights[i];
                  return input.data() * weight.data() + this.bias.data();
                })
            .sum();

    return new Value(activation).tanh();
  }
}
