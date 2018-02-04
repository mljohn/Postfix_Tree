/**
 * File: MultiplyOperator
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package components.operators;

/**
 * Class that extends {@link Operator} to evaluate an expression using multiplication.
 */

public class MultiplyOperator extends Operator {

  @Override
  public int evaluate(int x, int y) {
    return x * y;
  }
  
  @Override
  public String toString() {
    return "*";
  }
}
