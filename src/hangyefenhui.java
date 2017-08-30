import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class hangyefenhui extends JPanel{
    public static void main(String[] args) {
        JFrame frame = new JFrame("hangyefenhui");
        frame.setContentPane(new hangyefenhui().bottom);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton 备案;
    private JPanel center;
    private JPanel bottom;
    private JTable table;

    hangyefenhui() {
        //使用自定义table类型
        table = new JTable(new MyTableModel());
        //设置大小目前不管
        table.setPreferredScrollableViewportSize(new Dimension(680, 500));
        table.setFillsViewportHeight(true);

        JScrollPane jp = new JScrollPane(table);

        center.setPreferredSize(new Dimension(690,500));
        center.add(jp);

        this.add(bottom);

        备案.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> proposalName=new ArrayList<String>();
                proposalName=getSelectedName();
                String name;

                for(int i=0;i<proposalName.size();i++) {
                    name=proposalName.get(i);
                    ProposalWriteContorl proposalWriteContorl=new ProposalWriteContorl();
                    proposalWriteContorl.getConnection();
                    proposalWriteContorl.updateHangyefenhuiProposal(name);
                }


            }
        });
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nColumn = table.getSelectedColumn();

                if(nColumn == 1){
                    int nRow = table.getSelectedRow();
                    String name = String.valueOf(table.getValueAt(nRow,1));
                    new showPro(name);
                }
            }
        });
    }

    private ArrayList<String> getSelectedName(){
        int rCount = table.getRowCount();
        ArrayList<String> as = new ArrayList<String>();
        for(int i = 0 ; i < rCount ; i ++){
            if( String.valueOf(table.getValueAt(i,4)) == "true"){
                as.add(String.valueOf(table.getValueAt(i,1)));
            }
        }
        return as;
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = setColumnNames();
        private Object[][] data ;

        public MyTableModel(){
            int count=0;
            ProposalWriteContorl proposalWriteContorl=new ProposalWriteContorl();
            proposalWriteContorl.getConnection();
            count=proposalWriteContorl.countHangyefenhuiProposal();
            data=new Object[count][5];
            proposalWriteContorl.showBeianProposal(data);
            //readDatabase(data);
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        //变成checkbox
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        //返回true则是可以编辑的
        public boolean isCellEditable(int row, int col){
            if (col < 4) {
                return false;
            }
            else {
                return true;
            }
        }

        //能改变数据
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        //返回字段名
        private String[] setColumnNames(){
            String[] s = {"编号","提案名称","提案人","专委会","选择"};
            return s;
        }

        //返回表中数据
        /*private void readDatabase(Object[][] data){
            ProposalWriteContorl proposalWriteContorl=new ProposalWriteContorl();
            proposalWriteContorl.getConnection();
            proposalWriteContorl.showBeianProposal(data);
        }*/
    }
}
