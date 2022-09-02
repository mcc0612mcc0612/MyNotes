abstract class GirlFriend {
   abstract void speak();
   abstract void cooking();
}
class ChinaGirlFriend extends GirlFriend {
   void speak(){
      System.out.println("���");
   }
   void cooking(){
      System.out.println("ˮ����"); 
   }
}
class AmericanGirlFriend extends GirlFriend {
   void speak(){
      System.out.println("hello");
   }
   void cooking(){
      System.out.println("roast beef"); 
   }
}
class Boy {
   GirlFriend friend;
   void setGirlfriend(GirlFriend f){
       friend = f;
   }
   void showGirlFriend() {
      friend.speak();
      friend.cooking();
   }
}
public class Example5_12 {
   public static void main(String args[]) {
      GirlFriend girl = new ChinaGirlFriend(); //girl����ת�Ͷ���
      Boy boy = new Boy();
      boy.setGirlfriend(girl);
      boy.showGirlFriend();     
      girl = new AmericanGirlFriend(); //girl����ת�Ͷ���
      boy.setGirlfriend(girl);
      boy.showGirlFriend();      
   }
}
