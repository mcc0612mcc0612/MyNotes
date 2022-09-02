import java.sql.*;
public class Query {
   String databaseName="";    	//���ݿ���
   String SQL;        		//SQL���
   String [] columnName;        //ȫ���ֶΣ��У���
   String [][] record;          //��ѯ���ļ�¼
   public Query() {
      try{  Class.forName("com.mysql.jdbc.Driver");//����JDBC-MySQL����
      }
      catch(Exception e){}
   }
   public void setDatabaseName(String s) {
      databaseName=s.trim();
   }
   public void setSQL(String SQL) {
      this.SQL=SQL.trim();
   }
   public String[] getColumnName() {
       if(columnName ==null ){
           System.out.println("�Ȳ�ѯ��¼");
           return null;
       }
       return columnName;
   }
   public String[][] getRecord() {
       startQuery();
       return record;
   }
   private void startQuery() { 
      Connection con;
      Statement sql;  
      ResultSet rs;
      String uri = 
     "jdbc:mysql://localhost:3306/"+
      databaseName+"?useSSL=true&characterEncoding=utf-8";
      try { 
        con=DriverManager.getConnection(uri,"root","");
        sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
        rs=sql.executeQuery(SQL);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();//�ֶ���Ŀ 
        columnName=new String[columnCount]; 
        for(int i=1;i<=columnCount;i++){
            columnName[i-1]=metaData.getColumnName(i);
        } 
        rs.last(); 
        int recordAmount =rs.getRow();  //������еļ�¼��Ŀ
        record = new String[recordAmount][columnCount];
        int i=0;
        rs.beforeFirst();
        while(rs.next()) { 
          for(int j=1;j<=columnCount;j++){
             record[i][j-1]=rs.getString(j); //��i����¼�������ά����ĵ�i��
          }
          i++;
        }
        con.close();
      }
      catch(SQLException e) {
        System.out.println("��������ȷ�ı���"+e);
      }
   }    
}