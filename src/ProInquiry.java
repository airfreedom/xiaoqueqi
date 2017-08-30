import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProInquiry extends JPanel {
    public static void main(String args[]){
        new ProInquiry();
    }
    public CardLayout card;
    public JPanel dyq;

    ProInquiry(){
        dyq = new JPanel();
        card = new CardLayout();
        dyq.setLayout(card);

        dyq.add(new ProList(this));
        dyq.add(new ProDetail(this));

        this.add(dyq);
    }
}

class ProList extends JPanel {
        private JPanel bottom;
        private JLabel title;
        private JPanel buttonFace;
        private JScrollPane tableFace;
        private JButton button1;
        private JPanel center;
        private JComboBox comboBox1;
        private JLabel 选择编号;
        private JButton 查询详细内容;
        private JTable table1;

        //创建表头
        String[] columnNames = { "编号", "提案名称", "作者",
                "截止日期", "状态","附议数","反对数" };
        //创建数据
        Object[][] data = {
                { 1, "Smith", "Snowboarding", new Integer(5),
                        new Boolean(false),1,2 },
                { 2, "Doe", "Rowing", new Integer(3), new Boolean(true) ,3,4},
        };

        ProList(ProInquiry pi) {
            //表格相关
            table1 = new JTable(data, columnNames);
            tableFace = new JScrollPane(table1);
            center.add(BorderLayout.CENTER,tableFace);

            this.add(bottom);

            查询详细内容.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pi.card.next(pi.dyq);
                }
            });
        }

        private void toDetail(){
            this.setVisible(false);
        }
    }

class ProDetail extends JPanel{

    private JPanel bottom;
    private JLabel title;
    private JPanel south;
    private JTextPane textPane1;
    private JButton button1;
    private JPanel center;
    private JTextField textField1;
    private JRadioButton 附议RadioButton;
    private JRadioButton 反对RadioButton;
    private JButton 提交;
    private JTextField textField2;
    private JTextPane textPane2;
    private JTextPane textPane3;
    private JTextPane textPane4;
    private JTextPane textPane5;
    private JScrollPane tableFace;
    private JTable table1;

    //创建表头
    String[] columnNames = { "编号", "提案名称", "作者",
            "截止日期", "状态","附议数","反对数" };
    //创建数据
    Object[][] data = {
            { 1, "Smith", "Snowboarding", new Integer(5),
                    new Boolean(false),1,2 },
            { 2, "Doe", "Rowing", new Integer(3), new Boolean(true) ,3,4},
    };

    ProDetail(ProInquiry pi) {
        //表格相关
        table1 = new JTable(data, columnNames);

        south.setPreferredSize(new Dimension(200,50));

        tableFace = new JScrollPane(table1);
        south.add(BorderLayout.CENTER,tableFace);

        this.add(bottom);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pi.card.previous(pi.dyq);
            }
        });
    }


}