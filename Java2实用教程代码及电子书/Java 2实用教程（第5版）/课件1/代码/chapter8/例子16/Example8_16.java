public class Example8_16 {
   public static void main(String args[]) {
      CalendarBean cb=new CalendarBean();
      cb.setYear(2022);
      cb.setMonth(6);
      String [] a= cb.getCalendar();//返回号码的一维数组
      char [] str="日一二三四五六".toCharArray();
      for(char c:str) {     
         System.out.printf("%3c",c);
      }
      for(int i=0;i<a.length;i++) {     //输出数组a
         if(i%7==0)
             System.out.println("");  //换行
         System.out.printf("%4s",a[i]);
      } 
   } 
}
