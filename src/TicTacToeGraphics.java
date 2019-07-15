// Anirudh Prakash

import java.awt.*;

public class TicTacToeGraphics {

    public static final int SIZE = 200;

    public static void main(String[] args) { // trying out graphics
        DrawingPanel panel = new DrawingPanel(SIZE * 3, SIZE * 3);
        Graphics g = panel.getGraphics();

        /*
         * g.drawRect(0, 0, SIZE, SIZE); g.drawRect(SIZE, 0, SIZE, SIZE);
         * g.drawRect(SIZE * 2, 0, SIZE, SIZE); g.drawRect(0, SIZE, SIZE, SIZE);
         * g.drawRect(SIZE, SIZE, SIZE, SIZE); g.drawRect(SIZE * 2, SIZE, SIZE, SIZE);
         * g.drawRect(0, SIZE * 2, SIZE, SIZE); g.drawRect(SIZE, SIZE * 2, SIZE, SIZE);
         * g.drawRect(SIZE * 2, SIZE * 2, SIZE, SIZE);
         */

        g.fillRect(SIZE - (SIZE / 40), 0, SIZE / 20, SIZE * 3);
        g.fillRect(2 * SIZE - (SIZE / 40), 0, SIZE / 20, SIZE * 3);
        g.fillRect(0, SIZE - (SIZE / 40), SIZE * 3, SIZE / 20);
        g.fillRect(0, 2 * SIZE - (SIZE / 40), SIZE * 3, SIZE / 20);

        g.setFont(new Font("Monospaced", Font.BOLD, SIZE));
        g.drawString("X", SIZE / 5, 4 * SIZE / 5); // 0 0
        g.drawString("X", SIZE / 5 + SIZE, 4 * SIZE / 5); // 0 1
        g.drawString("X", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5); // 0 2
        g.drawString("O", SIZE / 5, 4 * SIZE / 5 + SIZE); // 1 0
        g.drawString("O", SIZE / 5 + SIZE, 4 * SIZE / 5 + SIZE); // 1 1
        g.drawString("O", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5 + SIZE); // 1 2
        g.drawString("X", SIZE / 5, 4 * SIZE / 5 + 2 * SIZE); // 2 0
        g.drawString("X", SIZE / 5 + SIZE, 4 * SIZE / 5 + 2 * SIZE); // 2 1
        g.drawString("X", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5 + 2 * SIZE); // 2 2

    }

}
