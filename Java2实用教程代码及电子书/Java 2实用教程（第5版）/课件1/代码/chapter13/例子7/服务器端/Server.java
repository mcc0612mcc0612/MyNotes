import java.io.*;
import java.net.*;
import java.sql.*;
public class Server {
   public static void main(String args[]) {
      Connection con;
      PreparedStatement sqlOne=null,sqlTwo=null; 
      ResultSet rs;
      try{  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(ClassNotFoundException e){}
      try{  con= GetDBConnection.connectDB("students","root","");
            sqlOne=con.prepareStatement("SELECT * FROM mess WHERE number=? ");
            sqlTwo=con.prepareStatement("SELECT * FROM mess WHERE name =?");
      }
      catch(SQLException e){}
      ServerSocket server=null;
      Runnable target;
      Thread threadForClient = null;
      Socket socketOnServer = null;
      while(true) {
        try{  server=new ServerSocket(4331);
        }
        catch(IOException e1) {
              System.out.println("���ڼ���"); 
        } 
        try{ System.out.println(" �ȴ��ͻ�����");
             socketOnServer = server.accept();
             System.out.println("�ͻ��ĵ�ַ:"+
             socketOnServer.getInetAddress());
        }
        catch(IOException e) {
              System.out.println("���ڵȴ��ͻ�");
        }
        if(socketOnServer!=null) {
            target = new Target(socketOnServer,sqlOne,sqlTwo);
            threadForClient =new Thread(target); 
            threadForClient.start();  
        }
     }
   }
}
