public class Example8_23 {
   public static void main(String args[]) {
      RedEnvelope redEnvelope = new RandomRedEnvelope(5.20,6);
      System.out.printf("������ѭ�����%d������%.2fԲ��������:\n",
                         redEnvelope.remainPeople,redEnvelope.remainMoney);
      showProcess(redEnvelope);
   }
   public static void showProcess (RedEnvelope redEnvelope) {
      double sum = 0;
      while(redEnvelope.remainPeople>0){
         double money = redEnvelope.giveMoney();
         System.out.printf("%.2f\t",money);
         sum = sum+money;
      }
      String s = String.format("%.2f",sum);   //����2λС��
      sum = Double.parseDouble(s);
      System.out.printf("\n%.2fԲ�ĺ��������",sum);
   }
}