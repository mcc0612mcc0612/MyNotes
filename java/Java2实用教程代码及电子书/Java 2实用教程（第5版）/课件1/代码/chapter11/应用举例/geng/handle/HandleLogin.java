package geng.handle; 
import geng.model.Login;
import java.sql.*;
import javax.swing.JOptionPane;
public class HandleLogin {
   Connection con;
   PreparedStatement preSql;
   ResultSet rs;  
   public HandleLogin(){
      try{  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){}
      String uri = "jdbc:mysql://localhost:3306/user?useSSL=true";
      try{  
         con = DriverManager.getConnection(uri,"root",""); 
      }
      catch(SQLException e){}
   }
   public Login queryVerify(Login loginModel) {
      String id = loginModel.getID();
      String pw = loginModel.getPassword();
      String sqlStr ="select id,password from register where "+
                      "id = ? and password = ?";
      try { 
          preSql = con.prepareStatement(sqlStr);
          preSql.setString(1,id);       
          preSql.setString(2,pw);  
          rs = preSql.executeQuery(); 
          if(rs.next()==true) {
             loginModel.setLoginSuccess(true);
             JOptionPane.showMessageDialog(null,"µÇÂ¼³É¹¦",
                             "¹§Ï²",JOptionPane.WARNING_MESSAGE);
          }
          else {
             loginModel.setLoginSuccess(false);
             JOptionPane.showMessageDialog(null,"µÇÂ¼Ê§°Ü",
                "µÇÂ¼Ê§°Ü£¬ÖØÐÂµÇÂ¼",JOptionPane.WARNING_MESSAGE);
          }
          con.close();
      }
      catch(SQLException e) {}
      return loginModel;
   }
}
