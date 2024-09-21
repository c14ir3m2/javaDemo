import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HeadObj extends GameObj{
    //direction
    private String direction = "right";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
        //keyboard listener
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }

        });
    }

    //control direction
    // w -up  a -left  d-right s-down
    public void changeDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                if(!"right".equals(direction)){
                    direction = "left";
                    img = GameUtils.leftImg;
                }
                break;
            case KeyEvent.VK_D:
                if(!"left".equals(direction)){
                    direction = "right";
                    img = GameUtils.rightImg;
                }
                break;
            case KeyEvent.VK_W:
                if(!"down".equals(direction)){
                    direction = "up";
                    img = GameUtils.upImg;
                }
                break;
            case KeyEvent.VK_S:
                if(!"up".equals(direction)){
                    direction = "down";
                    img = GameUtils.downImg;
                }
                break;
            default:
                break;
        }
    }

    //snake move
    public void move(){
        //body move
        java.util.List<BodyObj> bodyObjList = this.frame.bodyObjList;
        for (int i = bodyObjList.size() - 1; i >= 1; i--) {
            bodyObjList.get(i).x = bodyObjList.get(i-1).x;
            bodyObjList.get(i).y = bodyObjList.get(i-1).y;
        }
        bodyObjList.get(0).x = this.x;
        bodyObjList.get(0).y = this.y;

        //head move
        switch (direction){
            case "up":
                y -= height;
                break;
            case "down":
                y += height;
                break;
            case "left":
                x -= width;
                break;
            case "right":
                x += width;
            default:
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //snake eat food
        FoodObj food = this.frame.foodObj;
        //last part of body
        Integer newX = null;
        Integer newY = null;

        //eat food
        if(this.x == food.x && this.y == food.y){
            this.frame.foodObj = food.getFood();
            //get last object of body
            BodyObj lastBody = this.frame.bodyObjList.get(this.frame.bodyObjList.size() - 1);
            newX = lastBody.x;
            newY = lastBody.y;
            //score + 1
            GameWin.score++;
        }

        move();
        if(newX != null && newY != null){
            this.frame.bodyObjList.add(new BodyObj(GameUtils.bodyImg, newX, newY, this.frame));
        }

        if(x < 0){
            x = 570;
        }else if (x > 570){
            x = 0;
        }else if (y < 30){
            y = 570;
        }else if (y>570){
            y = 30;
        }
    }
}
