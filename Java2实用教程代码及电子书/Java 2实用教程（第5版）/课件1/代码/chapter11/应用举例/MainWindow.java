import geng.view.RegisterAndLoginView;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
public class MainWindow extends JFrame implements ActionListener{
   JButton computerButton; 
   RegisterAndLoginView view;
   MainWindow() {
      setBounds(100,100,800,260);
      view = new RegisterAndLoginView();
      computerButton = new JButton("玩华容道");
      computerButton.addActionListener(this);
      add(view,BorderLayout.CENTER);
      add(computerButton,BorderLayout.NORTH);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true); 
   }
   public void actionPerformed(ActionEvent e) {
      if(view.isLoginSuccess()==false){
        JOptionPane.showMessageDialog(null,"请登录","登录提示",
                                   JOptionPane.WARNING_MESSAGE);
      }
      else {
         Hua_Rong_Road win=new Hua_Rong_Road();//华容道
      }
   }
   public static void main(String args[]) {
       MainWindow window = new MainWindow();
       window.setTitle("登录后可玩华容道");
   }
}