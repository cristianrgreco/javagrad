package javagrad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(of = {"data", "gradient"})
@EqualsAndHashCode(of = {"data", "gradient"})
public class Value {

  private final float data;
  private final Value[] children;
  private float gradient = 0.0f;
  private Runnable backward = () -> {};

  public Value(float data) {
    this.data = data;
    this.children = new Value[0];
  }

  private Value(float data, Value[] children) {
    this.data = data;
    this.children = children;
  }

  public Value add(Value anotherValue) {
    var result = new Value(this.data + anotherValue.data, new Value[]{this, anotherValue});

    backward = () -> {
      this.gradient += result.gradient;
      anotherValue.gradient += result.gradient;
    };

    return result;
  }

  public Value mul(Value anotherValue) {
    var result = new Value(this.data * anotherValue.data, new Value[]{this, anotherValue});

    backward = () -> {
      this.gradient += anotherValue.data * result.gradient;
      anotherValue.gradient += this.data * result.gradient;
    };

    return result;
  }

  public Value tanh() {
    var result = new Value((float) Math.tanh(this.data), new Value[]{this});

    backward = () -> {
      this.gradient += (1 - result.data * result.data) * result.gradient;
    };

    return result;
  }

  public void backward() {
    this.gradient = 1.0f;
    var values = topologicalSort();
    Collections.reverse(values);
    values.forEach(Value::backwardInternal);
  }

  public void backwardInternal() {
    this.backward.run();
  }

  private List<Value> topologicalSort() {
    var visited = new HashSet<Value>();
    var stack = new ArrayList<Value>();
    var result = new ArrayList<Value>();

    stack.add(this);

    while (!stack.isEmpty()) {
      var current = stack.remove(stack.size() - 1);

      if (visited.contains(current)) {
        continue;
      }

      visited.add(current);
      result.add(current);

      Collections.addAll(stack, current.children);
    }

    return result;
  }

  public float gradient() {
    return gradient;
  }

  public float data() {
    return data;
  }
}
