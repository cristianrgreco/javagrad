package javagrad;

public class Main {

  public static void main(String[] args) {
    var inputs = new double[] {2f, 3f, -1f};
    var mlp = new MultiLayerPerceptron(3, new int[] {4, 4, 1});
    System.out.println(mlp.call(inputs));
  }
}