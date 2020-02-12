import javax.swing.*;
import java.awt.*;

public class JFontChooser {
    static private String fontName;
    static private int fontStyle, fontSize;
    static private JTextField txt_FontPreview;
    static private Font font;

    public static Font showDialog(JFrame parent, Font f){
        fontName = f.getFontName();
        fontStyle = f.getStyle();
        fontSize = f.getSize();
        font = new Font(fontName, fontStyle, fontSize);
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String[] styles = {"Plain", "Bold", "Italics"};
        Integer[] sizes = {8, 10, 11, 12, 14, 16, 18, 20, 24, 28, 36, 48, 72};

        // Create dialog component
        JDialog font_dialog = new JDialog(parent, "Font Selector", true);
        font_dialog.setSize(400, 375);
        font_dialog.setResizable(false);
        font_dialog.setLocationRelativeTo(parent); // Centers dialog in relation to the parent component
        font_dialog.setLayout(new BorderLayout());

        // *******************************
        // North Panel: Contains list of all available system fonts, fontStyle panel, fontSize panel
        // *******************************

        JPanel panel_North = new JPanel(new FlowLayout());

        // Font Panel
        JPanel panel_Font = new JPanel(new BorderLayout());
        JLabel lbl_fonts = new JLabel("Font:");
        JList<String> fontNames = new JList<>(fonts);
        JScrollPane sp_font = new JScrollPane(fontNames);
        fontNames.addListSelectionListener((ae)->{
            // Font Name List Action
            setFontName(fontNames.getSelectedValue());
        });

        panel_Font.add(lbl_fonts, BorderLayout.NORTH);
        panel_Font.add(sp_font, BorderLayout.CENTER);
        panel_North.add(panel_Font);

        // Style panel
        JPanel panel_style = new JPanel(new BorderLayout());
        JLabel lbl_style = new JLabel("Style:");
        JList list_styles = new JList<>(styles);
        JScrollPane sp_style = new JScrollPane(list_styles);
        sp_style.setPreferredSize(new Dimension(50,150));
        list_styles.addListSelectionListener((ae)->{
            switch(list_styles.getSelectedValue().toString()){
                case "Plain":
                    setFontStyle(Font.PLAIN);
                    break;
                case "Bold":
                    setFontStyle(Font.BOLD);
                    break;
                case "Italics":
                    setFontStyle(Font.ITALIC);
            }
        });

        panel_style.add(lbl_style, BorderLayout.NORTH);
        panel_style.add(sp_style, BorderLayout.SOUTH);
        panel_North.add(panel_style);

        // Size Panel
        JPanel panel_size = new JPanel(new BorderLayout());
        JLabel lbl_size = new JLabel("Size:");
        JList list_sizes = new JList<>(sizes);
        JScrollPane sp_size = new JScrollPane(list_sizes);
        sp_size.setPreferredSize(new Dimension(50, 150));
        list_sizes.addListSelectionListener((ae)->{
            setFontSize((int)list_sizes.getSelectedValue());
        });

        panel_size.add(lbl_size, BorderLayout.NORTH);
        panel_size.add(sp_size, BorderLayout.SOUTH);
        panel_North.add(panel_size);

        // ******************************************
        // Center Panel: Contains Font Preview Panel
        // ******************************************

        JPanel panel_Center = new JPanel();

        // Preview Panel
        JPanel panel_FontPreview = new JPanel(new BorderLayout());
        JLabel lbl_FontPreview = new JLabel("Preview:");
        txt_FontPreview = new JTextField(fontName);
        txt_FontPreview.setEditable(false);
        txt_FontPreview.setHorizontalAlignment(JTextField.CENTER);
        txt_FontPreview.setPreferredSize(new Dimension(300, 75));
        txt_FontPreview.setFont(font);

        panel_FontPreview.add(lbl_FontPreview);
        panel_FontPreview.add(txt_FontPreview, BorderLayout.SOUTH);
        panel_Center.add(panel_FontPreview);

        // ***********************************
        // Panel South: Contains Buttons
        // ***********************************

        JPanel panel_South = new JPanel();

        // Buttons Panel
        JPanel panel_buttons = new JPanel(new FlowLayout());

        // OK Button
        JButton ok_btn = new JButton("OK");
        ok_btn.setPreferredSize(new Dimension(75, 30));
        ok_btn.addActionListener((ae)->{
            // OK Button Action
            font = new Font(fontName, fontStyle, fontSize);
            font_dialog.setVisible(false);
        });

        // Cancel Button
        JButton cancel_btn = new JButton("Cancel");
        cancel_btn.setPreferredSize(new Dimension(75, 30));
        cancel_btn.addActionListener((ae)->{
            // Cancel Button Action
            font = null;
            font_dialog.setVisible(false);
        });

        panel_buttons.add(ok_btn);
        panel_buttons.add(cancel_btn);
        panel_South.add(panel_buttons);

        font_dialog.add(panel_North, BorderLayout.NORTH);
        font_dialog.add(panel_Center, BorderLayout.CENTER);
        font_dialog.add(panel_South, BorderLayout.SOUTH);

        font_dialog.setVisible(true);

        return font;
    }

    static private void setFontName(String name){
        fontName = name;
        refreshPreviewText();
    }

    static private void setFontStyle(int style){
        fontStyle = style;
        refreshPreviewText();
    }

    static private void setFontSize(int size){
        fontSize = size;
        refreshPreviewText();
    }

    static private void refreshPreviewText(){
        txt_FontPreview.setFont(new Font(fontName, fontStyle, fontSize));
        txt_FontPreview.setText(fontName);
    }
}
