import javax.swing.*;
public class WindowBoxLayout extends JFrame  {
    Box boxH;               //��ʽ��
    Box boxVOne,boxVTwo;    //��ʽ�� 
    public WindowBoxLayout() {
        setLayout(new java.awt.FlowLayout());
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    void init() {
        boxH =Box.createHorizontalBox();
        boxVOne=Box.createVerticalBox();
        boxVTwo=Box.createVerticalBox();
        boxVOne.add(new JLabel("����:"));
        boxVOne.add(new JLabel("ְҵ:"));
        boxVTwo.add(new JTextField(10));
        boxVTwo.add(new JTextField(10));
        boxH.add(boxVOne);
        boxH.add(Box.createHorizontalStrut(10));
        boxH.add(boxVTwo);
        add(boxH);       
    }
}
