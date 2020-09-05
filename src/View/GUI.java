package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{

    JTextArea console;

    public GUI () {
        super ("That's Life");
        setLayout (new GridLayout (3,3));
        init();

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setSize (900, 600);
        setVisible (true);
    }

    private void init () {
        JPanel panelEast;
        panelEast = new JPanel(new FlowLayout());

        console = new JTextArea();
        panelEast.add(console);

        // add (panelEast, );

    }
}
