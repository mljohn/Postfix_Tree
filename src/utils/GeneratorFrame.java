/**
 * File: GeneratorFrame
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package utils;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Class to build a custom {@link JFrame} for this application.
 */
public class GeneratorFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param title the title of the frame
   * @param width the width of the frame
   * @param height the height of the frame
   */
  public GeneratorFrame(String title, int width, int height) {
    super(title);
    setSize(width, height);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout(5, 5));
  }
  
  /**
   * Displays the frame.
   */
  public void display() {
    setVisible(true);
  }
}
