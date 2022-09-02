package geng.view;
import javax.swing.*; 
import java.awt.*;
public class RegisterAndLoginView extends JPanel{
    JTabbedPane p;
    RegisterView registerView;
    LoginView loginView;
    public RegisterAndLoginView(){
        registerView=new RegisterView();
        loginView = new LoginView();
        setLayout(new BorderLayout());
        p = new JTabbedPane();
        p.add("我要注册",registerView);
        p.add("我要登录",loginView);
        p.validate();
        add(p,BorderLayout.CENTER); 
    }
    public boolean isLoginSuccess() {
        return loginView.isLoginSuccess();
    }
}