package javagrad;

import org.junit.jupiter.api.Test;

class DerivativeChainRuleTest {

  @Test
  void x() {
    var h = 0.0001f;

    var a = 2.0f;
    var b = -3.0f;
    var c = 10.0f;
    var e = a * b;
    var d = e + c;
    var f = -2.0f;
    var L = d * f;

    /*
      dL/dL = 1 (base case, any increase is proportional to the increase in L)

      dL/dd = ???
      L = d * f
      ( f( x+h ) - f(x)) / h
      ( (d+h)*f) - d*f ) / h
      ( d*f + h*f - d*f ) / h
      ( h*f ) / h
      f
      => dL/dd = -2

      dL/df = ???
      L = d * f
      (f(x+h)-f(x))/h
      ((f+h)*d - d*f) / h
      (d*f + h*d - d*f) / h
      (h*d) / h
      d
      => dL/df = 4

      We could have found this value by symmetry (L = d * f == L = f * d)

      dd/dc = ???
      d = c + e
      (f(x+h)-f(x))/h
      ((c+h + e) - (c+e))/h
      (c+h+e-c-e)/h
      h/h
      1
      => dd/dc = 1

      By symmetry (d = c + e == d = e + c) we can conclude that dd/de is also 1.
      You see that the derivative of a sum expression is always 1.
      You can therefore look at a plus node as something that routes a derivative to child nodes.

      To figure out dL/dc, we can apply the chain rule, which is simply the product of the derivatives.
      dL/dc = dL/dd * dd/dc

      The chain for dL/dc is dL/dd->dL/dc.
      dL/dd = -2, dL/dc = 1
      => dL/dc = -2 * 1 = -2

      Similarly, to figure out dL/da:
      dL/da = (dL/de) * (de/da)

      de/da = ???
      e = a * b
      (f(x+h)-f(x))/h
      (((a+h)*b) - (a*b)) / h
      (a*b + h*b - a*b) / h
      (h*b)/h
      b
      => de/da = -3

      By symmetry, e = a * b == e = b * a, therefore de/db = a = 2

      Therefore, with chain rule

      dL/da = (dL/de) * (de/da) = TODO

      Once you know the derivatives of all nodes with respect to L, you can adjust the nodes according to their gradient
      to make L go up or down. For example to increase the value of L, you could increase a, a.data = a.grad * 0.001.
      Similarly, you could decrease L, e.g a.data = a.data - (a.grad * 0.001). This is the training process of a neural
      network, where L is the loss function, and we want to adjust our weights and biases to reduce the loss function.
     */
  }
}
