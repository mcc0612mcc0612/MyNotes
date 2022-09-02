public class Example8_2 {
  public static void main(String args[]) {
       String s1,s2;
       s1 = new String("天道酬勤");
       s2 = new String("天道酬勤");
       System.out.println(s1.equals(s2));   	//输出结果是：true
       System.out.println(s1==s2);          	//输出结果是：false
       String s3,s4; 
       s3 = "we are students";
       s4 = new String("we are students");
       System.out.println(s3.equals(s4));   	//输出结果是：true
       System.out.println(s3==s4);         	//输出结果是：false 
       String s5,s6;
       s5 = "勇者无敌";
       s6 = "勇者无敌"; 
       System.out.println(s5.equals(s6));   	//输出结果是：true
       System.out.println(s5==s6);         	//输出结果是：true
    }
}
