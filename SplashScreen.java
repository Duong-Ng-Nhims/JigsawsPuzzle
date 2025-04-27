package JigsawsPuzzle;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    
    public SplashScreen() {
        setUndecorated(true);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("E:\\Java\\JigsawsPuzzle\\Images\\logo.png"); 
        JLabel imageLabel = new JLabel(imageIcon);

        add(imageLabel);
        setVisible(true);
        Timer splashTimer = new Timer(3000, e -> {
            dispose();
            new Welcome();
        });
        splashTimer.setRepeats(false);
        splashTimer.start();

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new);
    }
}

