import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class idenManage extends  JPanel{

    private JPanel panel1;
    private JButton 同意Button;
    private JButton 拒绝Button;
    private JPanel center;
    private JTable table;

    idenManage() {
        //使用自定义table类型
        table = new JTable(new MyTableModel());
        //设置大小目前不管
        table.setPreferredScrollableViewportSize(new Dimension(680, 500));
        table.setFillsViewportHeight(true);

        JScrollPane jp = new JScrollPane(table);
        center.add(jp);

        panel1.setPreferredSize(new Dimension(690,555));
        this.add(panel1);


        //数据库中标注为会员
        同意Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> memberName=new ArrayList<String>();
                memberName=getSelectedName();
                String name;

                for(int i=0;i<memberName.size();i++){
                    name=memberName.get(i);
                    ModificationPerson modificationPerson=new ModificationPerson();
                    modificationPerson.addNewMember(name);
                }

            }
        });

        //将信息从数据库中删除
        拒绝Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> memberName=new ArrayList<String>();
                memberName=getSelectedName();
                String name;

                for(int i=0;i<memberName.size();i++){
                    name=memberName.get(i);
                    ModificationPerson modificationPerson=new ModificationPerson();
                    modificationPerson.deleteUnMember(name);
                }

            }
        });

        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nColumn = table.getSelectedColumn();

                if(nColumn == 3){
                    int nRow = table.getSelectedRow();
                    String value = String.valueOf(table.getValueAt(nRow,0));
                    String name = String.valueOf(table.getValueAt(nRow,1));
                    new showFrom(value,name);
                }
            }
        });

    }

    private ArrayList<String> getSelectedName(){
        int rCount = table.getRowCount();
        ArrayList<String> as = new ArrayList<String>();
        for(int i = 0 ; i < rCount ; i ++){
            if( String.valueOf(table.getValueAt(i,2)) == "true"){
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
            ModificationPerson modificationPerson=new ModificationPerson();
            count=modificationPerson.countUnMember();
            data=new Object[count][4];
            modificationPerson.setManagePerson(data);
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
            if (col < 2) {
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
            String[] s = {"编号","姓名","选择","申请信息和推荐表"};
            return s;
        }

        //返回表中数据
       /* public void readDatabase(Object[][] data){
            ModificationPerson modificationPerson=new ModificationPerson();
            modificationPerson.setManagePerson(data);
        }*/
    }
}

