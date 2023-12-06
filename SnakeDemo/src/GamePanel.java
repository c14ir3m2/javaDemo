import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    //set snake
    int length;
    int[] snakeX = new int[600];//x coordinate 25*25
    int[] snakeY = new int[500];//y coordinate 25*25
    String dir; //Initial direction

    //food position
    int foodx;
    int foody;
    Random random = new Random();

    int score; //score

    //game status: start, stop
    boolean isStart = false; //default is not start

    boolean isFail = false;

    //timer
    Timer timer = new Timer(100,this);//executed every 100ms

    //Constructor
    public GamePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init(){
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100; //head
        snakeX[1] = 75; snakeY[1] = 100; //first body
        snakeX[2] = 50; snakeY[2] = 100; //second body
        dir = "R"; //Initial direction to right

        foodx = 25 + 25*random.nextInt(30);
        foody = 75 + 25*random.nextInt(20);

        score = 0;
    }

    //paint panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.getHSBColor(0,0,30));

        //paint score
        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString("Length" + length,750,35);
        g.drawString("Score" + score,750,60);

        //paint food
        Data.food.paintIcon(this,g,foodx,foody);

        //paint snake
        if(dir.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(dir.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(dir.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(dir.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }


        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //game status
        if(isStart == false){
            g.setColor(Color.black);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g.drawString("Press Space Key to Start",200,200);
        }

        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g.drawString("Game Over! Press Space Key to Restart",150,200);
        }

    }




    //KeyListener Event
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){
                //restart
                isFail = false;
                init();
            }else{
                isStart = !isStart;
            }
            repaint();
        }
        //snake move
        if(keyCode==KeyEvent.VK_UP){
            dir = "U";
        }else if(keyCode==KeyEvent.VK_DOWN){
            dir = "D";
        }else if(keyCode==KeyEvent.VK_LEFT){
            dir = "L";
        }else if(keyCode==KeyEvent.VK_RIGHT){
            dir = "R";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && isFail == false){//if the game is start
            //eat food
            if(snakeX[0] == foodx && snakeY[0] == foody){
                length++;
                //add score
                score += 10;
                //random food appear again
                foodx = 25 + 25*random.nextInt(30);
                foody = 75 + 25*random.nextInt(20);
            }


            //move right
            for (int i = length-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1]; //move forward one unit
                snakeY[i] = snakeY[i-1];
            }
            //direction
            if(dir.equals("R")){
                snakeX[0] = snakeX[0]+25;
                if(snakeX[0]>850){snakeX[0] = 25;}
            }else if(dir.equals("L")){
                snakeX[0] = snakeX[0]-25;
                if(snakeX[0]<25){snakeX[0] = 850;}
            }else if(dir.equals("U")){
                snakeY[0] = snakeY[0]-25;
                if(snakeY[0]<75){snakeY[0] = 650;}
            }else if(dir.equals("D")){
                snakeY[0] = snakeY[0]+25;
                if(snakeY[0]>650){snakeY[0] = 75;}
            }

            //Game Over: snake touches itself
            for(int i=1; i <length; i++){
                if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                    isFail = true;
                }
            }

            repaint();
        }
        timer.start();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }



}
