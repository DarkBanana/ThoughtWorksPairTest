import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by qiaorenjie on 2018/6/9.
 */
public class PanelUi extends JPanel {

    private static int BLACK = 1;       // alive
    private static int WHITE = 0;      // dead

    int[][] cell = new int[][]{{0,1,0,0}, {1,0,0,1}, {0,0,0,1},{0,1,0,1}};

    public static void main(String[] args) {
        JFrame f = new JFrame();
        PanelUi p = new PanelUi();
        f.setContentPane(p);
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setBackground(Color.WHITE);
        f.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);

        // 画横线
        for (int i = 0; i <= 8; i++) {
            g.drawLine(50, 50 * i + 50, 450, 50 * i + 50);
        }
        // 画竖线
        for (int i = 0; i <= 8; i++) {
            g.drawLine(50 * i + 50, 50, 50 * i + 50, 450);
        }
        for(int[] i:cell){
            for(int j:i){
                System.out.println(j);
            }
        }
        // 画棋子
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell.length; j++) {
                if (cell[i][j] == 1) { // 黑色 活着
                    g.setColor(Color.BLACK);
                    g.fillOval(55 + 50 * j, 55 + 50 * i, 40, 40);
                }
            }
        }
    }



}
