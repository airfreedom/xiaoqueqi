import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class managerFace extends JFrame{
   /* public static void main(String[] args) {
        new managerFace();
    }*/

    private JPanel bottom;
    private JButton 身份管理Button;
    private JButton 提案审批Button;
    private JButton 注销Button;
    private JPanel show;
    private JLabel post;
    private JButton recommend;
    private CardLayout card;

    managerFace(String id,String authority){
        this.setPreferredSize(new Dimension(800,600));
        this.add(bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        post.setText(getPost(authority));

        card = new CardLayout();
        show.setLayout(card);
        show.setPreferredSize(new Dimension(690,555));

        show.add(new homePage());
        show.add("身份管理",new idenManage());
        if(Integer.parseInt(authority)==2){
            show.add("提案审批",new zhuanweihui());
        }
        else if(Integer.parseInt(authority)==3){
            show.add("提案审批",new hangyefenhui());
        }
        else if(Integer.parseInt(authority)==4) {
            show.add("提案审批", new yanjiuhui());
        }
        show.add("填写推荐表",new recommendForm());
        //show.add("提案审批",new hangyefenhui());
        //show.add("提案审批",new yanjiuhui());


        System.out.print(show.getWidth());
        System.out.print(show.getHeight());

        //1
        身份管理Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                card.show(show,"身份管理" );
            }
        });
        提案审批Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                card.show(show,"提案审批" );
            }
        });

        recommend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                card.show(show,"填写推荐表");
            }
        });
        注销Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                toLoginFace();
            }
        });
    }

    //切换为登陆界面 1
    private void toLoginFace(){
        this.dispose();
        new LoginFace();
    }
    //从数据库中获取职位 1
    private String getPost(String authority){
        String name;
        if(Integer.parseInt(authority)==2){
            return name="专委会";
        }
        else if(Integer.parseInt(authority)==3){
            return name = "行业分会";
        }
        else{
            return name="研究会";
        }
    }


}
