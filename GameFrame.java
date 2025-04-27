package JigsawsPuzzle;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel puzzlePanel; //panel chứa các mảnh ghép
    private JLabel infoLabel; //hiển thị tgian
    private JLabel originalImageLabel; //hinh mẫu
    private int secondsElapsed; //biến đếm tgian
    private javax.swing.Timer gameTimer; //

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

    public GameFrame(String imagePath, int rows, int cols) {
        //giao dien
        setTitle("Jigsaw Puzzle Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //icon
        setIconImage(new ImageIcon("E:\\Java\\JigsawsPuzzle\\Images\\Icon.png").getImage());

        //hinh mau
        originalImageLabel = new JLabel();
        originalImageLabel.setSize (200, 200);
        add(originalImageLabel, BorderLayout.WEST);

        //top panel
        JPanel topPanel = new JPanel();
        infoLabel = new JLabel("Time: 00:00");
        JButton resetButton = new JButton("Restart");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        topPanel.add(infoLabel);
        topPanel.add(resetButton);
        topPanel.setSize(screenWidth, 150);
        add(topPanel, BorderLayout.NORTH);

        //puzzle panel
        puzzlePanel = new GamePanel(this);
        puzzlePanel.setBounds(0, 200, screenWidth, screenHeight-200);
        add(puzzlePanel);

        resetButton.addActionListener(e -> {
            puzzlePanel.resetPuzzle();
            resetTimer();
        });

        loadGame(imagePath, rows, cols);
    }

    private void resetTimer(){
        secondsElapsed = 0;
        if(gameTimer != null)
	        gameTimer.stop();
        gameTimer = new javax.swing.Timer(1000, e -> {
            secondsElapsed++;
	        int minutes = secondsElapsed/60;
	        int seconds = secondsElapsed%60;
            infoLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
        });
        gameTimer.start();
    }

    private void updatePreview(String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        originalImageLabel.setIcon(new ImageIcon(scaled));
        setVisible(true);
    }
    

    //loadgame
    public void loadGame(String imagePath, int rows, int cols){
	    puzzlePanel.loadImage(imagePath, rows, cols);
        updatePreview(imagePath);
        resetTimer();
    }

    //win
    public void win(){
        if(gameTimer != null) gameTimer.stop();
        ImageIcon winIcon = new ImageIcon("E:\\Java\\JigsawsPuzzle\\Images\\firework.png");
    JOptionPane.showMessageDialog(
        this,
        "Congratulations!","You win!",
        JOptionPane.INFORMATION_MESSAGE,
        winIcon
    );
        this.dispose();
        SwingUtilities.invokeLater(GameOpening::new);
    }
}

