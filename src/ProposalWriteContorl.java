import javax.swing.*;
import java.sql.*;
import java.util.Calendar;

public class ProposalWriteContorl {
    String url="jdbc:mysql://localhost:3306/usermember";
    Connection conn;
    Statement st1;
    Statement st2;
    Proposal proposal;

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

    public void addNewProposal(String proposalName,String deadline,String content,String id){


        if(verification(proposalName,deadline,content)==0){

            JOptionPane.showMessageDialog(null, "有内容未填写", "通知", JOptionPane.PLAIN_MESSAGE);
        }
        else{

            if(countProposalNumber(id)>3){
                JOptionPane.showMessageDialog(null, "提案数量超过上限", "通知", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            else{
                ModificationPerson modificationPerson=new ModificationPerson();
                modificationPerson.setPerson(id);
                Calendar now = Calendar.getInstance();
                String year=String.valueOf(now.get(Calendar.YEAR));
                String month=String.valueOf(now.get(Calendar.MONTH));
                String day=String.valueOf(now.get(Calendar.DAY_OF_MONTH));
                String submitTime=year+"-"+month+"-"+day;

                System.out.println(submitTime);

                String sql_Insert="insert into proposal(proposalName,proposalWriter,deadline,content,submitTime,writerID)"
                        +"values(?,?,?,?,?,?)";
                try {
                    PreparedStatement ps=conn.prepareStatement(sql_Insert);
                    ps.setString(1,proposalName);
                    ps.setString(2,modificationPerson.getPname());
                    ps.setString(3,deadline);
                    ps.setString(4,content);
                    ps.setString(5,submitTime);
                    ps.setString(6,id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "提交成功", "通知", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private int verification(String proposalName,String deadline,String content){
        if(!proposalName.isEmpty() && !deadline.isEmpty() && !content.isEmpty()){
            return 1;
        }
        else{
            return 0;
        }
    }

    private int countProposalNumber(String id){
        int count=0;
        String sql_Select="select proposalid from proposal where writerID='"+id+"'";
        try {
            ResultSet rt=st2.executeQuery(sql_Select);
            while(rt.next()){
                count++;
            }
            System.out.println(count);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countZhuanweihuiProposal(){
        int count=0;
        String sql_select="select * from proposal where status=1";
        try {
            ResultSet rt=st1.executeQuery(sql_select);
            while(rt.next()){
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    public int countHangyefenhuiProposal(){
        int count=0;
        String sql_select="select * from proposal where status=2";
        try {
            ResultSet rt=st1.executeQuery(sql_select);
            while(rt.next()){
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    public int countYanjiuhuiProposal(){
        int count=0;
        String sql_select="select * from proposal where status=3";
        try {
            ResultSet rt=st1.executeQuery(sql_select);
            while(rt.next()){
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void showRecommendProposal(Object[][] data){

        String sql_Select="select proposalid,proposalName,proposalWriter from proposal where status='1'";
        int j=0;
        try {
            ResultSet rt=st2.executeQuery(sql_Select);
            while(rt.next()){
                data[j][0]=rt.getString("proposalid");
                data[j][1]=rt.getString("proposalName");
                data[j][2]=rt.getString("proposalWriter");
                data[j][3]=false;
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showBeianProposal(Object[][] data){
        String sql_select="select proposalid,proposalName,proposalWriter,recommendPerson from proposal where status='2'";
        int j=0;
        try {
            ResultSet rt=st1.executeQuery(sql_select);
            while(rt.next()){
                data[j][0]=rt.getString("proposalid");
                data[j][1]=rt.getString("proposalName");
                data[j][2]=rt.getString("proposalWriter");
                data[j][3]=rt.getString("recommendPerson");
                data[j][4]=false;
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showYanjiuhuiProposal(Object[][] data){
        String sql_select="select proposalid,proposalName,proposalWriter,recommendPerson,filingPerson from proposal where status='3'";
        int j=0;
        try {
            ResultSet rt=st1.executeQuery(sql_select);
            while(rt.next()){
                data[j][0]=rt.getString("proposalid");
                data[j][1]=rt.getString("proposalName");
                data[j][2]=rt.getString("proposalWriter");
                data[j][3]=rt.getString("recommendPerson");
                data[j][4]=rt.getString("filingPerson");
                data[j][5]=false;
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateZhuanweihuiProposal(String name){
        String sql_Update="update proposal set status=2,recommendPerson=? where proposalName=?";
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Update);
            ps.setString(1,"bjut");
            ps.setString(2,name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHangyefenhuiProposal(String name){
        String sql_Update="update proposal set status=3,filingPerson=? where proposalName=?";
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Update);
            ps.setString(1,"bjut");
            ps.setString(2,name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateYanjiuhuiProposal(String name){
        String sql_Update="update proposal set status=4 where proposalName=?";
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Update);
            ps.setString(1,name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProposal(String name){
        proposal=new Proposal();

        String sql_Select="select proposalid,proposalName,proposalWriter,deadline,content " +
                "from proposal where proposalName='"+name+"'";
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while(rt.next()){
                System.out.println(rt.getString("proposalid"));
                proposal.setProposalid(rt.getString("proposalid"));

                proposal.setProposalname(rt.getString("proposalName"));
                proposal.setProposalwriter(rt.getString("proposalWriter"));
                proposal.setDeadline(rt.getString("deadline"));
                proposal.setContent(rt.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void addProposalLikes(String name){
        String sql_Update="update proposal set likes=? where proposalName=?";
        String sql_Select="select likes from proposal where proposalName='"+name+"'";
        int likes=0;
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while(rt.next()){
                likes=rt.getInt("likes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        likes++;
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Update);
            ps.setInt(1,likes);
            ps.setString(2,name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addProposalDisagree(String name){
        String sql_Update="update proposal set disagree=? where proposalName=?";
        String sql_Select="select disagree from proposal where proposalName='"+name+"'";
        int disagree=0;
        try {
            ResultSet rt=st1.executeQuery(sql_Select);
            while(rt.next()){
                disagree=rt.getInt("disagree");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disagree++;
        try {
            PreparedStatement ps=conn.prepareStatement(sql_Update);
            ps.setInt(1,disagree);
            ps.setString(2,name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
