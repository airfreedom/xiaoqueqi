import javax.swing.*;
import java.awt.*;

public class showRecommend extends JPanel{

    private JTextField workplace;
    private JTextField phoneNumber;
    private JTextField zhiwu;
    private JTextField beRecommend;
    private JTextField zhicheng;
    private JTextField email;
    private JTextArea reason;
    private JPanel bottom;
    private JTextField recommendName;

    public showRecommend(String beRecommendName){
        RecommendPerson recommendPerson=new RecommendPerson();
        RecommendControl recommendControl=new RecommendControl();
        recommendControl.getConnection();
        recommendControl.loadPerson(recommendPerson,beRecommendName);
        recommendName.setText(recommendPerson.getRecommendName());
        workplace.setText(recommendPerson.getWorkplace());
        phoneNumber.setText(recommendPerson.getPhoneNumber());
        zhicheng.setText(recommendPerson.getZhicheng());
        zhiwu.setText(recommendPerson.getZhiwu());
        reason.setText(recommendPerson.getReason());
        beRecommend.setText(recommendPerson.getBeRecommend());
        email.setText(recommendPerson.getEmail());

        bottom.setPreferredSize(new Dimension(380,600));
        this.setPreferredSize(new Dimension(400,600));
        this.add(bottom);
        this.setVisible(true);
    }
}
