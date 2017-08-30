import java.sql.*;
public class RegistrationControl {
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

    public int verification(RegistrationPerson person){
        if(person.getPname().isEmpty() && person.getPbirthday().isEmpty() && person.getPphoneNumber().isEmpty()){
            return 0;
        }
        else{
            addNewData(person);
            return 1;
        }
    }

    private void addNewData(RegistrationPerson person){
        String sql_Insert="insert into member(name,gender,birthday,phonenumber,recommend,hangyefenhui,zhuanweihui,member)"
                +" values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Insert);
            ps.setString(1,person.getPname());
            ps.setString(2,person.getPgender());
            ps.setString(3,person.getPbirthday());
            //ps.setString(4,person.getPaddress());
            ps.setString(4,person.getPphoneNumber());
            ps.setString(5,person.getPrecommendPerson());
            ps.setString(6,person.getPhangyefenhui());
            ps.setString(7,person.getPzhuanweihui());
            ps.setString(8,"0");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args){
        RegistrationControl temp=new RegistrationControl();
        temp.getConnection();
    }*/
}
