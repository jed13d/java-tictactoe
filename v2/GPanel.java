import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class GPanel extends JPanel {

    // ----- Variables ==========================
    private GBoard board;

    // ----- Constructors =======================
    private GPanel(Boolean p1Human, Boolean p2Human) {
        newGame(p1Human, p2Human);
        initMouse();
    }// ---

    // ----- Builder ============================
    public static GPanel newPanel(Boolean p1Human, Boolean p2Human) {
        GPanel rv = new GPanel(p1Human, p2Human);
        rv.setSize(Constants.getPDim());

        return rv;
    }// ---

    // ----- Overrides ==========================
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Constants.getPWid(), Constants.getPHgt());
        board.paint(g);
    }// ---

    // ----- Methods ============================
    private void initMouse() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(board.activePisHuman() && board.getGStatus() == Constants.GStatus.PLAY) {
                    Integer mx = me.getX();
                    Integer my = me.getY();

                    if(board.attemptHumanMove(mx, my)) {
                        board.update();
                        getParent().repaint();
                    }
                }
            }
        });
    }// ---

    public final void newGame(Boolean p1Human, Boolean p2Human) {
        board = GBoard.newBoard(p1Human, p2Human);
    }// ---

    public void updateAI() {
        if(board.attemptComputerMove()) {
            board.update();
        }
    }// ---

    public Constants.GStatus getGStatus() {
        return board.getGStatus();
    }// ---

}
