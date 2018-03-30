import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

public class GWindow extends JFrame {

    // ----- Variables ==========================
    private GPanel gpanel;
    private Boolean p1Human;
    private Boolean p2Human;
    private Timer timer;

    // ----- Constructors =======================
    private GWindow() {
        p1Human = false;
        p2Human = false;
        gpanel = GPanel.newPanel(p1Human, p2Human);
        timer = null;
    }// ---

    // ----- Builder ============================
    public static GWindow newWindow() {
        GWindow.setDefaultLookAndFeelDecorated(true);
        GWindow rv = new GWindow();
        rv.setSize(Constants.getWDim());
        rv.setDefaultCloseOperation(GWindow.EXIT_ON_CLOSE);
        rv.setLocationRelativeTo(null);
        rv.initMB();
        rv.add(rv.gpanel);
        rv.setTitle("T3");
        rv.initiateAITimer();
        return rv;

    }// ---

    // ----- Overrides ==========================

    // ----- Methods ============================
    public void updateAI() {
        if((!p1Human || !p2Human) && gpanel.getGStatus() == Constants.GStatus.PLAY) {
            gpanel.updateAI();
            repaint();
        }
        if(gpanel.getGStatus() != Constants.GStatus.PLAY) {
            killTimer();
        }
    }// ---

    private void initiateAITimer() {
        // initialize timer if needed
        if(null == timer) {
            timer = new Timer(Constants.getAI_Timer(), (ActionEvent e) -> {
                this.updateAI();
            });
        }

        // start timer
        if(!timer.isRunning() && (!p1Human || !p2Human)) {
            timer.start();
        }
    }// ---

    private void killTimer() {
        // stop timer
        if(null != timer && timer.isRunning()) {
            timer.stop();
        }
    }// ---

    private void initMB() {
        JMenuBar mb = new JMenuBar();
        {
            JMenu fileMenu = new JMenu("File");
            {
                JMenuItem newMI = new JMenuItem("New");
                {
                    newMI.addActionListener((e) -> {
                        newGame();
                    });
                }
                fileMenu.add(newMI);

                JMenuItem quitMI = new JMenuItem("Quit");
                {
                    quitMI.addActionListener((e) -> {
                        System.exit(0);
                    });
                }
                fileMenu.add(quitMI);
            }
            mb.add(fileMenu);

            JMenu setMenu = new JMenu("Settings");
            {
                JMenu aiSubMenu = new JMenu("Toggle AI");
                {
                    JMenuItem p1mi = new JMenuItem("Player 1");
                    {
                        p1mi.addActionListener((e) -> {
                            toggleAI(Boolean.TRUE);
                            newGame();
                        });
                    }
                    aiSubMenu.add(p1mi);

                    JMenuItem p2mi = new JMenuItem("Player 2");
                    {
                        p2mi.addActionListener((e) -> {
                            toggleAI(Boolean.FALSE);
                            newGame();
                        });
                    }
                    aiSubMenu.add(p2mi);
                }
                setMenu.add(aiSubMenu);
            }
            mb.add(setMenu);
        }
        this.setJMenuBar(mb);
    }// ---

    private void newGame() {
        this.remove(gpanel);
        gpanel = GPanel.newPanel(p1Human, p2Human);
        this.add(gpanel);
        repaint();
        initiateAITimer();
    }// ---

    private void toggleAI(Boolean p1) {
        if(p1) {
            p1Human = !p1Human;
        } else {
            p2Human = !p2Human;
        }
        if(!p1Human || !p2Human) {
            initiateAITimer();
        } else {
            killTimer();
        }
    }// ---
}
