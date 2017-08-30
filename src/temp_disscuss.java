import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class temp_disscuss {
    public static void main(String[] args) {
        JFrame frame = new JFrame("disscuss");
        frame.setContentPane(new temp_disscuss("1").panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;
    private JButton 返回Button;
    private JPanel center;
    private JTable table;
    private JScrollPane jp;

    temp_disscuss(String proposalid){
        int count=0;
        temp_DiscussControl discussControl=new temp_DiscussControl();
        discussControl.getConnection();
        count=discussControl.countDiscussNumber(proposalid);
        Object[][] data=new Object[count][3];
        discussControl.showDiscuss(proposalid,data);
        table = new JTable(data,setColumnNames());
       // table.isVisible(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        jp = new JScrollPane(table);

        center.add(jp);
    }
    private String[] setColumnNames(){
        String[] s = {"评论人","评论内容","时间"};
        return s;
    }

    //返回表中数据
   /* private  readDatabase(){
        Object o[][] = {{"1","dyq","nan"},
                {"2","dyw","nv"}};
        return o;
    }*/
}
