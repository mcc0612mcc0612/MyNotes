public class Example8_13 {
   public static void main(String args[]) {
      String cost = "�л�76.8Ԫ,��;:167.38Ԫ,����12.68Ԫ";
      double priceSum = GetPrice.givePriceSum(cost);
      System.out.printf("%s\n�ܼ�:%.2fԲ\n",cost,priceSum);
      cost = "ţ��:8.5Բ,�㽶3.6Բ������:2.8Բ";
      priceSum = GetPrice.givePriceSum(cost);
      System.out.printf("%s\n�ܼ�:%.2fԲ\n",cost,priceSum);
   }
}
