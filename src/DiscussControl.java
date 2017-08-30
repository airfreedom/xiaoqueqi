import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.Calendar;

public class DiscussControl {
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

    public int countDiscussNumber(String proposalName){
        int count=0;
        String sql_Select="select * from discuss where proposalName='"+proposalName+"'";
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while(rt.next()){
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void showDiscuss(String proposalName,Object[][] data){
        String sql_Select="select * from discuss where proposalid=1";
        int i=0;
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while(rt.next()){
                data[i][0]=rt.getString("discussName");
                data[i][1]=rt.getString("discussContent");
                data[i][2]=rt.getString("discussSubmit");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDiscuss(String name,String content,String id){
        String sql_Insert="insert into discuss(discussName,discussContent,discussSubmit,proposalid) values(?,?,?,?)";
        Calendar now = Calendar.getInstance();
        String year=String.valueOf(now.get(Calendar.YEAR));
        String month=String.valueOf(now.get(Calendar.MONTH));
        String day=String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        String submitTime=year+"-"+month+"-"+day;
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Insert);
            ps.setString(1,name);
            ps.setString(2,content);
            ps.setString(3,submitTime);
            ps.setString(4,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
