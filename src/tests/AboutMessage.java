package tests;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AboutMessage {
  public static void main(String[] args) {
    JTextArea textArea = new JTextArea("About");
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setEditable(false);
    textArea.setFocusable(false);
    JOptionPane.showMessageDialog(null, textArea, "About", JOptionPane.PLAIN_MESSAGE);
  }
}
