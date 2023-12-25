import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DDAAlgorithm extends JFrame {
    private ArrayList<Point> points;

    public DDAAlgorithm() {
        points = new ArrayList<>();
        setTitle("DDA Line Drawing App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLines(g);
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(e.getPoint());
                    repaint();      
            }
        });

        getContentPane().add(drawingPanel);
    }

    private void drawDDALine( Graphics g) {
        g.setColor(Color.RED);
        int x1 = 0;int y1 = 0 ;int x2 = 0;int y2 = 0;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.fillRect(Math.round(x), Math.round(y), 1, 1);
            x += xIncrement;
            y += yIncrement;
        }
    }

    private void drawLines(Graphics g) {
        // Optional: Draw existing lines from stored points
        g.setColor(Color.RED);

        if (points.size() > 1) {
            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DDAAlgorithm app = new DDAAlgorithm();
            app.setVisible(true);
        });
    }
}

