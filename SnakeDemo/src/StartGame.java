import javax.swing.*;

//Gluttonous Snake Game
//Main Class
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(20,2,900,680);
        frame.setResizable(false);//Window Size Fix
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}