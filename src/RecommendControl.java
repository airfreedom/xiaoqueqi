import javax.swing.*;
import java.sql.*;

public  class RecommendControl {
    String url="jdbc:mysql://localhost:3306/usermember";
    Connection con;
    Statement st1;

    public void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动!");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
        try {
            con= DriverManager.getConnection(url,"root","a1s2d3f4");
            st1=con.createStatement();
            System.out.println("成功加载数据库");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewRecommend(String name,String zhicheng,String workplace,String zhiwu,
                                String phoneNumber,String email,String beRecommend,String reason){
        String sql_Insert="insert into recommendPerson(recommendName,zhicheng,workplace,zhiwu,phoneNumber,email,beRecommend,reason)"
                +"values(?,?,?,?,?,?,?,?)";
        if(verification(name, zhicheng, workplace, zhiwu, phoneNumber, email, beRecommend, reason)==0){
            System.out.println("1");
            JOptionPane.showMessageDialog(null, "提交失败", "通知", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            try {
                PreparedStatement ps=con.prepareStatement(sql_Insert);
                ps.setString(1,name);
                ps.setString(2,zhicheng);
                ps.setString(3,workplace);
                ps.setString(4,zhiwu);
                ps.setString(5,phoneNumber);
                ps.setString(6,email);
                ps.setString(7,beRecommend);
                ps.setString(8,reason);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "提交成功", "通知", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "提交失败", "通知", JOptionPane.PLAIN_MESSAGE);
                e.printStackTrace();
            }
        }

    }

    public int verification(String name,String zhicheng,String workplace,String zhiwu,
                            String phoneNumber,String email,String beRecommend,String reason){
        if(!name.isEmpty() && !zhicheng.isEmpty() && !workplace.isEmpty() && !zhiwu.isEmpty() && !phoneNumber.isEmpty()
                && !email.isEmpty() && !beRecommend.isEmpty() && !reason.isEmpty()){
            return 1;
        }
        else {
            return 0;
        }
    }

    public void loadPerson(RecommendPerson recommendPerson,String name){
        String sql_Select="Select * from recommendperson where beRecommend='"+name+"'";
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while(rt.next()){
                recommendPerson.setRecommendName(rt.getString("recommendName"));
                recommendPerson.setWorkplace(rt.getString("workplace"));
                recommendPerson.setPhoneNumber(rt.getString("phoneNumber"));
                recommendPerson.setBeRecommend(rt.getString("beRecommend"));
                recommendPerson.setEmail(rt.getString("email"));
                recommendPerson.setZhicheng(rt.getString("zhicheng"));
                recommendPerson.setZhiwu(rt.getString("zhiwu"));
                recommendPerson.setReason(rt.getString("reason"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
