package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    public final MainFrame frame;
    public static int W = 800, H = 500;
    public int numVertices;
    public double edgeProbability;
    public int[] x, y;
    BufferedImage image;
    Graphics2D graphics;
    private List<LineSegment> lineSegments;
    private Color[] colors = {Color.RED, Color.BLUE};
    private int colorIndex = 0;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();

        //add mouse listener for detecting clicks on the lines
        addMouseListener(new MouseAdapter()
        {
            private List<Integer> selectedVertices = new ArrayList<>();

            @Override
            public void mousePressed(MouseEvent e) {
                int distanceThreshold = 10;

                // check if a vertex was clicked
                for (int i = 0; i < numVertices; i++) {
                    int dist = distanceToPoint(e.getX(), e.getY(), x[i], y[i]);
                    if (dist < distanceThreshold) {
                        selectedVertices.add(i);
                        if (selectedVertices.size() == 2) {
                            int v1 = selectedVertices.get(0);
                            int v2 = selectedVertices.get(1);
                            for (LineSegment line : lineSegments) {
                                if ((line.getX1() == x[v1] && line.getY1() == y[v1] && line.getX2() == x[v2] && line.getY2() == y[v2])
                                        || (line.getX1() == x[v2] && line.getY1() == y[v2] && line.getX2() == x[v1] && line.getY2() == y[v1])) {
                                    if (line.getColor() == Color.BLACK) {
                                        colorIndex = (colorIndex + 1) % colors.length;
                                        Color color = colors[colorIndex];
                                        line.setColor(color);
                                        colorSegment(line.getX1(), line.getY1(), line.getX2(), line.getY2(), color);
                                        repaint();
                                    }
                                    break;
                                }
                            }
                            selectedVertices.clear();
                        }
                        return;
                    }
                }

                // check if a line was clicked
                for (LineSegment line : lineSegments) {
                    int dist = distanceToLine(e.getX(), e.getY(), line.getX1(), line.getY1(), line.getX2(), line.getY2());
                    if (dist < distanceThreshold) {
                        if (line.getColor() == Color.BLACK) {
                            colorIndex = (colorIndex + 1) % colors.length;
                            Color color = colors[colorIndex];
                            line.setColor(color);
                            colorSegment(line.getX1(), line.getY1(), line.getX2(), line.getY2(), color);
                            repaint();
                        }
                        selectedVertices.clear();
                        return;
                    }
                }
            }
        });
    }

    private int distanceToPoint(int x, int y, int x1, int y1) {
        double dx = x - x1;
        double dy = y - y1;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public void colorSegment(int x1, int y1, int x2, int y2, Color color) {
        graphics.setColor(color);
        graphics.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    private int distanceToLine(int x, int y, int x1, int y1, int x2, int y2) {
        double px = x2 - x1;
        double py = y2 - y1;
        double something = px * px + py * py;
        double u = ((x - x1) * px + (y - y1) * py) / something;
        if (u > 1) {
            u = 1;
        } else if (u < 0) {
            u = 0;
        }
        double x3 = x1 + u * px;
        double y3 = y1 + u * py;
        double dx = x3 - x;
        double dy = y3 - y;
        return (int) Math.sqrt(dx * dx + dy * dy);
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

    public void createBoard() {
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        //edgeProbability=Double.parseDouble((String)frame.configPanel.linesCombo.getSelectedItem());

        createOffscreenImage();
        createVertices();
        drawVertices();
        drawLines();

        repaint();
    }

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; //the angle
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void drawVertices() {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices; i++) {
            graphics.drawOval(x[i], y[i], 4, 4);
            graphics.fillOval(x[i], y[i], 4, 4);
        }
    }

    private void drawLines() {
        lineSegments=new ArrayList<>();
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                lineSegments.add(new LineSegment(x[i], y[i], x[j], y[j], Color.BLACK));
                graphics.drawLine(x[i], y[i], x[j], y[j]);
            }
        }

    }


    public int getWidth() {
        return W;
    }

    public int getHeight() {
        return H;
    }

    @Override
    public void update(Graphics g) {
        //no need for update
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
        repaint();
    }
}
