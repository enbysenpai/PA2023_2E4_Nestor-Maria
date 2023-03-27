package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel
{
    public final MainFrame frame;
    public JButton exitBtn = new JButton("Exit");
    public JButton loadBtn = new JButton("Load");
    public JButton saveBtn = new JButton("Save");

    public ControlPanel(MainFrame frame)
    {
        this.frame = frame;
        init();
    }
    private void init()
    {
        setLayout(new GridLayout(1,4));

        //add all buttons
        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
//        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
    }
    private void exitGame(ActionEvent e)
    {
        frame.dispose();
    }

    private void saveGame(ActionEvent e)
    {
        RenderedImage renderedImage=frame.canvas.image;
        File file=new File("D:/PA/Lab6/research/poza.png");
        try {
            ImageIO.write(renderedImage, "png", file);
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    //    private void loadGame(ActionEvent e)
//    {
//        JFileChooser fileChooser=new JFileChooser();
//
//    }
}
