import java.util.Date;
public class Example8_5 {
   public static void main(String args[]) {
       Date date = new Date();
       System.out.println(date.toString());
       TV tv = new TV();
       tv.setPrice(5897.98);
       System.out.println(tv.toString());  
   }
}
