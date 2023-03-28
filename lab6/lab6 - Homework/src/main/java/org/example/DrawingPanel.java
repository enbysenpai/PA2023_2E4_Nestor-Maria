package org.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel {
    public final MainFrame frame;
    final static int W = 800, H = 500;
    public int numVertices;
    public double edgeProbability;
    public int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;

        createOffscreenImage();
        initPanel();
        createBoard();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    final void createBoard() {
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        edgeProbability = Double.parseDouble((String) frame.configPanel.linesCombo.getSelectedItem());

        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
    }

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; // the angle
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void drawVertices()
    {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices; i++)
        {
            graphics.drawOval(x[i],y[i],4,4);
            graphics.fillOval(x[i],y[i],4,4);
        }
    }
    private void drawLines()
    {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices-1; i++)
        {
            for(int j=0;j<numVertices;j++)
            {
                graphics.drawLine(x[i],y[i],x[j],y[j]);
            }
        }
    }

    public void loadImage(File file)
    {
        try
        {
            BufferedImage image= ImageIO.read(file);
//            setPreferredSize(new Dimension(W,H));
           // graphics.drawImage(image,0,0,null);

            double scale=Math.min((double)W/image.getWidth(),(double)H/image.getHeight());
            Image scaledImage=image.getScaledInstance((int)(image.getWidth()*scale),(int)(image.getHeight()*scale),Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage,W,H,null);
            repaint();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Graphics g) {
    } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics)
    {
        graphics.drawImage(image, 0, 0, this);
    }

}

