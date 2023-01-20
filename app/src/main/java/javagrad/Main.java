package javagrad;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    var mlp = new MultiLayerPerceptron(3, new int[] {4, 4, 1});

    System.out.println(
        "\nThe neural network has the following number of parameters (weights + biases):");
    System.out.println(mlp.parameters().size());

    var inputs =
        new double[][] {
          {2f, 3f, -1f},
          {3f, -1f, 0.5f},
          {0.5f, 1f, 1f},
          {1f, 1f, -1f}
        };

    var desired = new double[] {1f, -1f, -1f, 1f};
    System.out.println("\nDesired output: ");
    Arrays.stream(desired).forEach(System.out::println);

    System.out.println("\nPre-trained output:");
    Arrays.stream(inputs).map(mlp::call).forEach(System.out::println);

    // Training loop
    System.out.println("\nLoss values at different training iterations:");
    for (int i = 0; i < 1000; i++) {
      // Forward pass
      var predictions = Arrays.stream(inputs).map(mlp::call).toList();
      var losses =
          IntStream.range(0, inputs.length)
              .mapToObj(
                  j -> {
                    var expected = desired[j];
                    var actual = predictions.get(j).get(0);
                    return actual.sub(new Value(expected)).pow(2); // mean squared error loss
                  })
              .toList();
      var loss = losses.stream().reduce(Value::add).orElseThrow();

      // Backward pass
      mlp.parameters().forEach(Value::resetGradient);
      loss.backward();

      // Update
      mlp.parameters()
          .forEach(
              parameter -> {
                var newData = parameter.data() + ((-0.01) * parameter.gradient());
                parameter.setData(newData);
              });

      if (i < 50 || i % 50 == 0) {
        System.out.println(i + " " + loss.data());
      }
    }

    System.out.println("\nTrained output:");
    Arrays.stream(inputs).map(mlp::call).forEach(System.out::println);
  }
}
