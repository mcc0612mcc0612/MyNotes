import java.util.Scanner; 
public class Example8_11 {
   public static void main (String args[ ]) {
      System.out.println("一行文本:");
      Scanner reader=new Scanner(System.in);
      String str = reader.nextLine();
  //regex匹配由空格、数字和!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~组成的字符序列
      String regex = "[\\s\\d\\p{Punct}]+"; 
      String words[] = str.split(regex); 
      for(int i=0;i<words.length;i++){
         int m = i+1;
         System.out.println("单词"+m+":"+words[i]);
      }   
   }
}



