package org.example;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel
{
    public final MainFrame frame;
    public JLabel dotsLabel,linesLabel;
    public JSpinner dotsSpinner;
    public JComboBox linesCombo;
    public JButton createButton;

    public ConfigPanel(MainFrame frame)
    {
        this.frame=frame;
        init();
    }

    private void init()
    {
        //create the label and the spinner
        dotsLabel=new JLabel("Number of dots: ");
        dotsSpinner=new JSpinner(new SpinnerNumberModel(6,3,100,1));

        //create the combo box for lines
        linesLabel=new JLabel("Lines' probability: ");
        String[] lineOptions={"4","5","6","7","8","9","10"};
        linesCombo=new JComboBox(lineOptions);
        linesCombo.setSelectedIndex(2);

        //create the 'create' button
        createButton=new JButton("CREATE");

        //add the components to the panel
        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
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
