/**
 * File: Operator
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package components.operators;

/**
 * Abstract class that defines the method to use for an operator node.
 */
public abstract class Operator {

  /**
   * Method that evaluates the input parameters.
   * 
   * @param x the first operand of the operation
   * @param y the second operand of the operation
   * @return the results of the evaluation
   */
  abstract public int evaluate(int x, int y);
  
  /**
   * Returns the string version of the operation for creating the 3 address instruction.
   * 
   * @return the operator as a 3 letter string
   */
  abstract public String operator();
}
