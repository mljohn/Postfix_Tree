/**
 * File: Node
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package components.nodes;

/**
 * Interface defining a Node.
 */
public interface Node {
  
  /**
   * Evaluates the value of the node.
   * @return the value of the node
   */
  public int evaluate();
  
  /**
   * Performs a preorder walk of the tree.
   * 
   * @return the results of a preorder walk
   */
  public String preOrderWalk();
  
  /**
   * Performs an inorder walk of the tree.
   * 
   * @return the results of a inorder walk
   */
  public String inOrderWalk();
  
  /**
   * Performs a postorder walk of the tree.
   * 
   * @return the results of the postorder walk
   */
  public String postOrderWalk();
}
