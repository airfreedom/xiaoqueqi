import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class writeFace extends JFrame{
   public static void main(String args[]){
        new writeFace("1");
    }

    private JPanel bottom;
    private JButton 提案查询;
    private JPanel show;
    private JButton 信息维护Button;
    private JButton 规范编制Button;
    private JButton 提案编制Button;
    private JButton 规范查询Button;
    private CardLayout card;

    public writeFace(String id){
        //this.setSize(1000,500);
        this.add(bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setVisible(true);
        card = new CardLayout();
        show.setLayout(card);


        show.add(new homePage());
        show.add("提案查询",new ProInquiry());
        show.add("提案编制",new ProposalWriteUI(id));
//        show.add("规范查询",new Button("规范查询"));
//        show.add(new Button("规范编制"));
        show.add("信息维护",new InformationModificationUI(id));

      //  card.show();



        提案查询.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(show,"提案查询");
            }
        });
        提案编制Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(show,"提案编制");
            }
        });
        信息维护Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(show,"信息维护");

            }
        });
        this.pack();
    }

}
