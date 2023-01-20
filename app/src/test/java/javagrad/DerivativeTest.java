package javagrad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * A derivative is the rate of change of a function with respect to a variable.
 *
 * In a neural network, we will find the derivatives of the weights and biases, with respect to the loss function.
 * We can then train the neural network to minimize the loss function by adjusting the weights and biases in the
 * direction of the negative gradient (to reduce the loss function).
 *
 * This test suite provides a refresher on calculating derivatives.
 *
 * We will find the derivatives of a, b and c with respect to d for the equation `(a + b) * c = d`.
 */
class DerivativeTest {

  /*
   * dd/da = b
   */
  @Test
  void derivativeOfA() {
    var h = 0.0001f;

    var a = 2.0f;
    var b = -3.0f;
    var c = 10.0f;

    var d1 = a * b + c;
    a = a + h;
    var d2 = a * b + c;

    var slope = (d2 - d1) / h;
    assertEquals(b, slope, 0.01f);
  }

  /*
   * dd/db = a
   */
  @Test
  void derivativeOfB() {
    var h = 0.0001f;

    var a = 2.0f;
    var b = -3.0f;
    var c = 10.0f;

    var d1 = a * b + c;
    b = b + h;
    var d2 = a * b + c;

    var slope = (d2 - d1) / h;
    assertEquals(a, slope, 0.01f);
  }

  /*
   * dd/dc = 1
   */
  @Test
  void derivativeOfC() {
    var h = 0.0001f;

    var a = 2.0f;
    var b = -3.0f;
    var c = 10.0f;

    var d1 = a * b + c;
    c = c + h;
    var d2 = a * b + c;

    var slope = (d2 - d1) / h;
    assertEquals(1.0f, slope, 0.01f);
  }
}
