import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static void main(String[] args) {
        Window window = new Window();
    }

    public final static int WINDOW_WIDTH = 1024;//1024
    public final static int WINDOW_HEIGHT = 594;//594
    public final int START_POSITION = 0;

    public Window() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Changer");
        this.setBackground(Color.black);
        Scene scene = new Scene(START_POSITION, START_POSITION, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(scene);
        this.setVisible(true);
    }
}
