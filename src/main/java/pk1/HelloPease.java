package pk1;


public class HelloPease {
private String name;
//ttttttt
///ttttt
     public static void main(String [] args) {
     System.out.println(new HelloWorld("Test").sayHello());
         System.out.println("ByeBye");
     }
  public HelloPease(String name) {
      this.name = name;
  }
  public String sayHello() {
      return "Hello, " + name;
  }
}
