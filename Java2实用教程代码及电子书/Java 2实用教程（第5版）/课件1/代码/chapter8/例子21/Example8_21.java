import java.io.Console;
public class Example8_21 {
   public static void main(String args[]) {
      boolean success=false;
      int count=0;
      Console cons;
      char[] passwd;
      cons = System.console();
      while(true) {
         System.out.print("��������:");
         passwd=cons.readPassword(); 
         count++;
         String password=new String(passwd); 
         if (password.equals("I love this game")) {
            success=true;
             System.out.println("��"+count+"��������ȷ!");
            break;
         }
         else {
             System.out.println("��"+count+"������"+password+"����ȷ");
         }
         if(count==3) {
            System.out.println("��"+count+"����������붼����ȷ");
            System.exit(0); 
         } 
      }
      if(success) {
          System.out.println("��ã���ӭ��!");
      }
   }
}
