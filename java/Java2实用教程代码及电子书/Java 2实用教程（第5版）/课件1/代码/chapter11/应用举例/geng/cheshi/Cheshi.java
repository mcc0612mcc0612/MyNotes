package geng.cheshi;
import geng.model.*;
import geng.handle.*;
import java.sql.*;
public class Cheshi {
   public static void main(String args[]) {
      Register user = new Register();
      user.setID("moonjava");
      user.setPassword("123456");
      user.setBirth("1999-12-10");
      HandleInsertData handleRegister = new HandleInsertData();
      handleRegister.writeRegisterModel(user);
      Login login = new Login();
      login.setID("moonjava");
      login.setPassword("123456");
      HandleLogin  handleLogin = new HandleLogin();
      login = handleLogin.queryVerify(login);
      if(login.getLoginSuccess()==true) {
         System.out.println("µÇÂ¼³É¹¦ÁË£¡");
      }
   }
}