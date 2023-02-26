import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Country Flag Quiz");
        new Main().setIcon(window);

        window.add(new MainPanel());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setIcon(JFrame window) {
        window.setIconImage(new ImageIcon("resources/gameIcon.png").getImage());
    }
}