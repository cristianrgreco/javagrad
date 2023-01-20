package javagrad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lombok.ToString;

@ToString(of = {"data"})
public class Value {

  private final double data;
  private final Value[] children;
  private double gradient = 0f;
  private Runnable backward = () -> {};

  public Value(double data) {
    this.data = data;
    this.children = new Value[0];
  }

  private Value(double data, Value[] children) {
    this.data = data;
    this.children = children;
  }

  public Value add(Value anotherValue) {
    var result = new Value(this.data + anotherValue.data, new Value[]{this, anotherValue});

    result.backward = () -> {
      this.gradient += result.gradient;
      anotherValue.gradient += result.gradient;
    };

    return result;
  }

  public Value mul(Value anotherValue) {
    var result = new Value(this.data * anotherValue.data, new Value[]{this, anotherValue});

    result.backward = () -> {
      this.gradient += anotherValue.data * result.gradient;
      anotherValue.gradient += this.data * result.gradient;
    };

    return result;
  }

  public Value tanh() {
    var result = new Value(Math.tanh(this.data), new Value[]{this});

    result.backward = () -> {
      this.gradient += (1 - result.data * result.data) * result.gradient;
    };

    return result;
  }

  public void backward() {
    var values = topologicalSort();
    Collections.reverse(values);
    this.gradient = 1f;
    values.forEach(Value::backwardInternal);
  }

  public void backwardInternal() {
    this.backward.run();
  }

  private List<Value> topologicalSort() {
    var values = new ArrayList<Value>();
    var visited = new HashSet<Value>();
    dfs(this, values, visited);
    return values;
  }

  private void dfs(Value current, List<Value> values, HashSet<Value> visited) {
    if (visited.contains(current)) {
      return;
    }

    visited.add(current);
    for (var child : current.children) {
      dfs(child, values, visited);
    }
    values.add(current);
  }

  public double gradient() {
    return gradient;
  }

  public double data() {
    return data;
  }
}
