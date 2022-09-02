import java.sql.*;
public class Example11_8 {
   public static void main(String[] args) {
      Connection con =null;
      Statement sta = null;
      ResultSet rs;
      String SQL;
      try { 
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//加载驱动
      }
      catch(Exception e) {
        System.out.println(e);  
        return;
      }
      try { 
         String uri ="jdbc:derby:students;create=true";
         con=DriverManager.getConnection(uri);  //连接数据库
         sta = con.createStatement();
      }
      catch(Exception e) {
        System.out.println(e);  
        return;
      }
      try { SQL = "create table chengji(name varchar(40),score float)";
            sta.execute(SQL);//创建表
      }
      catch(SQLException e) { 
         //System.out.println("该表已经存在"); 
      }
      SQL ="insert into chengji values"+
            "('张三', 90),('李斯', 88),('刘二', 67)";
      try {
         sta.execute(SQL);
         rs = sta.executeQuery("select * from chengji "); // 查询表中的记录
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
