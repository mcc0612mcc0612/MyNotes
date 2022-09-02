package geng.model;
public class Register {
   String id;
   String password;
   String birth;
   public void setID(String id){
      this.id = id;
   }
   public void setPassword(String password){
      this.password = password;
   }
   public void setBirth(String birth){
      this.birth = birth;
   }
   public String getID() {
      return id;
   }
   public String getPassword(){
      return password;
   }
   public String getBirth(){
      return birth;
   }
}