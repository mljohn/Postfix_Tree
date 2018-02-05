/**
 * File: ThreeAddressGenerator
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import components.operators.Operator;
import components.operators.SubtractOperator;
import exceptions.StackException;

/**
 * Class that receives a postfix expression, turns it into an infix expression using a tree, and creates three address 
 * instructions.
 */
public class ThreeAddressGenerator {
  
  private static final String SPACE = " ";
  private static final String NEWLINE = "\n";
  
  private List<String> tokenizedExpression;
  private String threeAddressInstruction = "";

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
  public String evaluateExpression() throws StackException {
    ArrayStack<Node> expressionStack = new ArrayStack<>();
    int register = 0;
    for (String element : tokenizedExpression) {
      if (isAnInteger(element)) {
        expressionStack.push(new OperandNode(Integer.parseInt(element)));
      } else {
        Node right = expressionStack.pop();
        Node left = expressionStack.pop();
        Operator op;
        switch (element) {
          case "+":
            op = new AddOperator();
            break;
          case "-":
            op = new SubtractOperator();
            break;
          case "*":
            op = new MultiplyOperator();
            break;
          case "/":
            op = new DivideOperator();
            break;
          default: 
            throw new StackException("Unknown operation");
        }
        expressionStack.push(new OperatorNode(op, left, right));
        createThreeAddressOp(op, left, right, register);
        register++;
      }
    }
    Node finalTree = expressionStack.pop();
    if (!expressionStack.isEmpty()) {
      throw new StackException("Not all elements were used");
    }
    writeInstructionsToFile();
    return finalTree.inOrderWalk();
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
  
  /**
   * Method that creates the three address instruction. 
   * Credit to Barrett Otte 
   * https://github.com/barrettotte/Postfix_Pseudo-Assembly_3_Address_Generator/blob/master/src/project2/ExpressionTree.java
   * 
   * @param op the operator node
   * @param left the left node
   * @param right the right node
   * @param register the register count
   */
  private void createThreeAddressOp(Operator op, Node left, Node right, int register) {
    threeAddressInstruction += op.operator() + " R" + register + SPACE;

    if (left instanceof OperandNode) {
      threeAddressInstruction += left.inOrderWalk() + SPACE;
      if (right instanceof OperandNode) {
        threeAddressInstruction += right.inOrderWalk() + NEWLINE;
      } else {
        threeAddressInstruction += "R" + (register - 1) + NEWLINE;
      }
    } else if (left instanceof OperatorNode) {
      register += register <= 2 ? 1 : 0;
      threeAddressInstruction += "R" + (register - 2) + SPACE;
      if (right instanceof OperatorNode) {
        threeAddressInstruction += "R" + (register - 1) + NEWLINE;
      } else {
        threeAddressInstruction += right.inOrderWalk() + NEWLINE;
      }
    }
  }
  
  /**
   * Method that prints the instruction set to a file.
   */
  private void writeInstructionsToFile() {
    try (FileWriter fileWriter = new FileWriter("ThreeAddressInstructionSet");
        PrintWriter printWriter = new PrintWriter(fileWriter)) {
      printWriter.print(threeAddressInstruction);
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
