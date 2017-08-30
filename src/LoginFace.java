import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFace extends JFrame{
    public String id;
    public String authority;
    public static void main(String[] args) {
        new LoginFace();
    }
    //构建组件
    private JPanel bottom;
    private JLabel title;
    private JPanel login;
    private JTextField nameTextField;
    private JPasswordField passwordField;
    private JButton 登录Button;
    private JButton 注册Button;
    private JLabel password;
    private JLabel username;
    private JTextPane 规范列表TextPane;

    public LoginFace() {
        //JLabel 默认是透明的，Opaque则为不透明
        title.setOpaque(true);
        //是否能用
        //title.setEnabled(false);
        登录Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        注册Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //显示登录界面
        this.add(bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        //为登录按钮注册监听，当按下登录时，自动检测姓名和密码
        登录Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ControlLayer.verification(getname(),getpw()) == 1){
                    LoginVerification loginVerification=new LoginVerification();
                    loginVerification.getConnection();
                    id=loginVerification.getId(getname(),getpw());
                    authority=loginVerification.getAuthority(getname(),getpw());
                    if(Integer.parseInt(authority,10)>1){
                        toManageFace(id,authority);
                    }
                    else {
                        toWriteFace(id);
                    }

                }
            }
        });
        //为注册按钮注册监听，按下时，弹出注册界面
        注册Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegistrationUI registrationUI=new RegistrationUI();
            }
        });
    }

    private String getname(){
        return nameTextField.getText();
    }//获得名字
    private String getpw(){
        return String.valueOf(passwordField.getPassword());
    }//获得密码

    private void toWriteFace(String id){
        this.dispose();
        new writeFace(id);
    }

    private void toManageFace(String id,String authority){
        this.dispose();
        new managerFace(id,authority);
    }

}

class ControlLayer {

    //验证用户名密码是否正确
    public static int verification(String name, String pw) {
        int num;
        //当用户名和密码有一为空时
        if(name.isEmpty()||pw.isEmpty()){
            num = 0;
        }
        //不为空则在数据库中查询
        else
            num = searchDB(name, pw);
        switch (num) {
            case 0:
                showDialog("用户名或密码错误",1);
                break;
            case 1:
                showDialog("登陆成功",2);
                break;
        }
        return num;
    }
    //type 1是错误模板，2是正确模板
    private static void showDialog(String s,int type){
        if(type == 1)
            JOptionPane.showMessageDialog(null,s,null,JOptionPane.ERROR_MESSAGE);
        //else
           // JOptionPane.showMessageDialog(null,s,null,JOptionPane.PLAIN_MESSAGE);
    }
    //从数据库中寻找,return 1 表示登陆成功 ，0 表示失败
    private static int searchDB(String name, String pw) {
        LoginVerification loginVerification=new LoginVerification();
        loginVerification.getConnection();
        return loginVerification.Verification(name,pw);
    }

}


