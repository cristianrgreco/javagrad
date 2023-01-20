package javagrad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ValueTest {

  @Test
  void addAnotherValue() {
    var a = new Value(1.0f);
    var b = new Value(2.0f);

    assertEquals(new Value(3.0f), a.add(b));
  }

  @Test
  void multiplyAnotherValue() {
    var a = new Value(1.0f);
    var b = new Value(2.0f);

    assertEquals(new Value(2.0f), a.mul(b));
  }

  @Test
  void tanhActivationFunction() {
    var a = new Value(1.0f);

    assertEquals(new Value(0.7615942f), a.tanh());
  }

  /*
   * TODO explain
   */
  @Test
  void gradientForBaseCase() {
    var a = new Value(3.0f);

    a.backward();

    assertEquals(1.0f, a.gradient());
  }

  /*
   * TODO show derivative rules for addition, and how it is applied to the chain rule
   *
   * dc/dc = 1
   * dc/db = 1
   * dc/da = 1
   */
  @Test
  void gradientForAddition() {
    var a = new Value(1.0f);
    var b = new Value(2.0f);
    var c = a.add(b);

    c.backward();

    assertEquals(1.0f, c.gradient());
    assertEquals(1.0f, a.gradient());
    assertEquals(1.0f, b.gradient());
  }

  @Test
  void gradientForAdditionMultivariate() {
    var a = new Value(2.0f);
    var b = new Value(2.0f);
    var c = a.add(b);

    c.backward();

    assertEquals(1.0f, c.gradient());
    assertEquals(2.0f, a.gradient());
    assertEquals(2.0f, b.gradient());
  }

  @Test
  void gradientForMultiplication() {
    var a = new Value(1.0f);
    var b = new Value(2.0f);
    var c = a.mul(b);

    c.backward();

    assertEquals(1.0f, c.gradient());
    assertEquals(2.0f, a.gradient());
    assertEquals(1.0f, b.gradient());
  }

  /*
   * TODO add test to show multivariate case of the chain rule
   * TODO add tanh + explain
   */
}
