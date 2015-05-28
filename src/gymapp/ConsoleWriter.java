package gymapp;

import javax.swing.JTextArea;

public class ConsoleWriter { 
    /* Common Singleton implementation */
    private static ConsoleWriter instance;
    
    public static ConsoleWriter getInstance() {
        if(instance == null) {
            instance = new ConsoleWriter();
        }
        return instance;
    }
    
    private JTextArea jTextArea;
    
    private ConsoleWriter() {
    }
    
    public void setJTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }
    
    /**
     * Appends a given string to the witten text
     * @param str string to write
     */
    public void write(String str) {
        if(jTextArea != null) {
            jTextArea.setText(jTextArea.getText() + str + "\n");
        }
    }
    
    /**
     * Clears text area
     */
    public void clear() {
        if(jTextArea != null) {
            jTextArea.setText("");
        }
    }
}
