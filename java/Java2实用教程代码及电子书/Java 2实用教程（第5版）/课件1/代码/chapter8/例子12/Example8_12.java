public class Example8_12  { 
   public static void main(String args[]) {
      String shoppingReceipt = "牛奶:8.5圆,香蕉3.6圆，酱油:2.8圆";
      PriceToken lookPriceMess = new PriceToken();
      System.out.println(shoppingReceipt);
      double sum =lookPriceMess.getPriceSum(shoppingReceipt);
      System.out.printf("购物总价格%-7.2f",sum);
      int amount = lookPriceMess.getGoodsAmount(shoppingReceipt);
      double aver = lookPriceMess.getAverPrice(shoppingReceipt);
      System.out.printf("\n商品数目:%d,平均价格:%-7.2f",amount,aver);
    } 
}
