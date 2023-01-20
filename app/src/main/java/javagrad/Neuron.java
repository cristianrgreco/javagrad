package javagrad;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Neuron {

  private final int numberOfInputs;
  private final Value bias;
  private final List<Value> weights;

  public Neuron(int numberOfInputs) {
    this.numberOfInputs = numberOfInputs;
    this.bias = new Value(randomNumberBetween(-1, 1));
    this.weights =
        IntStream.range(0, numberOfInputs)
            .mapToObj(i -> new Value(randomNumberBetween(-1, 1)))
            .toList();
  }

  public Value call(List<Value> inputs) {
    var activation =
        IntStream.range(0, this.numberOfInputs)
            .mapToDouble(
                i -> {
                  var input = inputs.get(i);
                  var weight = this.weights.get(i);
                  return input.data() * weight.data() + this.bias.data();
                })
            .sum();

    return new Value(activation).tanh();
  }

  private double randomNumberBetween(double start, double end) {
    return ThreadLocalRandom.current().nextDouble(start, end);
  }
}
