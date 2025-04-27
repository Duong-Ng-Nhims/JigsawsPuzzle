package JigsawsPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class GamePanel extends JPanel {
    private GameFrame gameframe;
    private int rows, cols;
    private int pieceWidth, pieceHeight;
    private ArrayList<PuzzlePieces> pieces = new ArrayList<>();

    public GamePanel(GameFrame gameframe) {
        this.gameframe = gameframe;
        setLayout(null); // dùng layout tự do để kéo thả
        setBackground(Color.white);
    }

    public void loadImage(String imagePath, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        pieces.clear();
        removeAll();
        repaint();
        revalidate();

        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image scaled = imageIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);

        ImageIcon[] splitPieces = ImageUtils.splitImage(imageIcon, rows, cols);
        pieceWidth = scaled.getWidth(null) / cols;
        pieceHeight = scaled.getHeight(null) / rows;

        for (int i = 0; i < splitPieces.length; i++) {
            int row = i / cols;
            int col = i % cols;
            PuzzlePieces piece = new PuzzlePieces(splitPieces[i], row, col, pieceWidth, pieceHeight);
            piece.setLocation(800 + (int)(Math.random() * 600), 100 + (int)(Math.random() * 400));
            piece.setDropTargetArea(rows, cols);
            pieces.add(piece);
            add(piece);
        }

        repaint();
    }

    // Hàm reset
    public void resetPuzzle() {
        for (PuzzlePieces piece : pieces) {
            piece.randomizePosition();
            piece.setEnabled(true);;
        }
        repaint();;
    }

    // Gọi khi 1 mảnh ghép đúng
    public void notifyCorrectPlacement() {
        boolean allCorrect = pieces.stream().allMatch(PuzzlePieces::isCorrectPosition);
        if (allCorrect) {
            gameframe.win();
    }
}
}



