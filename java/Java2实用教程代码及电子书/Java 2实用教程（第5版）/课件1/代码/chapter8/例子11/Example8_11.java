import java.util.Scanner; 
public class Example8_11 {
   public static void main (String args[ ]) {
      System.out.println("һ���ı�:");
      Scanner reader=new Scanner(System.in);
      String str = reader.nextLine();
  //regexƥ���ɿո����ֺ�!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~��ɵ��ַ�����
      String regex = "[\\s\\d\\p{Punct}]+"; 
      String words[] = str.split(regex); 
      for(int i=0;i<words.length;i++){
         int m = i+1;
         System.out.println("����"+m+":"+words[i]);
      }   
   }
}



