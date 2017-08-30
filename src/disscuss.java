import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class disscuss extends JPanel{

    private JPanel panel1;
    private JButton 返回Button;
    private JPanel center;
    private JTable table;
    private JScrollPane jp;

    disscuss(String proposalname,showPro sp){
        int count=0;
        DiscussControl discussControl=new DiscussControl();
        discussControl.getConnection();
        count=discussControl.countDiscussNumber(proposalname);
        Object[][] data=new Object[count][3];
        discussControl.showDiscuss(proposalname,data);

        table = new JTable(data,setColumnNames());
       // table.isVisible(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        jp = new JScrollPane(table);

        center.add(jp);
        this.add(panel1);
        返回Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sp.card.previous(sp.bottom);
               // sp.card.next(sp);
            }
        });
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
