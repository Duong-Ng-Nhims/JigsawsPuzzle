package JigsawsPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static ImageIcon[] splitImage(ImageIcon srcImage, int rows, int cols) {
        int pieceWidth = srcImage.getIconWidth() / cols;
        int pieceHeight = srcImage.getIconHeight() / rows;

        ImageIcon[] pieces = new ImageIcon[rows * cols];
        BufferedImage image = new BufferedImage(srcImage.getIconWidth(), srcImage.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.drawImage(srcImage.getImage(), 0, 0, null);
        g.dispose();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                BufferedImage pieceImage = image.getSubimage(col * pieceWidth, row * pieceHeight, pieceWidth, pieceHeight);
                pieces[row * cols + col] = new ImageIcon(pieceImage);
            }
        }

        return pieces;
    }
}
