import java.util.Scanner;
public class Example8_9 {
  public static void main (String args[ ]) {
      String regex = "[a-zA-Z|0-9|_]+"; 
      Scanner scanner = new Scanner(System.in);
      String str = scanner.nextLine();
      if(str.matches(regex)) {
          System.out.println(str+"是英文字母,数字或下划线构成"); 
      }
      else {
          System.out.println(str+"中有非法字符"); 
      }
   }
}