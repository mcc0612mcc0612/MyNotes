import java.sql.*; 
public class Example11_5 {
   public static void main(String args[]) {
      Connection con;
      PreparedStatement preSql;  //Ԥ����������preSql
      ResultSet rs;
      con = GetDBConnection.connectDB("students","root","");
      if(con == null ) return;
      String sqlStr ="insert into mess values(?,?,?,?)";
      try { 
          preSql = con.prepareStatement(sqlStr);//�õ�Ԥ����������preSql
          preSql.setString(1,"A001");       //���õ�1��?�����ֵ
          preSql.setString(2,"��ΰ");       //���õ�2��?�����ֵ
          preSql.setString(3,"1999-9-10"); //���õ�3��?�����ֵ
          preSql.setFloat(4,1.77f);        //���õ�4��?�����ֵ   
          int ok = preSql.executeUpdate();
          sqlStr="select * from mess where name like ? ";
          preSql = con.prepareStatement(sqlStr);//�õ�Ԥ����������preSql
          preSql.setString(1,"��%");       //���õ�1��?�����ֵ
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
         System.out.println("��¼��numberֵ�����ظ�"+e);
      }
  }
}
