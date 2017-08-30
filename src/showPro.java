import javax.swing.*;
import java.awt.*;

public class showPro extends JFrame{

    public JPanel bottom;
    public CardLayout card;

    showPro(String name){
        this.setPreferredSize(new Dimension(800,600));
        this.pack();
        this.setVisible(true);
        this.add(bottom);



        card = new CardLayout();
        bottom.setLayout(card);

        bottom.add(new proposalDetails(name,this));
        bottom.add(new disscuss(name,this));




    }
}
