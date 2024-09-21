import java.awt.*;

public class GameUtils {
    //head
    public static Image upImg = Toolkit.getDefaultToolkit().getImage("img/up.png");
    public static Image downImg = Toolkit.getDefaultToolkit().getImage("img/down.png");
    public static Image leftImg = Toolkit.getDefaultToolkit().getImage("img/left.png");
    public static Image rightImg = Toolkit.getDefaultToolkit().getImage("img/right.png");

    //body
    public static Image bodyImg = Toolkit.getDefaultToolkit().getImage("img/body.png");
    //food
    public static Image foodImg = Toolkit.getDefaultToolkit().getImage("img/food.png");

    public static void drawWord(Graphics g, String str, Color color, int size, int x, int y){
        g.setColor(color);
        g.setFont(new Font("Arial",Font.BOLD, size));
        g.drawString(str, x, y);
    }

}
