package main;

import org.junit.Test;

public class ThreeAddressGeneratorTest {
  
  
  @Test
  public void testTokenize() {
    String expression = "43+4*";
    new ThreeAddressGenerator(expression);
  }
  
  @Test
  public void testFail() {
    String expression = "(43+4*)";
    new ThreeAddressGenerator(expression);
  }

}
