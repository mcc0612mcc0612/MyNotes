public class Example8_12  { 
   public static void main(String args[]) {
      String shoppingReceipt = "ţ��:8.5Բ,�㽶3.6Բ������:2.8Բ";
      PriceToken lookPriceMess = new PriceToken();
      System.out.println(shoppingReceipt);
      double sum =lookPriceMess.getPriceSum(shoppingReceipt);
      System.out.printf("�����ܼ۸�%-7.2f",sum);
      int amount = lookPriceMess.getGoodsAmount(shoppingReceipt);
      double aver = lookPriceMess.getAverPrice(shoppingReceipt);
      System.out.printf("\n��Ʒ��Ŀ:%d,ƽ���۸�:%-7.2f",amount,aver);
    } 
}
