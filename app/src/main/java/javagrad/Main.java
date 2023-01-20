package javagrad;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    var mlp = new MultiLayerPerceptron(3, new int[] {4, 4, 1});

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

    var outputBeforeTraining = Arrays.stream(inputs).map(mlp::call).toList();
    System.out.println("\nOutput before training:");
    outputBeforeTraining.forEach(System.out::println);

    var losses =
        IntStream.range(0, inputs.length)
            .mapToDouble(
                i -> {
                  var expected = desired[i];
                  var actual = outputBeforeTraining.get(i).get(0).data();
                  return Math.pow((actual - expected), 2);
                })
            .toArray();

    System.out.println("\nLoss using mean squared error loss: ");
    Arrays.stream(losses).forEach(System.out::println);

    var loss = Arrays.stream(losses).sum();
    System.out.println("\nTotal loss:");
    System.out.println(loss);

    System.out.println("\nThe neural network has the following number of weights and biases:");
    System.out.println(mlp.parameters().size());

    /*
    Need to implement other operations in the Value class such as exp, sub, div, etc.
    That way can add the loss function to the computational graph and perform backward propagation on the loss.
    Because what we want is for each node's gradient to be the derivative of the loss, such that we can nudge the
    gradient of the nodes in the opposite direction of the loss, to reduce the loss (gradient descent). Once done,
    create training loop showing the loss at each step.
     */
  }
}
