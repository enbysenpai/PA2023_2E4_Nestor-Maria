package org.example;

import javax.swing.*;

import static java.awt.BorderLayout.*;


public class MainFrame extends JFrame
{
    public ConfigPanel configPanel;
    public ControlPanel controlPanel;
    public DrawingPanel canvas;

    public MainFrame()
    {
        super("My Drawing Application");
        init();
    }

    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel=new ConfigPanel(this);
        canvas=new DrawingPanel(this);
        controlPanel=new ControlPanel(this);

        add(configPanel,NORTH);
        add(canvas,CENTER);
        add(controlPanel,SOUTH);

        pack();
    }
}
