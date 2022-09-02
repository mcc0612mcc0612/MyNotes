import java.util.regex.*;
public class Example8_22 {
   public static void main(String args[ ]) { 
      String s = "市话76.8元,长途:167.38元,短信12.68"; 
      String regex = "[0123456789.]+";    //匹配数字序列 
      Pattern p =Pattern.compile(regex);  //模式对象
      Matcher m =p.matcher(s); 	          //匹配对象
      double sum =0;
      while(m.find()) {
         String item = m.group();
         System.out.println(item);
         sum = sum+Double.parseDouble(item);
      } 
      System.out.println("账单总价格:"+sum);
   }
}
