/**
 * File: OperandNode
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package components.nodes;

import static java.lang.String.valueOf;

/**
 * Class that implements {@link Node} for the operands.
 */
public class OperandNode implements Node {
  
  private int value;
  
  /**
   * Constructor.
   * 
   * @param value the value of this node
   */
  public OperandNode(int value) {
    this.value = value;
  }

  @Override
  public int evaluate() {
    return value;
  }

  @Override
  public String preOrderWalk() {
    return valueOf(value);
  }

  @Override
  public String inOrderWalk() {
    return valueOf(value);
  }

  @Override
  public String postOrderWalk() {
    return valueOf(value);
  }
}
