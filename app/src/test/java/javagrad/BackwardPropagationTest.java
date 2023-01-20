package javagrad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BackwardPropagationTest {

  /** <img src="../../../../../graph2.png"/> */
  @Test
  void backwardPropagation() {

    // Inputs
    var x1 = new Value(2.0f);
    var x2 = new Value(0.0f);

    // Weights
    var w1 = new Value(-3.0f);
    var w2 = new Value(1.0f);

    // Bias
    var b = new Value(6.8813735870195432f);

    // Computational graph
    var x1w1 = x1.mul(w1);
    var x2w2 = x2.mul(w2);
    var x1w1x2w2 = x1w1.add(x2w2);
    var n = x1w1x2w2.add(b);
    var o = n.tanh();

    // Backward propagation
    o.backward();

    // Assert values
    assertEquals(0.7071, o.data(), 0.01f);
    assertEquals(0.8814f, n.data(), 0.01f);
    assertEquals(-6f, x1w1x2w2.data(), 0.01f);
    assertEquals(6.8814f, b.data(), 0.01f);
    assertEquals(0f, x2w2.data(), 0.01f);
    assertEquals(-6f, x1w1.data(), 0.01f);
    assertEquals(1f, w2.data(), 0.01f);
    assertEquals(0f, x2.data(), 0.01f);
    assertEquals(2f, x1.data(), 0.01f);
    assertEquals(-3f, w1.data(), 0.01f);

    // Assert gradients
    assertEquals(1f, o.gradient(), 0.01f);
    assertEquals(0.5f, n.gradient(), 0.01f);
    assertEquals(0.5f, x1w1x2w2.gradient(), 0.01f);
    assertEquals(0.5f, b.gradient(), 0.01f);
    assertEquals(0.5f, x2w2.gradient(), 0.01f);
    assertEquals(0.5f, x1w1.gradient(), 0.01f);
    assertEquals(-1.5f, x1.gradient(), 0.01f);
    assertEquals(0.5f, x2.gradient(), 0.01f);
    assertEquals(1f, w1.gradient(), 0.01f);
    assertEquals(0f, w2.gradient(), 0.01f);
  }
}
