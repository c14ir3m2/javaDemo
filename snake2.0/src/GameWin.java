import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameWin extends JFrame {
    //score
    public static int score = 0;

    //window size
    int winWidth = 800;
    int winHeight = 600;

    //head
    HeadObj headObj = new HeadObj(GameUtils.rightImg, 30, 570, this);

    //snake body
    public List<BodyObj> bodyObjList = new ArrayList<>();

    //food
    public FoodObj foodObj = new FoodObj().getFood();

    public void launch(){
        //visibility
        this.setVisible(true);
        // size
        this.setSize(winWidth,winHeight);
        // position
        this.setLocationRelativeTo(null);
        // title
        this.setTitle("Snake Game");

        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 30, 570, this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 0, 570, this));

        while (true){
            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //gray background
        g.setColor(Color.gray);
        g.fillRect(0,0,winWidth,winHeight);
        //grid
        g.setColor(Color.black);
        for(int i=0; i<=20; i++){
            g.drawLine(0,i * 30,600,i* 30);
            g.drawLine(i * 30,0,i * 30,600);
        }

        //paint body
        for (int i = bodyObjList.size() - 1; i >= 0 ; i--) {
            bodyObjList.get(i).paintSelf(g);

        }

        //paint head
        headObj.paintSelf(g);

        //paint food
        foodObj.paintSelf(g);

        //paint score
        GameUtils.drawWord(g, "Score: "+ score, Color.BLUE, 20, 650, 300 );

    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }

}
