import java.math.*;
public class Example8_17 {
  public static void main(String args[]) {
      double a = 5.0;
      double st = Math.sqrt(a);
      System.out.println(a+"��ƽ����:"+st);
      System.out.printf("���ڵ���%f����С����%d\n",5.2,(int)Math.ceil(5.2));
      System.out.printf("С�ڵ���%f���������%d\n",-5.2,(int)Math.floor(-5.2));
      System.out.printf("%f�������������:%d\n",12.9,Math.round(12.9));
      System.out.printf("%f�������������:%d\n",-12.6,Math.round(-12.6));
      BigInteger result = new BigInteger("0"),
                 one = new BigInteger("123456789"),
                 two = new BigInteger("987654321");
       result = one.add(two);
       System.out.println("��:"+result);
       result=one.multiply(two);
       System.out.println("��:"+result);
    }
}
