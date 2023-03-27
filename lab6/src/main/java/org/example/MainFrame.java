package org.example;

import javax.swing.*;
import java.lang.module.Configuration;

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


        //create the components
        configPanel=new ConfigPanel(this);
        canvas=new DrawingPanel(this);
        controlPanel=new ControlPanel(this);


        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
        add(configPanel,NORTH);
        add(canvas,CENTER);//this is BorderLayout.CENTER
        add(controlPanel,SOUTH);

        //invoke the layout manager
        pack();
    }
}
