package JigsawsPuzzle;

import javax.swing.*;
import java.awt.*;


public class GameOpening extends JFrame{

    private final String[] levelOptions ={"Level 1", "Level 2", "Level 3", "Level 4"};
    private final String[] imagePaths = {
        "E:\\Java\\JigsawsPuzzle\\Images\\h1.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h2.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h3.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h4.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h5.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h6.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h7.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h8.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h9.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h10.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h11.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h12.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h13.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h14.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h15.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h16.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h17.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h18.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h19.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h20.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h21.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h22.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h23.jpg",
        "E:\\Java\\JigsawsPuzzle\\Images\\h24.jpg",
    };

    public GameOpening(){
        setTitle("Jigsaw Puzzle Game");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setIconImage(new ImageIcon("E:\\Java\\JigsawsPuzzle\\Images\\Icon.png").getImage());

        JPanel imagePanel = new JPanel(new GridLayout(3, 8, 20, 20));
        imagePanel.setBackground(Color.white);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for(String imagePath : imagePaths) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaled = icon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            JButton imageButton = new JButton(new ImageIcon(scaled));
            imageButton.setBorder(BorderFactory.createEmptyBorder());
            imageButton.setContentAreaFilled(false);
            imageButton.addActionListener(e -> selectLevel(imagePath));
            imagePanel.add(imageButton);
        }
        add(imagePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void selectLevel(String selectedImagePath) {
        String selectedLevel = (String) JOptionPane.showInputDialog(this, "Select Level", "Level Selection", JOptionPane.QUESTION_MESSAGE, null, levelOptions, levelOptions[0]);
        if(selectedLevel != null){
            int rows = 3;
            int cols = 3;
            if(selectedLevel.equals("Level 2")){
                rows = 4;
                cols = 4;
            } else if(selectedLevel.equals("Level 3")){
                rows = 5;
                cols = 6;
            } else if(selectedLevel.equals("Level 4")){
                rows = 9;
                cols = 10;
            }
            dispose();
            GameFrame gameFrame = new GameFrame(selectedImagePath, rows, cols); 
            gameFrame.setVisible(true);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameOpening::new);
    }
}


