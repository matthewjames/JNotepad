import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;

public class Sandbox extends JFrame implements CaretListener {
    private int characterCount;
    private JTextArea textArea;
    private JLabel lbl_bottom;

    public Sandbox(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 400));
        setLocationRelativeTo(null);
        setJMenuBar(createMenuBar());



        lbl_bottom = new JLabel();
        add(lbl_bottom, BorderLayout.SOUTH);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");
        JMenuItem test = new JMenuItem("Test");

        menu.add(test);
        menuBar.add(menu);

        return menuBar;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new Sandbox();
        });
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        characterCount = textArea.getText().length();
        lbl_bottom.setText("Character Count: " + characterCount);
    }
}
