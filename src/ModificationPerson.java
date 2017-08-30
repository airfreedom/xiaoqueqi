import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ModificationPerson {
    String pid;
    String pname;
    String paddress;
    String pphonenumber;
    String pworkplace;
    String pemail;
    String pgender;
    String precommendPerson;
    String pbirthday;


    public void setPid(String id){
        pid=id;
    }
    public void setPname(String name){
        pname=name;
    }
    public void setPaddress(String address){
        paddress=address;
    }
    public void setPphonenumber(String phonenumber){
        pphonenumber=phonenumber;
    }
    public void setPworkplace(String workplace){
        pworkplace=workplace;
    }
    public void setPemail(String email){
        pemail=email;
    }
    public void setPgender(String gender){
        pgender=gender;
    }
    public void setPrecommendPerson(String recommendPerson){
        precommendPerson=recommendPerson;
    }
    public void setPbirthday(String birthday){
        pbirthday=birthday;
    }
    public String getPid(){
        return pid;
    }
    public String getPname(){
        return pname;
    }
    public String getPaddress(){
        return paddress;
    }
    public String getPphonenumber(){
        return pphonenumber;
    }
    public String getPworkplace(){
        return pworkplace;
    }
    public String getPemail(){
        return pemail;
    }
    public String getPgender(){
        return pgender;
    }
    public String getPrecommendPerson(){
        return precommendPerson;
    }
    public String getPbirthday(){
        return pbirthday;
    }

    public void setPerson(String id){
        ModificationControl modificationControl=new ModificationControl();
        modificationControl.getConnection();
        modificationControl.loadPerson(id);
    }

    public void savePerson(String address,String phonenumber,String workplace,String email,String id){
        ModificationControl modificationControl=new ModificationControl();
        modificationControl.getConnection();
        modificationControl.save(address,phonenumber,workplace,email,id);
    }

    public void setManagePerson(Object[][] data){
        ModificationControl modificationControl=new ModificationControl();
        modificationControl.getConnection();
        modificationControl.showPerson(data);
    }

    public void addNewMember(String name){
        ModificationControl modificationControl=new ModificationControl();
        modificationControl.getConnection();
        modificationControl.addNewData(name);

    }

    public int countUnMember(){
        ModificationControl modificationControl=new ModificationControl();
        modificationControl.getConnection();
        return modificationControl.countUn();
    }

    public void deleteUnMember(String name){
        ModificationControl modificationControl=new ModificationControl();
        modificationControl.getConnection();
        modificationControl.deleteUn(name);
    }

    class ModificationControl {
        String url = "jdbc:mysql://localhost:3306/usermember";
        Connection conn;
        Statement st1;
        Statement st2;

        public void getConnection() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("成功加载MySQL驱动!");
            } catch (ClassNotFoundException e) {
                // TODO 自动生成的 catch 块
                System.out.println("找不到MySQL驱动!");
                e.printStackTrace();
            }

            try {
                conn = DriverManager.getConnection(url, "root", "a1s2d3f4");
                st1 = conn.createStatement();
                st2 = conn.createStatement();
                System.out.println("成功连接到数据库!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void loadPerson(String id){
            String sql_select="select id,name,address,phonenumber,workplace,email," +
                    "gender,recommend,birthday from member where id='"+id+"'";
            try {
                ResultSet rt_select=st1.executeQuery(sql_select);
                while(rt_select.next()){
                    setPid(rt_select.getString("id"));
                    setPname(rt_select.getString("name"));
                    setPaddress(rt_select.getString("address"));
                    setPphonenumber(rt_select.getString("phonenumber"));
                    setPworkplace(rt_select.getString("workplace"));
                    setPemail(rt_select.getString("email"));
                    setPgender(rt_select.getString("gender"));
                    setPrecommendPerson(rt_select.getString("recommend"));
                    setPbirthday(rt_select.getString("birthday"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void save(String address,String phonenumber,String workplace,String email,String id){
            String sql_update="update member set address=?, phonenumber=?, workplace=?, email=? where id=?";
            System.out.println(phonenumber);
            try {
                PreparedStatement ps=conn.prepareStatement(sql_update);
                ps.setString(1,address);
                ps.setString(2,phonenumber);
                ps.setString(3,workplace);
                ps.setString(4,email);
                ps.setString(5,id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void showPerson(Object[][] data){
            String sql_Select="select id,name from member where member= '0'";
            int i=0;
            //Object o[][]=new Object[10][2];
            try {
                ResultSet rs=st1.executeQuery(sql_Select);
                while(rs.next()){
                   data[i][0]=rs.getString("id");
                   data[i][1]=rs.getString("name");
                   data[i][2]=false;
                   data[i][3]="查看";
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //return  o;
        }

        public void addNewData(String name){
            String sql_Update="update member set member=? where name=?";
            try {
                PreparedStatement ps=conn.prepareStatement(sql_Update);
                ps.setString(1,"1");
                ps.setString(2,name);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "操作失败", "通知", JOptionPane.PLAIN_MESSAGE);
            }
        }

        public int countUn(){
            int count=0;
            String sql_select="select * from member where member='0'";
            try {
                ResultSet rt=st1.executeQuery(sql_select);
                while (rt.next()){
                    count++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return count;

        }

        public void deleteUn(String name){
            String sql_Delete="delete from member where name=?";
            try {
                PreparedStatement ps=conn.prepareStatement(sql_Delete);
                ps.setString(1,name);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}


