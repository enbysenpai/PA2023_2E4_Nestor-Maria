package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ConfigPanel extends JPanel {
    public final MainFrame frame;
    public JLabel dotsLabel, linesLabel;
    public JSpinner dotsSpinner;
    public JComboBox colors;
    public JButton createButton;
    public JButton saveState;
    public JButton loadState;
    public Color strokeColor;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        dotsLabel = new JLabel("Number of dots: ");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));

        linesLabel = new JLabel("?Lines probability?");
        String[] colorsOptions = {"RED", "BLUE"};
        colors = new JComboBox(colorsOptions);
        colors.setSelectedIndex(0);


        createButton = new JButton("CREATE");
        saveState = new JButton("SAVE STATE");
        loadState = new JButton("LOAD STATE");

        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(colors);
        add(createButton);
        add(saveState);
        add(loadState);

        createButton.addActionListener(this::createGame);
        saveState.addActionListener(this::saveStateGame);
        loadState.addActionListener(this::loadStateGame);
    }

    private void createGame(ActionEvent e) {
        frame.canvas.remove(frame.canvas);
        frame.canvas.createBoard();

        frame.revalidate();
        frame.repaint();
    }


    public void saveStateGame(ActionEvent e) {
        try {
            GameState gameState = new GameState(
                    (Integer) dotsSpinner.getValue(),
                    this.frame.canvas.getLines(),
                    this.frame.canvas.getColorIndex()
            );

            FileOutputStream fileOut = new FileOutputStream("D:/PA/Lab6 - Homework/gameState.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameState);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in gameState.ser");
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

//    public void loadStateGame(ActionEvent e)
//    {
//        JFileChooser fileChooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("SER File (.ser)", "ser");
//        fileChooser.setFileFilter(filter);
//
//        int result = fileChooser.showOpenDialog(frame);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            try {
//                FileInputStream fileIn = new FileInputStream("D:/PA/Lab6 - Homework/");
//                ObjectInputStream in = new ObjectInputStream(fileIn);
//                GameState gameState = (GameState) in.readObject();
//                in.close();
//                fileIn.close();
//
//                System.out.println("Deserialized data:");
//                System.out.println(gameState);
//            } catch (IOException | ClassNotFoundException ex) {
//                ex.getStackTrace();
//            }
//        }
//    }

    public void loadStateGame(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SER File (.ser)", "ser");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                // Deserialize the GameState object
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                GameState gameState = (GameState) in.readObject();
                in.close();
                fileIn.close();

                // Set the state of the drawing table using the deserialized GameState object
                this.frame.canvas.setNumVertices(gameState.getNumDots());
                this.frame.canvas.setLineSegments(gameState.getLines());
                this.frame.canvas.setIndexColor(gameState.getNextIndexColor());

                // Repaint the drawing table to show the restored state
                this.frame.canvas.createDeserialized();

                System.out.println("Deserialized data is loaded from gameState.ser");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

}
