import javax.swing.*;
import java.awt.*;

public class showApply extends JPanel{
    private JTextField gender;
    private JTextField birthday;
    private JTextField recommendPerson;
    private JTextField address;
    private JTextField phoneNumber;
    private JTextArea applyReason;
    private JPanel bm;
    private JTextField name;

    public showApply(String id){
        ModificationPerson modificationPerson=new ModificationPerson();
        modificationPerson.setPerson(id);
        name.setText(modificationPerson.getPname());
        gender.setText(modificationPerson.getPgender());
        birthday.setText(modificationPerson.getPbirthday());
        address.setText(modificationPerson.getPaddress());
        phoneNumber.setText(modificationPerson.getPphonenumber());
        recommendPerson.setText(modificationPerson.getPrecommendPerson());

        bm.setPreferredSize(new Dimension(380,600));
        this.setPreferredSize(new Dimension(400,600));
        this.add(bm);
        this.setVisible(true);

    }
}
