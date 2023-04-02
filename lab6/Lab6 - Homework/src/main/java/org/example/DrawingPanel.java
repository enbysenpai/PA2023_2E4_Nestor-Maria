package org.example;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel implements Serializable {
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

    private List<LineSegment> linePut;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();

        //add mouse listener for detecting clicks on the lines
        addMouseListener(new MouseAdapter() {
            private List<Integer> selectedVertices = new ArrayList<>();

            @Override
            public void mousePressed(MouseEvent e) {
                int distanceThreshold = 10;
                graphics.setStroke(new BasicStroke(2));

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
                                        linePut.add(line);
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
                            colorIndex = colorIndex + 1;
                            Color color = colors[colorIndex % 2];
                            line.setColor(color);
                            colorSegment(line.getX1(), line.getY1(), line.getX2(), line.getY2(), color);
                            linePut.add(line);
                            repaint();
                        }
                        selectedVertices.clear();
                        return;
                    }
                }
            }

//            @Override
//            public void mouseEntered(MouseEvent e) {
//                System.out.println("Mouse Entered");
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                System.out.println("Mouse Exited");
//            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (checkTriangle())
                    createWinnerScreen();
                else
                    System.out.println("Not yet...");
            }

        });

    }

    private void createWinnerScreen() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //graphics.setColor(Color.YELLOW);
        //graphics.fillRect(0, 0, W, H);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        graphics.drawString("Game over", 400, 250);
        repaint();
    }

    private int distanceToPoint(int x, int y, int x1, int y1) {
        //using the formula to find the distance between two points

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
        colorIndex = 0;
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices; i++) {
            graphics.drawOval(x[i], y[i], 4, 4);
            graphics.fillOval(x[i], y[i], 4, 4);
        }
    }

    private void drawLines() {
        lineSegments = new ArrayList<>();
        linePut = new ArrayList<>();
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

    public boolean checkTriangle() {
        // iterate through all possible triangles
        for (int i = 0; i < linePut.size(); i++) {
            LineSegment line1 = linePut.get(i);
            for (int j = i + 1; j < linePut.size(); j++) {
                LineSegment line2 = linePut.get(j);
                for (int k = j + 1; k < linePut.size(); k++) {
                    LineSegment line3 = linePut.get(k);
                    // check if all three sides have the same color
                    if (line1.getColor() == line2.getColor() && line2.getColor() == line3.getColor()) {
                        // check if the color is either red or blue
                        if (line1.getColor() == Color.RED || line1.getColor() == Color.BLUE) {
                            if (checkCoordinates(line1, line2) && checkCoordinates(line2, line3) && checkCoordinates(line1, line3)) {
                                System.out.println("ioti triunghiu");
                                System.out.println(line1.getColor());
                                createWinnerScreen();
                                return true;

                            }
                        }
                    }
                }
            }
        }
        System.out.println("Not a triangle");
        return false;
    }

    private boolean checkCoordinates(LineSegment line1, LineSegment line2) {
        Point2D.Double line1Start = new Point2D.Double(line1.getX1(), line1.getY1());
        Point2D.Double line1End = new Point2D.Double(line1.getX2(), line1.getY2());
        Point2D.Double line2Start = new Point2D.Double(line2.getX1(), line2.getY1());
        Point2D.Double line2End = new Point2D.Double(line2.getX2(), line2.getY2());

        if (line1Start.equals(line2Start) || line1Start.equals(line2End) ||
                line1End.equals(line2Start) || line1End.equals(line2End)) {
            return true;
        }

        return false; //they don't intersect in one of their extremities
    }

    public void undoGame(ActionEvent e) {
        if (!linePut.isEmpty()) {
            LineSegment lastLinePut = linePut.remove(linePut.size() - 1);
            linePut.remove(lastLinePut);
            colorIndex = colorIndex - 1;
            System.out.println("Line size, color index: " + linePut.size() + " " + colorIndex);
            graphics.setColor(Color.BLACK);
            lastLinePut.setColor(Color.BLACK);
            graphics.drawLine(lastLinePut.getX1(), lastLinePut.getY1(), lastLinePut.getX2(), lastLinePut.getY2());
            repaint();
        } else {
            System.out.println("Nothing happening here");
        }
    }

}
