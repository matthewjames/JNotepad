import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JTreeFileChooser extends JFrame {
    DefaultMutableTreeNode selectedNode;
    File selectedFile;
    boolean selectionMade;

    public JTreeFileChooser(){
        selectedNode = null;
        selectedFile = null;
        selectionMade = false;
    }

    public boolean showOpenDialog(JFrame parent, String rootPath){
        Path path = Paths.get(rootPath);

        // Create Dialog Component
        JDialog fileChooserDialog = new JDialog(parent, "File Chooser", true);
        fileChooserDialog.setSize(300, 450);

        // Create root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(path);
        createChildDirectory(path, root);


        // Create JTree and make scrollable
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree jTree = new JTree(treeModel);
        jTree.getSelectionModel().addTreeSelectionListener((e)->{
            // Tree Selection Action
            selectedNode = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        });

        JScrollPane scrollPane = new JScrollPane(jTree);
        scrollPane.setSize(250, 375);
        fileChooserDialog.add(scrollPane, BorderLayout.CENTER);

        // ***** Buttons Panel ******
        JPanel pnl_buttons = new JPanel(new FlowLayout());

        // ***** OK Button *****
        JButton btn_open = new JButton("Open");
        btn_open.addActionListener((ae)->{
            // OK Button Action
            selectionMade = true;
            FileNode fileNode = (FileNode) selectedNode.getUserObject();
            System.out.println("Selected Node Path: " + fileNode.toString());
            selectedFile = fileNode.getFile();
            fileChooserDialog.setVisible(false);
        });
        pnl_buttons.add(btn_open);

        // ***** Cancel Button *****
        JButton btn_cancel = new JButton("Cancel");
        btn_cancel.addActionListener((ae)->{
            // Cancel Button Action
            fileChooserDialog.setVisible(false);
        });
        pnl_buttons.add(btn_cancel);

        fileChooserDialog.add(pnl_buttons, BorderLayout.SOUTH);
        fileChooserDialog.pack();

        fileChooserDialog.setLocationRelativeTo(parent);
        fileChooserDialog.setVisible(true);

        return selectionMade;
    }

    public File getSelectedFile(){
//        selectedFile.setReadable(true);
//        selectedFile.setWritable(true);
//        selectedFile.setExecutable(true);
        return selectedFile;
    }

    private void createChildDirectory (Path root, DefaultMutableTreeNode rootNode){
        // Creates child directory using DirectoryStream object. Iterates through the stream
        // filtering only folders, java, and text files.
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(root)){
            for(Path file : directoryStream){
                if(file.toFile().isDirectory() || isCompatableFile(file.toFile().getName())) {
                    DefaultMutableTreeNode newNode =  new DefaultMutableTreeNode(new FileNode(file));
                    if(file.toFile().isDirectory()){
                        createChildDirectory(file, newNode);
                    }
                    rootNode.add(newNode);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Parse fileName for extension, check if extension matches .java
    private boolean isCompatableFile(String fileName){
        int lastIndex = fileName.lastIndexOf(".");
        boolean isCompatable = false;
        if(fileName.substring(lastIndex).equals(".java") || fileName.substring(lastIndex).equals(".txt")){
            isCompatable = true;
        };
        System.out.printf("isCompatableFile(%s):%b\n", fileName, isCompatable);
        return isCompatable;
    }


    // Custom FileNode class to contain File objects in JTree
    private class FileNode {
        File file;
        Path path;

        public FileNode(Path p){
            file = new File(p.toString());
            path = p;
        }

        public File getFile(){
            return file;
        }

        // Restricts Node label to file name
        @Override
        public String toString(){
            return path.getFileName().toString();
        }
    }
}
