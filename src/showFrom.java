import javax.swing.*;
import java.awt.*;

public class showFrom extends JFrame{
    private JPanel bottom;

    showFrom(String id,String name){
        bottom.add(BorderLayout.WEST,new showApply(id));
        bottom.add(BorderLayout.CENTER,new showRecommend(name));

        this.setPreferredSize(new Dimension(800,600));
        this.pack();
        this.setVisible(true);
        this.add(bottom);
    }

}
