import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
public class CLI extends JFrame {
    JTextArea ta = new JTextArea (20,6);
    Document document = ta.getDocument ();
    public static void main (String [] args) {
        CLI cli = new CLI ();
    }
    CLI () {
        ta.setText ("First Line\nSecond Line\nThird Line");
        Container c = getContentPane ();
        c.add (ta);
        document.addDocumentListener (new DocumentListener () {
            public void changedUpdate(DocumentEvent e) {
                doCLI (e);
            }
            public void removeUpdate(DocumentEvent e) {
                doCLI (e);
            }
            public void insertUpdate(DocumentEvent e) {
                doCLI (e);

            }
        });
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setBounds (10,10, 300, 300);
        setVisible (true);
    }
    void doCLI (DocumentEvent e) {
        Element elem = null;
        System.out.println ("doCLI getLength " + e.getLength() );
        //Returns the length of the change.
        System.out.println ("doCLI getOffset " + e.getOffset() );
        //Returns the offset within the document of the start of the change.
        System.out.println ("doCLI getType " + e.getType ());
        System.out.println(e.getDocument());
    }
}