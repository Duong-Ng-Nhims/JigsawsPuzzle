package JigsawsPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class PuzzlePieces extends JLabel{
    private final int originalRow;
    private final int originalCol;
    private Point initialClick;
    private int pieceWidth, pieceHeight;
    private int rows, cols;
    private Consumer<Boolean> onDrop;

    public PuzzlePieces(ImageIcon image, int originalRow, int originalCol, int pieceWidth, int pieceHeight) {
        super(image);
        this.originalRow = originalRow;
        this.originalCol = originalCol;
        this.pieceWidth = pieceWidth;
        this.pieceHeight = pieceHeight;
        setSize(image.getIconWidth(), image.getIconHeight());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponent().getParent().setComponentZOrder(getComponent(), 0);
                repaint();
            }
            
            public void mouseReleased(MouseEvent e) {
                int x = getX();
                int y = getY();
                int newCol = Math.round(x / (float) pieceWidth);
                int newRow = Math.round(y / (float) pieceHeight);

                int snapX = newCol * pieceWidth;
                int snapY = newRow * pieceHeight;

                setLocation(snapX, snapY);

                boolean isCorrect = (newRow == originalRow && newCol == originalCol);

                if (isCorrect) {
                    setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    if (getParent() instanceof GamePanel puzzlePanel) {
                        puzzlePanel.notifyCorrectPlacement();
                    }
                } else {
                    setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                }

                if (onDrop != null) {
                    onDrop.accept(isCorrect);
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = getX();
                int thisY = getY();
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });
    }

    private Component getComponent() {
        return this;
    }

    public void setDropTargetArea(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void setCallback(Consumer<Boolean> callback) {
        this.onDrop = callback;
    }

    public int getOriginalRow() {
        return originalRow;
    }

    public int getOriginalCol() {
        return originalCol;
    }

    public boolean isCorrectPosition() {
        int col = getX() / pieceWidth;
        int row = getY() / pieceHeight;
        return row == originalRow && col == originalCol;
    }

    public void randomizePosition() {
        setLocation(800 + (int)(Math.random() * 400), 200 + (int)(Math.random() * 300));
        setBorder(null);
    }
    
}
