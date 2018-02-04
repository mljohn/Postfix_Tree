/**
 * File: ThreeAddressGenerator
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import components.ArrayStack;
import components.nodes.Node;
import components.nodes.OperandNode;
import components.nodes.OperatorNode;
import components.operators.AddOperator;
import components.operators.DivideOperator;
import components.operators.MultiplyOperator;
import components.operators.SubtractOperator;
import exceptions.DivideByZeroException;
import exceptions.StackException;

/**
 * Class that receives a postfix expression, turns it into an infix expression using a tree, and creates three address 
 * instructions.
 */
public class ThreeAddressGenerator {
  
  private List<String> tokenizedExpression;

  /**
   * Constructor.
   * 
   * @param expression the postfix expression to evaluate
   */
  public ThreeAddressGenerator(String expression) {
    tokenizedExpression = tokenizeExpression(expression);
  }
  
  /**
   * Method that takes the expression list and creates the tree to use for evaluation.
   * 
   * @return the infix expression
   * @throws StackException if an error occurs while operating on a stack
   * @throws DivideByZeroException if an attempt is made to divide by zero
   */
  public String evaluateExpression() throws StackException, DivideByZeroException {
    ArrayStack<Node> expressionStack = new ArrayStack<>();
    for (String element : tokenizedExpression) {
      if (isAnInteger(element)) {
        expressionStack.push(new OperandNode(Integer.parseInt(element)));
      } else {
        Node tree2 = expressionStack.pop();
        Node tree1 = expressionStack.pop();
        switch (element) {
          case "+":
            expressionStack.push(new OperatorNode(new AddOperator(), tree1, tree2));
            break;
          case "-":
            expressionStack.push(new OperatorNode(new SubtractOperator(), tree1, tree2));
            break;
          case "*":
            expressionStack.push(new OperatorNode(new MultiplyOperator(), tree1, tree2));
            break;
          case "/":
            if (tree2.toString() == "0") {
              throw new DivideByZeroException("Division by zero occured.");
            }
            expressionStack.push(new OperatorNode(new DivideOperator(), tree1, tree2));
            break;
          default: 
            throw new StackException("Unknown");
        }
      }
    }
    return expressionStack.pop().inOrderWalk();
  }

  /**
   * Method that takes the postfix expression and turns it into a {@link List} of type {@link String}.
   * 
   * @param expression the postfix expression to parse
   * @return the {@link List} of {@link String}s
   */
  private List<String> tokenizeExpression(String expression) {
    Matcher notMatch = Pattern.compile("[^0-9/*+-]").matcher(expression);
    if (notMatch.find()) {
      throw new RuntimeException("Invalid token " + notMatch.group());
    }
    List<String> output = new ArrayList<>();
    Matcher match = Pattern.compile("[0-9/*+-]").matcher(expression);
    while (match.find()) {
      output.add(match.group().trim());
    }
    return output;
  }
  
  /**
   * Method that determines if the string is an integer.
   * 
   * @param integer the string that may be an integer
   * @return if the string is an integer
   */
  private boolean isAnInteger(String integer) {
    try {
      Integer.parseInt(integer);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
}
