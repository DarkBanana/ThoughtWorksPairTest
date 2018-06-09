import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
/**
 * 黑白棋面板
 * 
 * @author hyg
 * 
 */
public class HbPanel extends JPanel {
    private static int BLACK = 1;
    private static int WHITE = -1;
     
    int[][] qizi = new int[8][8];
    int curQizi = BLACK; // 当前走棋方
    ArrayList a = new ArrayList();
    ArrayList tmp = new ArrayList();
 
    // 在指定位置画棋子
    private void drawQizi(int i, int j) {
        Graphics g = this.getGraphics();
        if (qizi[i][j] == BLACK) {
            g.setColor(Color.BLACK);
        } else if (qizi[i][j] == WHITE) {
            g.setColor(Color.WHITE);
        }
        g.fillOval(55 + 50 * i, 55 + 50 * j, 40, 40);
    }
 
    /**
     * Create the panel
     */
    public HbPanel() {
        super();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + ", " + y);
                // 计算索引
                int i = (x - 50) / 50;
                int j = (y - 50) / 50;
                System.out.println(i + ", " + j);
                if (i < 0 || i >= 8 || j < 0 || j >= 8 || qizi[i][j] != 0) // 如果指定位置超出范围或已经有棋子，直接返回。
                    return;
                if (checkLocation(curQizi, i, j)) { // 判断当前位置是否可以放棋子
                    // 如果可以
                    // 1. 画出棋子
                    qizi[i][j] = curQizi;
                    drawQizi(i, j);
                    // 3. 判断对方是否有棋可走，如有
                    if (checkNext(-1 * curQizi)) {
                        // 2. 交换走棋方
                        curQizi *= -1;
                    } else if (checkNext(curQizi)) {
                        // 有无，判断自己是否有棋可走，如有，给出提示
                        JOptionPane.showConfirmDialog(null,
                                (curQizi == BLACK ? "白方" : "黑方") + "无棋可走，"
                                        + (curQizi == 1 ? "黑方" : "白方") + "继续",
                                "提示", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // 游戏结束
                        JOptionPane.showConfirmDialog(null, "双方都无棋可走，游戏结束",
                                "游戏结束", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        qizi[3][3] = WHITE; // 1为黑，-1为白，0为无棋子
        qizi[3][4] = BLACK;
        qizi[4][3] = BLACK;
        qizi[4][4] = WHITE;
        // qizi[1][0] = 1;
        //
        // Graphics g = getGraphics();
        setBackground(Color.CYAN);
    }
 
    /**
     * 验证参数代表的走棋方是否还有棋可走
     * @param i 代表走棋方，1为黑方，-1为白方
     * @return true/false
     */
    protected boolean checkNext(int i) {
         
        return true;
    }
 
     
    protected boolean checkLocation(int cur, int i, int j) {
        return toLeft(cur,i,j);
    }
     
    /**
     * 从当前位置开始向左验证，
     * 验证过程中，j不变，i减小。
     * @param cur 走棋方颜色
     * @param i 横坐标
     * @param j 纵坐标
     * @return
     */
    private boolean toLeft(int cur, int i, int j) {
        System.out.println("向左搜索"+(cur==1?"黑":"白")+"色棋子");
        boolean result = false;
        if(i < 2 || qizi[i-1][j] == cur) //如果放棋子的位置位于第2列以前，或左侧棋子同颜色，无需继续
            return false;
        int m;
        for(m=i-2; m>0; m--) {
            if(qizi[m][j] == cur) {
                result = true;
                break;
            }
        }
        if(result) 
            System.out.println("左侧搜索成功");
        else
            System.out.println("左侧搜索失败");
        return result;
    }
 
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 当前走棋方
        if (curQizi == 1) { // 黑色
            g.setColor(Color.BLACK);
        } else if (curQizi == -1) { // 白色
            g.setColor(Color.WHITE);
        }
        g.fillOval(5, 5, 40, 40);
        g.setColor(Color.BLACK);
 
        // 画横线
        for (int i = 0; i <= 8; i++) {
            g.drawLine(50, 50 * i + 50, 450, 50 * i + 50);
        }
        // 画竖线
        for (int i = 0; i <= 8; i++) {
            g.drawLine(50 * i + 50, 50, 50 * i + 50, 450);
        }
        // 画标志
        String[] xl = { "A", "B", "C", "D", "E", "F", "G", "H" };
        String[] yl = { "1", "2", "3", "4", "5", "6", "7", "8" };
        for (int i = 0; i < yl.length; i++) {
            g.drawString(xl[i], 50 * i + 72, 40);
            g.drawString(yl[i], 40, 50 * i + 80);
        }
        // 画棋子
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (qizi[i][j] != 0) { //
                    if (qizi[i][j] == 1) { // 黑色
                        g.setColor(Color.BLACK);
                    } else if (qizi[i][j] == -1) { // 白色
                        g.setColor(Color.WHITE);
                    }
                    g.fillOval(55 + 50 * i, 55 + 50 * j, 40, 40);
                }
            }
        }
    }
 
    public static void main(String[] args) {
        JFrame f = new JFrame();
        HbPanel p = new HbPanel();
        f.setContentPane(p);
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}