package org.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        // Add a mouse listener to detect clicks on the lines or dots
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//
                // Check if the mouse click is within a certain distance of a line
                int lineDistanceThreshold = 10; // maximum distance of mouse click to line in pixels
                for (int i = 0; i < numVertices; i++) {
                    for (int j = i + 1; j < numVertices; j++) {
                        int dist = distanceToLine(e.getX(), e.getY(), x[i], y[i], x[j], y[j]);
                        if (dist < lineDistanceThreshold) {
                            // The mouse click is close to the line, so select it
                            graphics.setColor(Color.RED);
                            graphics.drawLine(x[i],y[i],x[j],y[j]);
                            repaint();
                            return;
                        }
                    }
                }
            }
        });

    }
    private int distanceToLine(int x, int y, int x1, int y1, int x2, int y2) {
        double px = x2-x1;
        double py = y2-y1;
        double something = px*px + py*py;
        double u =  ((x - x1) * px + (y - y1) * py) / something;
        if (u > 1) {
            u = 1;
        } else if (u < 0) {
            u = 0;
        }
        double x3 = x1 + u * px;
        double y3 = y1 + u * py;
        double dx = x3 - x;
        double dy = y3 - y;
        return (int) Math.sqrt(dx*dx + dy*dy);
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

    public int getWidth()
    {
        return W;
    }

    public int getHeight() {
        return H;
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

