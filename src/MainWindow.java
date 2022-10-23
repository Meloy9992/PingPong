import javax.swing.*;

public class MainWindow {

    JFrame frame = new JFrame("Ping-Pong");

    public MainWindow() {
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(new GamePanel());
    }
}