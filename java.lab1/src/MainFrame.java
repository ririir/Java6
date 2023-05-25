
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    private Field field = new Field();

    Action pauseAction5;
    Action resumeAction5;

    public MainFrame() {
        super("Bouncing balls");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Balls");
        Action addBallAction = new AbstractAction("Add ball") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if ((!pauseMenuItem.isEnabled()) && !resumeMenuItem.isEnabled()) {
                    pauseMenuItem.setEnabled(true);
                }
            }
        };

        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);

        JMenu controlMenu = new JMenu("Menu");
        menuBar.add(controlMenu);

        Action pauseAction = new AbstractAction("Stop"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };

        pauseAction5 = new AbstractAction("Stop 5 balls"){
            public void actionPerformed(ActionEvent event) {
                field.setPause1(true);
                pauseAction5.setEnabled(false);
                resumeAction5.setEnabled(true);
            }
        };

        resumeAction5 = new AbstractAction("Restart 5 balls"){
            public void actionPerformed(ActionEvent event) {
                field.setPause1(false);
                resumeAction5.setEnabled(false);
                pauseAction5.setEnabled(true);
            }
        };

        resumeAction5.setEnabled(false);

        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        controlMenu.add(pauseAction5);
        controlMenu.add(resumeAction5);

        Action resumeAction = new AbstractAction("Restart") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };

        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
        getContentPane().add(field, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}