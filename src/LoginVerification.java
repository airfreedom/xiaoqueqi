import java.sql.*;

public class LoginVerification {
    String url="jdbc:mysql://localhost:3306/usermember";
    Connection conn;
    Statement st1;
    Statement st2;
    public void getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动!");
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }

        try{
            conn= DriverManager.getConnection(url,"root","a1s2d3f4");
            st1=conn.createStatement();
            st2=conn.createStatement();
            System.out.println("成功连接到数据库!");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int Verification(String name,String password){
        String sql_Select="select password from member where name='"+name+ "';";
        try {
            ResultSet rt_Select=st1.executeQuery(sql_Select);
            while(rt_Select.next()){
                //System.out.println(rt_Select.getString());
                if(rt_Select.getString("password").equals(password)) {
                    return 1;
                }
            }

            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getId(String name,String password){
        String sql_Select_id="select id from member where name='"+name+"' and password='"+password+"'";
        try {
            ResultSet rt_Select_id=st2.executeQuery(sql_Select_id);
            while (rt_Select_id.next()){
                return rt_Select_id.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getAuthority(String name,String password){
        String sql_Select="select authority from member where name='"+name+"' and password='"+password+"'";
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while (rt.next()){
                return rt.getString("authority");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
