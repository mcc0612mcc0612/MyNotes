import java.util.regex.*;
public class Example8_22 {
   public static void main(String args[ ]) { 
      String s = "�л�76.8Ԫ,��;:167.38Ԫ,����12.68"; 
      String regex = "[0123456789.]+";    //ƥ���������� 
      Pattern p =Pattern.compile(regex);  //ģʽ����
      Matcher m =p.matcher(s); 	          //ƥ�����
      double sum =0;
      while(m.find()) {
         String item = m.group();
         System.out.println(item);
         sum = sum+Double.parseDouble(item);
      } 
      System.out.println("�˵��ܼ۸�:"+sum);
   }
}
