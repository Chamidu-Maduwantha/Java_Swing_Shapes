import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Shape extends JFrame {
    private JPanel shapePanel;
    private Shape currentShape;
    private Color shapeColor = Color.BLUE;
    private double shapeWidth = 100.0;
    private double shapeHeight = 100.0;

    public void ShapeApp() {
        setTitle("Shape App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createToolbar();
        createShapePanel();

        pack();
        setLocationRelativeTo(null);
    }

    private void createToolbar() {
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel shapeLabel = new JLabel("Select Shape:");
        JComboBox<String> shapeComboBox = new JComboBox<>(new String[]{"Rectangle", "Circle"});
        shapeComboBox.setSelectedIndex(0);

        JLabel widthLabel = new JLabel("Width:");
        JTextField widthField = new JTextField(String.valueOf(shapeWidth), 5);

        JLabel heightLabel = new JLabel("Height:");
        JTextField heightField = new JTextField(String.valueOf(shapeHeight), 5);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeComboBox.getSelectedItem();
                double width = Double.parseDouble(widthField.getText());
                double height = Double.parseDouble(heightField.getText());
                createShape(selectedShape, width, height);
                repaint();
            }
        });

        toolbar.add(shapeLabel);
        toolbar.add(shapeComboBox);
        toolbar.add(widthLabel);
        toolbar.add(widthField);
        toolbar.add(heightLabel);
        toolbar.add(heightField);
        toolbar.add(createButton);

        add(toolbar, BorderLayout.NORTH);
    }

    private void createShapePanel() {
        shapePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(shapeColor);
                if (currentShape != null) {
                    g2.fill(currentShape);
                }
            }
        };

        shapePanel.setPreferredSize(new Dimension(400, 400));

        add(shapePanel, BorderLayout.CENTER);
    }

    private void createShape(String shapeType, double width, double height) {
        if (shapeType.equals("Rectangle")) {
            currentShape = new Shape(0, 0, width, height);
        } else if (shapeType.equals("Circle")) {
            currentShape = new Ellipse2D.Double(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapeApp().setVisible(true);
            }
        });
    }

}
