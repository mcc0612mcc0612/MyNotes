import java.util.Scanner;
public class Example8_9 {
  public static void main (String args[ ]) {
      String regex = "[a-zA-Z|0-9|_]+"; 
      Scanner scanner = new Scanner(System.in);
      String str = scanner.nextLine();
      if(str.matches(regex)) {
          System.out.println(str+"��Ӣ����ĸ,���ֻ��»��߹���"); 
      }
      else {
          System.out.println(str+"���зǷ��ַ�"); 
      }
   }
}