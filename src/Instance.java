import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Instance {
    public boolean isSaved;
    private JTextArea textArea;
    private int characterCount;

    public Instance(){
        textArea = new JTextArea();
        isSaved = false;
    }

    public JTextArea getTextArea(){
        return textArea;
    }


    private String parseFile(File f) {
        StringBuilder stringBuilder = new StringBuilder();

        try(Scanner reader = new Scanner(f)){
            while(reader.hasNextLine()){
                stringBuilder.append(reader.nextLine());

                if(reader.hasNextLine()) {
                    stringBuilder.append("\n");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

}
