public class Example8_16 {
   public static void main(String args[]) {
      CalendarBean cb=new CalendarBean();
      cb.setYear(2022);
      cb.setMonth(6);
      String [] a= cb.getCalendar();//���غ����һά����
      char [] str="��һ����������".toCharArray();
      for(char c:str) {     
         System.out.printf("%3c",c);
      }
      for(int i=0;i<a.length;i++) {     //�������a
         if(i%7==0)
             System.out.println("");  //����
         System.out.printf("%4s",a[i]);
      } 
   } 
}
