import javax.swing.*;
public class PanelNullLayout extends JPanel {
   JButton button;
   JTextField text;
   PanelNullLayout() {
      setLayout(null);  //�ղ���
      button = new JButton("ȷ��");
      text = new JTextField();
      add(text);
      add(button);
      text.setBounds(100,30,90,30);
      button.setBounds(190,30,66,30);
   }

}