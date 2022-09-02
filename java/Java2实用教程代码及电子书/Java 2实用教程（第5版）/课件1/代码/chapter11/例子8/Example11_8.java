import java.sql.*;
public class Example11_8 {
   public static void main(String[] args) {
      Connection con =null;
      Statement sta = null;
      ResultSet rs;
      String SQL;
      try { 
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//��������
      }
      catch(Exception e) {
        System.out.println(e);  
        return;
      }
      try { 
         String uri ="jdbc:derby:students;create=true";
         con=DriverManager.getConnection(uri);  //�������ݿ�
         sta = con.createStatement();
      }
      catch(Exception e) {
        System.out.println(e);  
        return;
      }
      try { SQL = "create table chengji(name varchar(40),score float)";
            sta.execute(SQL);//������
      }
      catch(SQLException e) { 
         //System.out.println("�ñ��Ѿ�����"); 
      }
      SQL ="insert into chengji values"+
            "('����', 90),('��˹', 88),('����', 67)";
      try {
         sta.execute(SQL);
         rs = sta.executeQuery("select * from chengji "); // ��ѯ���еļ�¼
         while(rs.next()) {
            String name=rs.getString(1);
            System.out.print(name+"\t");
            float score=rs.getFloat(2);
            System.out.println(score);
         }
         con.close();
      } 
      catch(SQLException e) {
          System.out.println(e);  
      }
  }
}
