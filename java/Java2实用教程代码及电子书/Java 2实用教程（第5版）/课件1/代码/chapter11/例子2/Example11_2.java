import java.sql.*; 
public class Example11_2 {
   public static void main(String args[]) {
      Connection con;
      Statement sql; 
      ResultSet rs;
      con = GetDBConnection.connectDB("students","root","");
      if(con == null ) return;
      try { 
          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
          rs = sql.executeQuery("SELECT * FROM mess ");
          rs.last();
          int max = rs.getRow();
          System.out.println("����"+max+"����¼,�����ȡ2����¼��");
          int [] a =GetRandomNumber.getRandomNumber(max,2);//�õ�1-max֮��2����ͬ�����
          for(int i:a){
             rs.absolute(i);//�ͱ��ƶ�����i��
             String number = rs.getString(1);
             String name = rs.getString(2);
             Date date = rs.getDate(3);
             float h = rs.getFloat(4);
             System.out.printf("%s\t",number);
             System.out.printf("%s\t",name);
             System.out.printf("%s\t",date); 
             System.out.printf("%.2f\n",h);
          }
          con.close();
      }
      catch(SQLException e) { 
         System.out.println(e);
      }
  }
}
