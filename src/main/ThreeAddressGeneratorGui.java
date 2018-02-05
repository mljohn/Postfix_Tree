/**
 * File: ThreeAddressGeneratorGui
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package main;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.StackException;
import utils.GeneratorFrame;
import utils.GeneratorTextArea;

/**
 * Main class for the Three Address Generator. This class builds and displays the GUI.
 */
public class ThreeAddressGeneratorGui {

  /**
   * Entry point into the application. Builds the GUI.
   * 
   * @param args the arguments to set at the start
   */
  public static void main(String[] args) {
    ThreeAddressGeneratorGui gui = new ThreeAddressGeneratorGui();
    gui.buildGeneratorGui();
  }
  
  /**
   * Method that builds the three address generator gui.
   */
  private void buildGeneratorGui() {
    GeneratorFrame frame = new GeneratorFrame("Three Address Generator", 450, 175);
    
    JPanel expressionPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel outputPanel = new JPanel();
    JPanel guiPanel = new JPanel(new GridLayout(3, 1, 5, 5));
    
    JLabel expressionLabel = new JLabel("Enter Postfix Expression");
    JLabel outputLabel = new JLabel("Infix Expression");
    
    GeneratorTextArea expressionTextArea = new GeneratorTextArea(true);
    GeneratorTextArea outputTextArea = new GeneratorTextArea(false);
    
    JButton constructButton = new JButton("Construct Tree");
    
    constructButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        String expression = expressionTextArea.getText();
        if (expression.isEmpty() || expression == null) {
          showMessageDialog(frame, "Please enter a valid postfix expression.");
        } else {
          try {
            ThreeAddressGenerator result = new ThreeAddressGenerator(expression);
            outputTextArea.setText(result.evaluateExpression().toString());
          } catch (RuntimeException | StackException exception) {
            showMessageDialog(frame, exception.getMessage());
          }
        }
      }
    });
    
    expressionPanel.add(expressionLabel);
    expressionPanel.add(expressionTextArea);
    buttonPanel.add(constructButton);
    outputPanel.add(outputLabel);
    outputPanel.add(outputTextArea);
    guiPanel.add(expressionPanel);
    guiPanel.add(buttonPanel);
    guiPanel.add(outputPanel);
    
    frame.add(guiPanel, CENTER);
    frame.add(new JPanel(), SOUTH);
    frame.add(new JPanel(), EAST);
    frame.add(new JPanel(), WEST);
    frame.add(new JPanel(), NORTH);
    frame.display();
  }
}
