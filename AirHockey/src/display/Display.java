package display;

import javax.swing.*;
import java.awt.*;

public class Display {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    String name;
    private JFrame frame;
    private Canvas canvas;


    public Display(String name) {
        this.name = name;
        init();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    private void init() {
        this.frame = new JFrame(name);
        this.frame.setVisible(true);
        this.frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setFocusable(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);

        this.canvas = new Canvas();

        this.canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.canvas.setVisible(true);

        this.frame.add(canvas);
        this.frame.pack();
    }
}
