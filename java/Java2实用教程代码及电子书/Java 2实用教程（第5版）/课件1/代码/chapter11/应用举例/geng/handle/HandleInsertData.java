package geng.handle; 
import geng.model.Register;
import java.sql.*;
import javax.swing.JOptionPane;
public class HandleInsertData {
   Connection con;
   PreparedStatement preSql;  
   public HandleInsertData(){
      try{  Class.forName("com.mysql.jdbc.Driver");//����JDBC-MySQL����
      }
      catch(Exception e){}
      String uri = "jdbc:mysql://localhost:3306/user?useSSL=true";
      try{  
         con = DriverManager.getConnection(uri,"root",""); //���Ӵ���
      }
      catch(SQLException e){}

   }
   public void writeRegisterModel(Register register) {
      String sqlStr ="insert into register values(?,?,?)";
      int ok = 0;
      try { 
          preSql = con.prepareStatement(sqlStr);
          preSql.setString(1,register.getID());       
          preSql.setString(2,register.getPassword());  
          preSql.setString(3,register.getBirth());
          ok = preSql.executeUpdate();
          con.close();
      }
      catch(SQLException e) {
         JOptionPane.showMessageDialog(null,"id�����ظ�","����",
                                   JOptionPane.WARNING_MESSAGE);
      }
      if(ok!=0) {
         JOptionPane.showMessageDialog(null,"ע��ɹ�",
                             "��ϲ",JOptionPane.WARNING_MESSAGE);
      }
   }
}
