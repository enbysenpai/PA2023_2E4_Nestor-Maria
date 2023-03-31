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
    public JButton exitBtn=new JButton("Exit");
    public JButton loadBtn=new JButton("Load");
    public JButton saveBtn=new JButton("Save");

    public ControlPanel(MainFrame frame)
    {
        this.frame=frame;
        init();
    }

    private void init()
    {
        setLayout(new GridLayout(1,4));

        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        exitBtn.addActionListener(this::exitGame);
        //loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);

        //doar asa a mers
        loadBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files (*.png, *.jpeg, *.gif, *.bmp)", "png", "jpeg", "gif", "bmp");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(file);
                    double scale=Math.min((double)800/image.getWidth(),(double)500/image.getHeight());
                    Image scaledImage=image.getScaledInstance((int)(image.getWidth()*scale),(int)(image.getHeight()*scale),Image.SCALE_SMOOTH);
                    frame.canvas.graphics.drawImage(scaledImage,0,0,this);
                } catch (IOException ex) {
                    System.err.println("Could not load image, IOException occurred: " + ex.getMessage());
                }
            }
        });
    }

    private void exitGame(ActionEvent e)
    {
        frame.dispose();
    }

    private void saveGame(ActionEvent e)
    {
        RenderedImage renderedImage=frame.canvas.image;
        File file=new File("D:/PA/Lab6/research/poza.png");
        try
        {
            ImageIO.write(renderedImage,"png",file);
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

}
