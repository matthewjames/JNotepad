//
// Name: James, Matt
// Project: Project #5
// Due: 12/8/2018
// Course: CS-2450-01-F18
//
// Description:
// A replication of the Windows Notepad text editor
//


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JNotepad extends JFrame {
    private JTextArea textArea;
    private JFileChooser jfc;
    private JPanel statusBar;
    private JCheckBoxMenuItem jmi_viewStatusBar;
    private DateFormat dateFormat;
    private Font font;
    private File file = null;
    private ImageIcon appIcon = new ImageIcon(getClass().getResource("JNotepad.png"));
    private String appName = "JNotepad",
                   fileName = "Untitled.txt",
                   path = System.getProperty("user.dir");
    private boolean statusBarShowing = false,
                    isSaved = false;

    private JNotepad(String s){
        fileName = s;
        new JNotepad();
    }

    private JNotepad(){
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle(appName + " - " + fileName);
        setIconImage(appIcon.getImage());
        setJMenuBar(createJMenuBar(this));
        setLayout(new BorderLayout());

        // Initialize global variables
        font = new Font("Courier New", Font.PLAIN, 12);
        jfc = new JFileChooser(path);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text or Java Source Files", "txt", "java");
        jfc.setFileFilter(filter);
        textArea = new JTextArea();
        dateFormat = new SimpleDateFormat("hh:mm a MM/dd/yyyy");

        // *** Text Area Properties***
        textArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        textArea.setBorder(null);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("Change Detected!");
                isSaved = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("Change Detected!");
                isSaved = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Change Detected!");
                isSaved = false;
            }
        });

        // *** Pop-up Edit Menu ***
        JPopupMenu popupMenu = createPopUpMenu();
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(textArea, e.getX(), e.getY());
            }
        });
        textArea.setComponentPopupMenu(popupMenu);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize status bar
        statusBar = JStatusBar.create(this);
        add(statusBar, BorderLayout.SOUTH);
        statusBar.setVisible(false);

        // WindowListener to catch close operation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if(!isSaved){
                    showSaveAlertDialog(null, true, false);
                }
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private JMenuBar createJMenuBar(JFrame p){
        JMenuBar jmb = new JMenuBar();

        jmb.add(createFileMenu(p));
        jmb.add(createEditMenu());
        jmb.add(createFormatMenu());
        jmb.add(createViewMenu());
        jmb.add(createHelpMenu(p));

        return jmb;
    }

    private JMenu createFileMenu(JFrame p){
        JMenu jm_File = new JMenu("File");
        jm_File.setMnemonic(KeyEvent.VK_F);

        // ***** New Menu Item *****
        JMenuItem jmi_New = new JMenuItem("New", KeyEvent.VK_N);
        jmi_New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        jmi_New.addActionListener((ae)->{
            // *** New Menu Item Action
            if(!isSaved){
                showSaveAlertDialog(p,false, true);
            }
            textArea.setText("");
            this.setTitle(appName + " - " + fileName);
        });

        // ***** Open Menu Item *****
        JMenuItem jmi_Open = new JMenuItem("Open...", KeyEvent.VK_O);
        jmi_Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        jmi_Open.addActionListener((ae)->{
            // *** Open Menu Item Action
            if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                file = jfc.getSelectedFile();
                textArea.setText(parseFile(file));
            }
        });

        // ***** Save Menu Item *****
        JMenuItem jmi_Save = new JMenuItem("Save", KeyEvent.VK_S);
        jmi_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        jmi_Save.addActionListener((ae)->{
            // *** Save Menu Item Action
            File file = new File(String.format("%s\\%s", System.getProperty("user.dir"), fileName));
            System.out.println("Current Selected File: "+ file.toString());
            System.out.println("File Exists: " + file.exists());

            // if the file does not exist, write a new file
            if(!file.exists()) {
                showSaveDialog(p, false);
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    textArea.write(writer);
                    writer.close();
                    isSaved = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.setTitle(String.format("%s - %s", appName, fileName));
            }
        });

        // ***** Save As Menu Item *****
        JMenuItem jmi_SaveAs = new JMenuItem("Save As...", KeyEvent.VK_A);
        jmi_SaveAs.addActionListener((ae)->{
            // *** Save As Menu Item Action
            showSaveDialog(p, false);
        });

        // ***** Page Setup Menu Item *****
        JMenuItem jmi_PageSetup = new JMenuItem("Page Setup...", KeyEvent.VK_U);
        jmi_PageSetup.setEnabled(false);
        jmi_PageSetup.addActionListener((ae)->{
            // *** Page Setup Menu Item Action

        });

        // ***** Print Menu Item *****
        JMenuItem jmi_Print = new JMenuItem("Print...", KeyEvent.VK_S);
        jmi_Print.setEnabled(false);
        jmi_Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        jmi_Print.addActionListener((ae)->{
            // *** Print Menu Item Action

        });

        // ***** Exit Menu Item *****
        JMenuItem jmi_Exit = new JMenuItem("Exit", KeyEvent.VK_X);
        jmi_Exit.addActionListener((ae)->{
            // *** Exit Menu Item Action
            if(!isSaved){
                showSaveAlertDialog(null, true, false);
            }
            System.exit(0);
        });

        jm_File.add(jmi_New);
        jm_File.add(jmi_Open);
        jm_File.add(jmi_Save);
        jm_File.add(jmi_SaveAs);
        jm_File.addSeparator();
        jm_File.add(jmi_PageSetup);
        jm_File.add(jmi_Print);
        jm_File.addSeparator();
        jm_File.add(jmi_Exit);

        return jm_File;
    }

    private JMenu createEditMenu(){
        JMenu jm_Edit = new JMenu("Edit");
        jm_Edit.setMnemonic(KeyEvent.VK_E);

        // ***** Undo Menu Item *****
        JMenuItem jmi_Undo = new JMenuItem("Undo", KeyEvent.VK_U);
        jmi_Undo.setEnabled(false);
        jmi_Undo.addActionListener((ae)->{
            // *** Undo Menu Item Action

        });


        // ***** Cut Menu Item *****
        JMenuItem jmi_Cut = new JMenuItem(new DefaultEditorKit.CutAction());
        jmi_Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        jmi_Cut.setText("Cut");
        jmi_Cut.setMnemonic(KeyEvent.VK_T);

        // ***** Copy Menu Item *****
        JMenuItem jmi_Copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        jmi_Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        jmi_Copy.setText("Copy");
        jmi_Copy.setMnemonic(KeyEvent.VK_C);

        // ***** Paste Menu Item *****
        JMenuItem jmi_Paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        jmi_Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        jmi_Paste.setText("Paste");
        jmi_Paste.setMnemonic(KeyEvent.VK_P);

        // ***** Delete Menu Item *****
        JMenuItem jmi_Delete = new JMenuItem("Delete", KeyEvent.VK_L);
        jmi_Delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        jmi_Delete.addActionListener((ae)->{
            // *** Delete Menu Item Action
            textArea.replaceSelection("");
        });

        // ***** Find Menu Item *****
        JMenuItem jmi_Find = new JMenuItem("Find...", KeyEvent.VK_F);
        jmi_Find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        jmi_Find.addActionListener((ae)->{
            // *** Find Menu Item Action
            JFindDialog.showFindDialog(this, textArea);
        });

        // ***** Find Next Menu Item *****
        JMenuItem jmi_FindNext = new JMenuItem("Find Next", KeyEvent.VK_N);
        jmi_FindNext.addActionListener((ae)->{
            // *** Find Next Menu Item Action

        });

        // ***** Replace Menu Item *****
        JMenuItem jmi_Replace = new JMenuItem("Replace...", KeyEvent.VK_R);
        jmi_Replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        jmi_Replace.addActionListener((ae)->{
            // *** Replace Menu Item Action

        });
        jmi_Replace.setEnabled(false);

        // ***** Go To Menu Item *****
        JMenuItem jmi_GoTo = new JMenuItem("Go To...", KeyEvent.VK_G);
        jmi_GoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        jmi_GoTo.addActionListener((ae)->{
            // *** Go To Menu Item Action

        });
        jmi_GoTo.setEnabled(false);

        // ***** Select All Menu Item *****
        JMenuItem jmi_SelectAll = new JMenuItem("Select All", KeyEvent.VK_A);
        jmi_SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        jmi_SelectAll.addActionListener((ae)->{
            // *** Select All Menu Item Action
            textArea.selectAll();
        });

        // ***** Time/Date Menu Item *****
        JMenuItem jmi_TimeDate = new JMenuItem("Time/Date", KeyEvent.VK_D);
        jmi_TimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        jmi_TimeDate.addActionListener((ae)-> {
            // *** Time/Date Menu Item Action
            Date date = new Date();
            textArea.append(dateFormat.format(date));
        });

        jm_Edit.add(jmi_Undo);
        jm_Edit.addSeparator();
        jm_Edit.add(jmi_Cut);
        jm_Edit.add(jmi_Copy);
        jm_Edit.add(jmi_Paste);
        jm_Edit.add(jmi_Delete);
        jm_Edit.addSeparator();
        jm_Edit.add(jmi_Find);
        jm_Edit.add(jmi_FindNext);
        jm_Edit.add(jmi_Replace);
        jm_Edit.add(jmi_GoTo);
        jm_Edit.addSeparator();
        jm_Edit.add(jmi_SelectAll);
        jm_Edit.add(jmi_TimeDate);

        return jm_Edit;
    }

    private JMenu createFormatMenu(){
        JMenu jm_Format = new JMenu("Format");
        jm_Format.setMnemonic(KeyEvent.VK_O);

        // ***** Word Wrap Menu Item *****
        JCheckBoxMenuItem jmi_WordWrap = new JCheckBoxMenuItem("Word Wrap");
        jmi_WordWrap.setMnemonic(KeyEvent.VK_W);
        jmi_WordWrap.addActionListener((ae)->{
            // *** Word Wrap Menu Item Action

            if(textArea.getLineWrap()){      // if line wrap is enabled
                textArea.setLineWrap(false);// disable it
                jmi_viewStatusBar.setEnabled(true);
                if(statusBarShowing){
                    statusBar.setVisible(true);
                }
            } else {                         // else
                textArea.setLineWrap(true);  // enable it
                jmi_viewStatusBar.setEnabled(false);
                statusBar.setVisible(false);
            }
        });

        // ***** Font Menu Item *****
        JMenuItem jmi_Font = new JMenuItem("Font...", KeyEvent.VK_F);
        jmi_Font.addActionListener((ae)->{
            // *** Font Menu Item Action
            font = JFontChooser.showDialog(this, font);
            textArea.setFont(font);
        });

        jm_Format.add(jmi_WordWrap);
        jm_Format.add(jmi_Font);

        return jm_Format;
    }

    private JMenu createViewMenu(){
        JMenu jm_View = new JMenu("View");
        jm_View.setMnemonic(KeyEvent.VK_V);

        // ***** View Menu Item *****
        jmi_viewStatusBar = new JCheckBoxMenuItem("Status Bar");
        jmi_viewStatusBar.setMnemonic(KeyEvent.VK_S);
        jmi_viewStatusBar.addActionListener((ae)->{
            // *** Status Bar Menu Item Action
            if(statusBarShowing){
                statusBar.setVisible(false);
                statusBarShowing = false;
            } else {
                statusBar.setVisible(true);
                statusBarShowing = true;
            }
        });

        jm_View.add(jmi_viewStatusBar);

        return jm_View;
    }

    private JMenu createHelpMenu(JFrame parent){
        JMenu jm_Help = new JMenu("Help");
        jm_Help.setMnemonic(KeyEvent.VK_H);

        // ***** View Help Menu Item *****
        JMenuItem jmi_ViewHelp = new JMenuItem("View Help", KeyEvent.VK_H);
        jmi_ViewHelp.addActionListener((ae)->{
            // *** View Help Menu Item Action

        });
        jmi_ViewHelp.setEnabled(false);

        // ***** About Menu Item *****
        JMenuItem jmi_About = new JMenuItem("About JNotepad", KeyEvent.VK_A);
        jmi_About.addActionListener((ae)->{
            // *** About Menu Item Action
            JOptionPane.showMessageDialog(parent, "JNotepad version 1.0\n(c) Matt James 2018", "About " + appName, JOptionPane.PLAIN_MESSAGE, appIcon);
        });

        jm_Help.add(jmi_ViewHelp);
        jm_Help.addSeparator();
        jm_Help.add(jmi_About);

        return jm_Help;
    }

    private JPopupMenu createPopUpMenu(){
        JPopupMenu popupMenu = new JPopupMenu();

        // *** Cut Menu Item ***
        JMenuItem jmi_Cut = new JMenuItem(new DefaultEditorKit.CutAction());
        jmi_Cut.setText("Cut");
        jmi_Cut.setMnemonic(KeyEvent.VK_T);

        // *** Paste Menu Item ***
        JMenuItem jmi_Paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        jmi_Paste.setText("Paste");
        jmi_Paste.setMnemonic(KeyEvent.VK_P);

        // *** Copy Menu Item ***
        JMenuItem jmi_Copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        jmi_Copy.setText("Copy");
        jmi_Copy.setMnemonic(KeyEvent.VK_C);

        popupMenu.add(jmi_Cut);
        popupMenu.add(jmi_Paste);
        popupMenu.add(jmi_Copy);

        return popupMenu;
    }

    // Method parseFile(File): Parses file and returns contents as a String
    private String parseFile(File f) {
        StringBuilder stringBuilder = new StringBuilder();

        try(Scanner reader = new Scanner(f)){
            while(reader.hasNextLine()){
                stringBuilder.append(reader.nextLine());

                if(reader.hasNextLine()) {
                    stringBuilder.append("\n");
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private void showSaveAlertDialog(JFrame p, boolean closingApplication, boolean newDocument){
        String message = String.format("Do you want to save changes to %s?", fileName);

        switch(JOptionPane.showConfirmDialog(this, message, appName, JOptionPane.YES_NO_CANCEL_OPTION)){
            case JOptionPane.YES_OPTION:
                showSaveDialog(p, newDocument);
            case JOptionPane.NO_OPTION:
                if(!closingApplication){
                    textArea.setText("");
                } else {
                    System.exit(0);
                }
            case JOptionPane.CANCEL_OPTION:
        }
    }

    private void showSaveDialog(JFrame p, boolean newDocument){
        if (jfc.showSaveDialog(p) == JFileChooser.APPROVE_OPTION) {
            System.out.println("This file has been selected: " + jfc.getSelectedFile().toString());
            fileName = jfc.getSelectedFile().getName();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(jfc.getSelectedFile()));
                textArea.write(writer);
                writer.close();
                isSaved = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!newDocument && p != null) {
                p.setTitle(String.format("%s - %s", appName, fileName));
            }
        }
    }

    public static void main(String[] args) {
        if(args.length > 0){
            SwingUtilities.invokeLater(()->{
                new JNotepad(args[0]);
            });
        } else {
            SwingUtilities.invokeLater(() -> {
                new JNotepad();
            });
        }
    }
}
