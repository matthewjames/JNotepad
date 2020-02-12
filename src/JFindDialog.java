import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class JFindDialog {

    public static void showFindDialog(JFrame parent, JTextArea text){
        int width = 400,
            height = 150;

        // Create JDialog Component
        JDialog dialog = new JDialog(parent, "Find", true);
        dialog.setSize(new Dimension(width, height));
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());


        // pnl_top: Contains JLabel and JTextField for search input
        JPanel pnl_top = new JPanel();

        JLabel lbl_find = new JLabel("Find what:");
        JTextField txtf_searchField = new JTextField(20);

        pnl_top.add(lbl_find);
        pnl_top.add(txtf_searchField);

        // pnl_bottom: Contains filter and dispay options
        JPanel pnl_bottom = new JPanel();

        JCheckBox cb_matchCase = new JCheckBox("Match Case");

        JRadioButton rb_up = new JRadioButton("Up", true);
        JRadioButton rb_down = new JRadioButton("Down");
        ButtonGroup group = new ButtonGroup();
        group.add(rb_up);
        group.add(rb_down);

        JPanel pnl_radioButtons = new JPanel();
        pnl_radioButtons.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Direction"));
        pnl_radioButtons.add(rb_up);
        pnl_radioButtons.add(rb_down);

        pnl_bottom.add(cb_matchCase);
        pnl_bottom.add(pnl_radioButtons);

        // pnl_right: Contains JButtons to submit search
        JPanel pnl_right = new JPanel();
        pnl_right.setLayout(new BoxLayout(pnl_right, BoxLayout.Y_AXIS));
        pnl_right.setSize(new Dimension(width/4, height));

        // Search buttons
        JButton btn_findNext = new JButton("Find Next");
        btn_findNext.addActionListener((ae)->{
            // btn_findNext Action
            String keyword = txtf_searchField.getText();
            String textToScan;
            int position = text.getCaretPosition(),
                keywordIndex = 0;

            // If direction selected = up:
            if(rb_up.isSelected()){
                textToScan = text.getText().substring(0, position); // Scan substring of text from index 0 to position for input
            } else { // else (down is selected):
                textToScan = text.getText().substring(position);// Scan substring of text from position to end for input
            }

            System.out.println("Keyword: " + keyword);
            System.out.println("Text to scan: " + textToScan);

            keywordIndex = textToScan.indexOf(keyword);
            System.out.println("Index of keyword: " + keywordIndex);
            System.out.println("Index of caret: " + position);

            // If found, select word at index
            if(keywordIndex >= 0){
                if(rb_up.isSelected()) {
                    System.out.println("Selecting text from index %d to %d");
                    text.select(keywordIndex, keywordIndex + keyword.length());
                } else {
                    text.select(position + keywordIndex, keywordIndex + keyword.length());
                }
            } else { // else, display message alerting not found
                JOptionPane.showMessageDialog(parent, String.format("\"%s\" not found!", keyword), "Not Found", JOptionPane.ERROR_MESSAGE );
            }
        });

        JButton btn_cancel = new JButton("Cancel");
        btn_cancel.addActionListener((ae)->{
            // btn_cancel Action
            dialog.setVisible(false);
        });

        pnl_right.add(btn_findNext);
        pnl_right.add(btn_cancel);

        // pnl_left: Contains pnl_top and pnl_bottom
        JPanel pnl_left = new JPanel();
        pnl_left.setLayout(new BorderLayout());
        pnl_left.add(pnl_top, BorderLayout.NORTH);
        pnl_left.add(pnl_bottom, BorderLayout.SOUTH);


        // Add panels to dialog
        dialog.add(pnl_left, BorderLayout.WEST);
        dialog.add(pnl_right, BorderLayout.EAST);
        dialog.pack();
        dialog.setVisible(true);
    }


}
