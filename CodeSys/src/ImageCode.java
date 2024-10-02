import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class ImageCode {

    static String[] strs = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "2", "3", "4", "5", "6", "7", "8", "9"
    };//l, 0, 1 is confusing

    public static void main(String[] args) throws Exception{
        //generate image(has number or alphabet code on it)
        //drawing picture
        /*
        1.have a paper
        2. get a pen
        3. prepare data, randomly choose from array list
        4. paint data on the paper
        5. generate a real image
         */

        //image size
        int w = 150;
        int h = 50;
        //image type  RGB red green blue
        //int imageType = BufferedImage.TYPE_INT_RGB;
        //int imageType = 1;

        //ctrl + p view the parameter
        BufferedImage image = new BufferedImage(w,h,1);
        //change image color
        //get pen
        Graphics g = image.getGraphics();
        g.setColor(Color.yellow);
        g.fillRect(0,0,w,h);

        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.PLAIN,25));
        Random random = new Random();

        int x = 30;
        int y = 30;
        //get data from arraylist
        for(int i = 0; i<4; i++){
            int num = random.nextInt(strs.length);
            String str = strs[num];
            //draw
            g.drawString(str,x,y);
            x += 25;
        }

        g.setColor(Color.BLUE);
        for(int i = 0; i<4; i++){
            int x1 = random.nextInt(30);
            int y1 = random.nextInt(50);
            int x2 = random.nextInt(30)+120;
            int y2 = random.nextInt(50);
            //disturb line
            g.drawLine(x1,y1,x2,y2);
        }


        //generate image to drive
        ImageIO.write(image, "jpg",new File("E:\\java\\aaa.jpg"));
    }
}
