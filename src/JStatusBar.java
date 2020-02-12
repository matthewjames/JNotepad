import javax.swing.*;
import java.awt.*;

public class JStatusBar {
    private static JPanel statusBar;
    private static JFrame parent;
    private static boolean enabled;

    public static JPanel create(JFrame p){
        parent = p;
        statusBar = new JPanel();
        statusBar.setPreferredSize(new Dimension(parent.getWidth(), 25));
        statusBar.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        enabled = false;

        statusBar.setBackground(new Color(240, 240, 240));

        return statusBar;
    }

    public JStatusBar(){

    }

    public JPanel getStatusBar(){
        return statusBar;
    }

    public void show(){
        statusBar.setVisible(true);
        enabled = true;
    }

    public void hide(){
        statusBar.setVisible(false);
        enabled = false;
    }

    public boolean isShowing(){
        return enabled;
    }

    public void setEnabled(boolean b){
        enabled = b;
    }
}
