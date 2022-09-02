public class TV {
   double price ;
   public void setPrice(double m) {
      price = m;
   }
   public String toString() {
      String oldStr = super.toString(); 
      return oldStr+"\n这是电视机，价格是:"+price;
   }
}
