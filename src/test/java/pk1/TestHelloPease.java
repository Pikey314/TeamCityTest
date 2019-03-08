package pk1;


import junit.framework.Assert;
import junit.framework.TestCase;

public class  TestHelloPease extends TestCase {
  public void testSimpleMessage() {
     String message = new HelloPease("World222").sayHello();
     Assert.assertEquals("Test Hello Machine", "Hello, World222", message);
  }
}
