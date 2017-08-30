import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class recommendForm extends JPanel{
    //测试使用
    public static void main(String[] args) {
        JFrame frame = new JFrame("recommendForm");
        frame.setContentPane(new recommendForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //控件
    private JPanel panel1;
    private JButton 提交Button;
    private JTextField Jworkplace;
    private JTextField JphoneNumber;
    private JTextField Jzhiwu;
    private JTextField JbeRecommend;
    private JTextField Jzhicheng;
    private JTextField Jemail;
    private JTextArea Jreason;
    private JTextField JrecommendName;

    //提交事件
    public recommendForm() {
        this.add(panel1);
        提交Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name=JrecommendName.getText();
                String zhicheng= Jzhicheng.getText();
                String workplace=Jworkplace.getText();
                String zhiwu=Jzhiwu.getText();
                String berecommend=JbeRecommend.getText();
                String email=Jemail.getText();
                String reason=Jreason.getText();
                String phoneNumber=JphoneNumber.getText();
                writeDatabase(name,zhicheng,workplace,zhiwu,phoneNumber,email,berecommend,reason);
            }
        });
    }

    //写入数据库
    private void writeDatabase(String name,String zhicheng,String workplace,String zhiwu,
                               String phonenumber, String email,String berecommend,String reason){
        RecommendControl recommendControl=new RecommendControl();
        recommendControl.getConnection();
        recommendControl.addNewRecommend(name,zhicheng,workplace,zhiwu,phonenumber,email,berecommend,reason);
    }

}
