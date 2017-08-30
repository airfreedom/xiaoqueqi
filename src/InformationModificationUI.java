import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationModificationUI extends JPanel{
    JTextField address=new JTextField(20);
    JTextField phoneNumber=new JTextField(20);
    JTextField workplace=new JTextField(20);
    JTextField email=new JTextField(20);
    JButton save= new JButton("保存");
    JButton cancel=new JButton("还原");
    JPanel modificationArea=new JPanel(new GridLayout(3,4));
    JPanel buttonArea=new JPanel(new FlowLayout());

    ModificationPerson modificationPerson=new ModificationPerson();

    public InformationModificationUI(String id){
        modificationPerson.setPerson(id);
        this.setLayout(new GridLayout(2,1));
        JLabel jLabel1=new JLabel("id:");
        JLabel jLabel2=new JLabel(modificationPerson.getPid());
        JLabel jLabel3=new JLabel("name:");
        JLabel jLabel4=new JLabel(modificationPerson.getPname());
        JLabel jLabel5=new JLabel("address:");
        address.setText(modificationPerson.getPaddress());
        JLabel jLabel6=new JLabel("phonenumber:");
        phoneNumber.setText(modificationPerson.getPphonenumber());
        JLabel jLabel7=new JLabel("workplace:");
        workplace.setText(modificationPerson.getPworkplace());
        JLabel jLabel8=new JLabel("email:");
        email.setText(modificationPerson.getPemail());
        modificationArea.add(jLabel1);
        modificationArea.add(jLabel2);
        modificationArea.add(jLabel3);
        modificationArea.add(jLabel4);
        modificationArea.add(jLabel5);
        modificationArea.add(address);
        modificationArea.add(jLabel6);
        modificationArea.add(phoneNumber);
        modificationArea.add(jLabel7);
        modificationArea.add(workplace);
        modificationArea.add(jLabel8);
        modificationArea.add(email);

        buttonArea.add(save);
        buttonArea.add(cancel);

        this.add(modificationArea);
        this.add(buttonArea);



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _address=address.getText();
                String _phonenumber=phoneNumber.getText();
                String _workplace=workplace.getText();
                String _email=email.getText();
                modificationPerson.savePerson(_address,_phonenumber,_workplace,_email,modificationPerson.getPid());
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificationPerson.setPerson(id);
                address.setText(modificationPerson.getPaddress());
                phoneNumber.setText(modificationPerson.getPphonenumber());
                workplace.setText(modificationPerson.getPworkplace());
                email.setText(modificationPerson.getPemail());
            }
        });

    }

    /*public static void main(String[] arges){
        InformationModificationUI informationModificationUI=new InformationModificationUI("1");
    }*/

}
