/**
 * File: GeneratorTextArea
 * Author: Michelle John
 * Date: 04 February 2018
 * Purpose: Week 4 Three Address Generator
 */
package utils;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * Class that creates a custom {@link JTextArea} to use in this application.
 */
public class GeneratorTextArea extends JTextArea {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor. 
   * 
   * @param editable if the text area should be editable
   */
  public GeneratorTextArea(boolean editable) {
    super();
    setEditable(editable);
    setBackground(WHITE);
    setForeground(BLACK);
    setColumns(20);
    setBorder(new LineBorder(BLACK));
  }
}
