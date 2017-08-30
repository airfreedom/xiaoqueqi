import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegistrationUI extends JFrame{
    JPanel writeArea=new JPanel(new GridLayout(8,2));
    JPanel buttonArea=new JPanel(new GridLayout(1,3));
    JTextField name=new JTextField(20);
    JComboBox gender=new JComboBox();
    JTextField birthday=new JTextField(20);
    JTextField address=new JTextField(20);
    JTextField phoneNumber=new JTextField(20);
    JTextField recommendPerson=new JTextField(20);
    JComboBox hangyefenhui=new JComboBox();
    JComboBox zhuanweihui=new JComboBox();
    Container contentPane;
    public RegistrationUI(){
        super("会员注册");
        contentPane=getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,500,50));
        JLabel jLabel1=new JLabel("姓名：");
        JLabel jLabel2=new JLabel("性别：");
        JLabel jLabel3=new JLabel("生日：");
        JLabel jLabel4=new JLabel("住址：");
        JLabel jLabel5=new JLabel("联系方式：");
        JLabel jLabel6=new JLabel("推荐人：");
        JLabel jLabel7=new JLabel("行业分会：");
        JLabel jLabel8=new JLabel("专委会：");
        gender.addItem("male");
        gender.addItem("female");
        writeArea.add(jLabel1);
        writeArea.add(name);
        writeArea.add(jLabel2);
        writeArea.add(gender);
        writeArea.add(jLabel3);
        writeArea.add(birthday);
        writeArea.add(jLabel4);
        writeArea.add(address);
        writeArea.add(jLabel5);
        writeArea.add(phoneNumber);
        writeArea.add(jLabel6);
        writeArea.add(recommendPerson);
        writeArea.add(jLabel7);
        writeArea.add(hangyefenhui);
        hangyefenhui.addItem("bjut");
        writeArea.add(jLabel8);
        writeArea.add(zhuanweihui);
        zhuanweihui.addItem("bjut");

        JButton submit=new JButton("提交");
        JButton cancel=new JButton("取消");
        JButton clear=new JButton("清空");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit_actionPerformed(e);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel_actionPerformed(e);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear_actionPerformed(e);
            }
        });
        buttonArea.add(submit);
        buttonArea.add(cancel);
        buttonArea.add(clear);
        getContentPane().add(writeArea);
        getContentPane().add(buttonArea);
        setSize(900,450);
        show();
    }

    /*清空按钮*/
    private void clear_actionPerformed(ActionEvent e) {
        name.setText("");
        birthday.setText("");
        address.setText("");
        phoneNumber.setText("");
        recommendPerson.setText("");
    }

    /*取消按钮*/
    private void cancel_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    /*提交按钮*/
    private void submit_actionPerformed(ActionEvent e) {
        RegistrationPerson temp_person=new RegistrationPerson();
        temp_person.setName(name.getText());
        temp_person.setGender(String.valueOf(gender.getSelectedItem()));
        temp_person.setBirthday(birthday.getText());
        temp_person.setAddress(address.getText());
        temp_person.setPhoneNumber(phoneNumber.getText());
        temp_person.setRecommendPerson(recommendPerson.getText());
        temp_person.setHangyefenhui(String.valueOf(hangyefenhui.getSelectedItem()));
        temp_person.setZhuanweihui(String.valueOf(zhuanweihui.getSelectedItem()));
        RegistrationControl rControl=new RegistrationControl();
        rControl.getConnection();
        if(rControl.verification(temp_person)==1){
            JOptionPane.showMessageDialog(null, "提交成功", "通知", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "提交失败", "通知", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args){
        RegistrationUI registrationUI=new RegistrationUI();
        registrationUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}