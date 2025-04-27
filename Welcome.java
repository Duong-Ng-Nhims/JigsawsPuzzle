package JigsawsPuzzle;

import javax.swing.*;
import org.w3c.dom.events.MouseEvent;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Welcome extends JFrame{
    public Welcome(){
        setUndecorated(true);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel(){
            Image background = new ImageIcon("E:\\Java\\JigsawsPuzzle\\Images\\back-ground.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null); 
        add(backgroundPanel);

        Font PoetsenOne = new Font("PoetsenOne", Font.BOLD, 50);

        JLabel titleLabel = new JLabel("Jigsaw Puzzle Game");
        titleLabel.setFont(PoetsenOne);
        titleLabel.setForeground(Color.PINK);
        titleLabel.setBounds(150, 200, 800, 100);
        backgroundPanel.add(titleLabel);

        JButton playButton = new JButton("Play Now");
        playButton.setBounds(300, 300, 200, 50);
        backgroundPanel.add(playButton);

        JButton quitButton = new JButton("Quit Game");
        quitButton.setBounds(300, 400, 200, 50);
        backgroundPanel.add(quitButton);

        playButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                playButton.setBackground(Color.CYAN); // Màu khi hover vào
            }
            public void mouseExited(MouseEvent e) {
                playButton.setBackground(UIManager.getColor("control")); // Màu ban đầu
            }
        });
        
        quitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                quitButton.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent e) {
                quitButton.setBackground(UIManager.getColor("control"));
            }
        });

        playButton.addActionListener(e -> {
            dispose(); 
            new GameOpening(); 
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        }); 

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Welcome::new);
    }

}
