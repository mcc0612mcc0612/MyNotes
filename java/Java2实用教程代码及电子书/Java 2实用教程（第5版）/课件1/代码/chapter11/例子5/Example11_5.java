import java.sql.*; 
public class Example11_5 {
   public static void main(String args[]) {
      Connection con;
      PreparedStatement preSql;  //预处理语句对象preSql
      ResultSet rs;
      con = GetDBConnection.connectDB("students","root","");
      if(con == null ) return;
      String sqlStr ="insert into mess values(?,?,?,?)";
      try { 
          preSql = con.prepareStatement(sqlStr);//得到预处理语句对象preSql
          preSql.setString(1,"A001");       //设置第1个?代表的值
          preSql.setString(2,"刘伟");       //设置第2个?代表的值
          preSql.setString(3,"1999-9-10"); //设置第3个?代表的值
          preSql.setFloat(4,1.77f);        //设置第4个?代表的值   
          int ok = preSql.executeUpdate();
          sqlStr="select * from mess where name like ? ";
          preSql = con.prepareStatement(sqlStr);//得到预处理语句对象preSql
          preSql.setString(1,"张%");       //设置第1个?代表的值
          rs = preSql.executeQuery();
          while(rs.next()) { 
             String number=rs.getString(1);
             String name=rs.getString(2);
             Date date=rs.getDate(3);
             float height=rs.getFloat(4);
             System.out.printf("%s\t",number);
             System.out.printf("%s\t",name);
             System.out.printf("%s\t",date); 
             System.out.printf("%.2f\n",height);
          }
          con.close();
      }
      catch(SQLException e) { 
         System.out.println("记录中number值不能重复"+e);
      }
  }
}
