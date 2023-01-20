package javagrad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ValueTest {

  @Test
  void add() {
    var a = new Value(2);
    var b = new Value(3);

    var result = a.add(b);

    assertEquals(5, result.data());
  }

  @Test
  void sub() {
    var a = new Value(2);
    var b = new Value(3);

    var result = a.sub(b);

    assertEquals(-1, result.data());
  }

  @Test
  void mul() {
    var a = new Value(2);
    var b = new Value(3);

    var result = a.mul(b);

    assertEquals(6, result.data());
  }

  @Test
  void pow() {
    var a = new Value(2);

    var result = a.pow(3);

    assertEquals(8, result.data());
  }
}
