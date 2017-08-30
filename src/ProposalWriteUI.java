import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProposalWriteUI extends JPanel{


    private JPanel northJpanel=new JPanel(new GridLayout(1,4));
    private JTextArea content=new JTextArea();
    private JPanel eastJpanel=new JPanel(new GridLayout(3,1));
    private JPanel southJpanel=new JPanel();

    private JTextField proposalName=new JTextField();
    private JTextField deadline=new JTextField();

    private JButton save=new JButton("保存");
    private JButton print=new JButton("打印");
    private JButton submit=new JButton("提交");

    private JLabel jlabel_name=new JLabel("提案名字");
    private JLabel jlabel_deadline=new JLabel("截止日期");
    private JLabel jlabel_content=new JLabel("提案内容");

    public ProposalWriteUI(String id){
        this.setLayout(new BorderLayout());
        this.add(northJpanel,BorderLayout.NORTH);
        this.add(content,BorderLayout.CENTER);
        this.add(jlabel_content,BorderLayout.WEST);
        this.add(eastJpanel,BorderLayout.EAST);
        this.add(southJpanel,BorderLayout.SOUTH);

        northJpanel.add(jlabel_name);
        northJpanel.add(proposalName);
        northJpanel.add(jlabel_deadline);
        northJpanel.add(deadline);

        eastJpanel.add(save);
        eastJpanel.add(print);
        eastJpanel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String String_proposalName=proposalName.getText();
                String String_deadline=deadline.getText();
                String String_content=content.getText();
                saveProposal(String_proposalName,String_deadline,String_content,id);
            }
        });

    }

    private void saveProposal(String String_proposalName,String String_deadline,String String_content,String id){
        ProposalWriteContorl proposalWriteContorl=new ProposalWriteContorl();
        proposalWriteContorl.getConnection();
        //System.out.println(proposalWriteContorl.countProposalNumber("1"));

        proposalWriteContorl.addNewProposal(String_proposalName,String_deadline,String_content,id);

    }


}
