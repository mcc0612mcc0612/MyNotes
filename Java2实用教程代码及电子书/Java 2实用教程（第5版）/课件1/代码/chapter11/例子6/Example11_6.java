import javax.swing.*;
public class Example11_6 {
   public static void main(String args[]) {
      String [] tableHead;
      String [][] content; 
      JTable table ;
      JFrame win= new JFrame();
      Query findRecord = new  Query();
      findRecord.setDatabaseName("students");
      findRecord.setSQL("select * from mess");
      content = findRecord.getRecord();
      tableHead=findRecord.getColumnName();
      table = new JTable(content,tableHead); 
      win.add(new JScrollPane(table));
      win.setBounds(12,100,400,200);
      win.setVisible(true); 
      win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
