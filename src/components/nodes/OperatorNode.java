/**
 * File: OperatorNode
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package components.nodes;

import components.operators.Operator;

/**
 * Class that implements {@link Node} for the operatorse.
 */
public class OperatorNode implements Node {
  
  private Node left;
  private Node right;
  private Operator operator;
  
  /**
   * Constructor. 
   * 
   * @param operator the operator for this node
   * @param left the left node
   * @param right the right node
   */
  public OperatorNode(Operator operator, Node left, Node right) {
    this.operator = operator;
    this.left = left;
    this.right = right;
  }

  @Override
  public int evaluate() {
    int leftValue = left.evaluate();
    int rightValue = right.evaluate();
    return operator.evaluate(leftValue, rightValue);
  }

  @Override
  public String preOrderWalk() {
    String leftValue = left.preOrderWalk();
    String rightValue = right.preOrderWalk();
    return operator + " " + leftValue + " " + rightValue;
  }

  @Override
  public String inOrderWalk() {
    String leftValue = left.inOrderWalk();
    String rightValue = right.inOrderWalk();
    return "(" + leftValue + " " + operator + " " + rightValue + ")";
  }

  @Override
  public String postOrderWalk() {
    String leftValue = left.postOrderWalk();
    String rightValue = right.postOrderWalk();
    return leftValue + " " + rightValue + " " + operator;
  }
}
