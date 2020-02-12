import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class JNotepadMenuBar {

    public static JMenuBar createJMenuBar(){
        JMenuBar jmb = new JMenuBar();

        jmb.add(createFileMenu());
        jmb.add(createEditMenu());
        jmb.add(createFormatMenu());
        jmb.add(createViewMenu());
        jmb.add(createHelpMenu());

        return jmb;
    }

    private static JMenu createFileMenu(){
        JMenu jm_File = new JMenu("File");
        jm_File.setMnemonic(KeyEvent.VK_F);

        // ***** New Menu Item *****
        JMenuItem jmi_New = new JMenuItem("New", KeyEvent.VK_N);
        jmi_New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        jmi_New.addActionListener((ae)->{
            // *** New Menu Item Action

        });

        // ***** Open Menu Item *****
        JMenuItem jmi_Open = new JMenuItem("Open...", KeyEvent.VK_O);
        jmi_Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        jmi_Open.addActionListener((ae)->{
            // *** Open Menu Item Action

        });

        // ***** Save Menu Item *****
        JMenuItem jmi_Save = new JMenuItem("Save", KeyEvent.VK_S);
        jmi_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        jmi_Save.addActionListener((ae)->{
            // *** Save Menu Item Action

        });

        // ***** Save As Menu Item *****
        JMenuItem jmi_SaveAs = new JMenuItem("Save As...", KeyEvent.VK_A);
        jmi_SaveAs.addActionListener((ae)->{
            // *** Save As Menu Item Action

        });

        // ***** Page Setup Menu Item *****
        JMenuItem jmi_PageSetup = new JMenuItem("Page Setup...", KeyEvent.VK_U);
        jmi_PageSetup.addActionListener((ae)->{
            // *** Page Setup Menu Item Action

        });

        // ***** Print Menu Item *****
        JMenuItem jmi_Print = new JMenuItem("Print...", KeyEvent.VK_S);
        jmi_Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        jmi_Print.addActionListener((ae)->{
            // *** Print Menu Item Action

        });

        // ***** Exit Menu Item *****
        JMenuItem jmi_Exit = new JMenuItem("Exit", KeyEvent.VK_X);
        jmi_Exit.addActionListener((ae)->{
            // *** Exit Menu Item Action

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

    private static JMenu createEditMenu(){
        JMenu jm_Edit = new JMenu("Edit");
        jm_Edit.setMnemonic(KeyEvent.VK_E);

        // ***** Undo Menu Item *****
        JMenuItem jmi_Undo = new JMenuItem("Undo", KeyEvent.VK_U);
        jmi_Undo.addActionListener((ae)->{
            // *** Undo Menu Item Action

        });


        // ***** Cut Menu Item *****
        JMenuItem jmi_Cut = new JMenuItem("Cut", KeyEvent.VK_T);
        jmi_Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        jmi_Cut.addActionListener((ae)->{
            // *** Cut Menu Item Action

        });

        // ***** Copy Menu Item *****
        JMenuItem jmi_Copy = new JMenuItem("Copy", KeyEvent.VK_C);
        jmi_Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        jmi_Copy.addActionListener((ae)->{
            // *** Copy Menu Item Action

        });

        // ***** Paste Menu Item *****
        JMenuItem jmi_Paste = new JMenuItem("Paste", KeyEvent.VK_P);
        jmi_Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        jmi_Paste.addActionListener((ae)->{
            // *** Paste Menu Item Action

        });

        // ***** Delete Menu Item *****
        JMenuItem jmi_Delete = new JMenuItem("Delete", KeyEvent.VK_L);
        jmi_Delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        jmi_Delete.addActionListener((ae)->{
            // *** Delete Menu Item Action

        });

        // ***** Find Menu Item *****
        JMenuItem jmi_Find = new JMenuItem("Find...", KeyEvent.VK_F);
        jmi_Find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        jmi_Find.addActionListener((ae)->{
            // *** Find Menu Item Action

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

        // ***** Go To Menu Item *****
        JMenuItem jmi_GoTo = new JMenuItem("Go To...", KeyEvent.VK_G);
        jmi_GoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        jmi_GoTo.addActionListener((ae)->{
            // *** Go To Menu Item Action

        });

        // ***** Select All Menu Item *****
        JMenuItem jmi_SelectAll = new JMenuItem("Select All", KeyEvent.VK_A);
        jmi_SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        jmi_SelectAll.addActionListener((ae)->{
            // *** Select All Menu Item Action

        });

        // ***** Time/Date Menu Item *****
        JMenuItem jmi_TimeDate = new JMenuItem("Time/Date", KeyEvent.VK_D);
        jmi_TimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        jmi_TimeDate.addActionListener((ae)->{
            // *** Time/Date Menu Item Action

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

    private static JMenu createFormatMenu(){
        JMenu jm_Format = new JMenu("Format");
        jm_Format.setMnemonic(KeyEvent.VK_O);

        // ***** Word Wrap Menu Item *****
        JMenuItem jmi_WordWrap = new JMenuItem("Word Wrap", KeyEvent.VK_W);
        jmi_WordWrap.addActionListener((ae)->{
            // *** Word Wrap Menu Item Action

        });

        // ***** Font Menu Item *****
        JMenuItem jmi_Font = new JMenuItem("Font...", KeyEvent.VK_F);
        jmi_Font.addActionListener((ae)->{
            // *** Font Menu Item Action

        });

        jm_Format.add(jmi_WordWrap);
        jm_Format.add(jmi_Font);

        return jm_Format;
    }

    private static JMenu createViewMenu(){
        JMenu jm_View = new JMenu("View");
        jm_View.setMnemonic(KeyEvent.VK_V);

        // ***** View Menu Item *****
        JMenuItem jmi_View = new JMenuItem("View", KeyEvent.VK_V);
        jmi_View.addActionListener((ae)->{
            // *** View Menu Item Action

        });

        jm_View.add(jmi_View);

        return jm_View;
    }

    private static JMenu createHelpMenu(){
        JMenu jm_Help = new JMenu("Help");
        jm_Help.setMnemonic(KeyEvent.VK_H);

        // ***** View Help Menu Item *****
        JMenuItem jmi_ViewHelp = new JMenuItem("View Help", KeyEvent.VK_H);
        jmi_ViewHelp.addActionListener((ae)->{
            // *** View Help Menu Item Action

        });

        // ***** About Menu Item *****
        JMenuItem jmi_About = new JMenuItem("About JNotepad", KeyEvent.VK_A);
        jmi_About.addActionListener((ae)->{
            // *** About Menu Item Action

        });

        jm_Help.add(jmi_ViewHelp);
        jm_Help.addSeparator();
        jm_Help.add(jmi_About);

        return jm_Help;
    }
}
