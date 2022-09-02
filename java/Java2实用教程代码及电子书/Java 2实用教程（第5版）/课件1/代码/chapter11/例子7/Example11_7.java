import java.sql.*; 
public class Example11_7{
    public static void main(String args[]){
       Connection con = null;
       Statement sql;
       ResultSet rs; 
       String sqlStr;
       con = GetDBConnection.connectDB("students","root","");
       if(con == null ) return;
       try{ float n = 0.02f;
            con.setAutoCommit(false);       //关闭自动提交模式
            sql = con.createStatement();
            sqlStr = "select name,height from mess where number='R1001'";
            rs = sql.executeQuery(sqlStr);
            rs.next();
            float h1 = rs.getFloat(2);
            System.out.println("事务之前"+rs.getString(1)+"身高:"+h1);
            sqlStr = "select name,height from mess where number='R1002'"; 
            rs = sql.executeQuery(sqlStr);
            rs.next();
            float h2 = rs.getFloat(2);
            System.out.println("事务之前"+rs.getString(1)+"身高:"+h2);  
            h1 = h1-n;
            h2 = h2+n;
            sqlStr = "update mess set height ="+h1+" where number='R1001'";
            sql.executeUpdate(sqlStr);
            sqlStr = "update mess set height ="+h2+" where number='R1002'";
            sql.executeUpdate(sqlStr);
            con.commit(); //开始事务处理,如果发生异常直接执行catch块
            con.setAutoCommit(true); //恢复自动提交模式
            String s = "select name,height from mess"+
                      " where number='R1001'or number='R1002'";
            rs = 
            sql.executeQuery(s);
            while(rs.next()){
               System.out.println("事务后"+rs.getString(1)+
                                  "身高:"+rs.getFloat(2));  
            }
            con.close();
         }
         catch(SQLException e){
            try{ con.rollback();          //撤销事务所做的操作
            }
            catch(SQLException exp){}
         }
    }
}
