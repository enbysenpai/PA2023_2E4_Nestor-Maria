package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel
{
    public final MainFrame frame;
    public JLabel dotsLabel,linesLabel;
    public JSpinner dotsSpinner;
    public JComboBox colors;
    public JButton createButton;
    public Color strokeColor;

    public ConfigPanel(MainFrame frame)
    {
        this.frame=frame;
        init();
    }

    private void init()
    {
        dotsLabel=new JLabel("Number of dots: ");
        dotsSpinner=new JSpinner(new SpinnerNumberModel(6,3,100,1));

        linesLabel=new JLabel("?Lines probability?");
        String[] colorsOptions={"RED","BLUE"};
        colors=new JComboBox(colorsOptions);
        colors.setSelectedIndex(0);



        createButton=new JButton("CREATE");

        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(colors);
        add(createButton);
        createButton.addActionListener(this::createGame);
    }

    private void createGame(ActionEvent e)
    {
        frame.canvas.remove(frame.canvas);
        frame.canvas.createBoard();

        frame.revalidate();
        frame.repaint();
    }
}
