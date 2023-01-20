package javagrad;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    return IntStream.range(0, this.numberOfInputs)
        .mapToObj(
            i -> {
              var input = inputs.get(i);
              var weight = weights.get(i);
              return weight.mul(input).add(bias);
            })
        .reduce(Value::add)
        .orElseThrow()
        .tanh();
  }

  public List<Value> parameters() {
    return Stream.concat(this.weights.stream(), Stream.of(this.bias)).toList();
  }

  private double randomNumberBetween(double start, double end) {
    return ThreadLocalRandom.current().nextDouble(start, end);
  }
}
