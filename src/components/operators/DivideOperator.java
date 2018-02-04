/**
 * File: DivideOperator
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package components.operators;

/**
 * Class that extends {@link Operator} to evaluate an expression using division.
 */
public class DivideOperator extends Operator {

  @Override
  public int evaluate(int x, int y) {
    if (y == 0) return 0;
    return x / y;
  }
  
  @Override
  public String toString() {
    return "/";
  }
}
