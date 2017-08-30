import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class proposalDetails extends JFrame{

    private JPanel bottom;
    private JPanel center;
    private JTextField proposalWriter;
    private JTextField deadline;
    private JTextField proposalName;
    private JTextField proposalid;
    private JTextArea content;
    private JTextField discuss;
    private JRadioButton 附议RadioButton;
    private JRadioButton 反对RadioButton;
    private JButton 提交Button;
    private JButton 查看评论区Button;

    public proposalDetails(String name,showPro sp) {

        this.setPreferredSize(new Dimension(800,600));
        this.pack();
        this.setVisible(true);
        this.add(bottom);

        readDatabase(name);

        提交Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                writeDiscussDatabase(proposalName.getText());
            }
        });

        查看评论区Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               sp.card.next(sp.bottom);
               //sp.card.previous(sp.bottom);

            }
        });
    }
    private void writeDiscussDatabase(String name){
        String select = new String();
        ProposalWriteContorl proposalWriteContorl=new ProposalWriteContorl();
        proposalWriteContorl.getConnection();
        DiscussControl discussControl=new DiscussControl();
        discussControl.getConnection();
        String id=proposalid.getText();
        String content=discuss.getText();
        String discussname=proposalWriter.getText();

        if(附议RadioButton.isSelected()){
            select = "附议";
            proposalWriteContorl.addProposalLikes(name);

        }
        else if(反对RadioButton.isSelected()){
            select = "反对";
            proposalWriteContorl.addProposalDisagree(name);
        }
        discussControl.addDiscuss(discussname,content,id);
    }

    private void readDatabase(String name){
        ProposalWriteContorl proposalWriteContorl=new ProposalWriteContorl();
        proposalWriteContorl.getConnection();
        proposalWriteContorl.loadProposal(name);
        proposalid.setText(proposalWriteContorl.getProposal().getProposalid());
        proposalName.setText(proposalWriteContorl.getProposal().getProposalname());
        proposalWriter.setText(proposalWriteContorl.getProposal().getProposalwriter());
        deadline.setText(proposalWriteContorl.getProposal().getDeadline());
        content.setText(proposalWriteContorl.getProposal().getContent());
    }
}
