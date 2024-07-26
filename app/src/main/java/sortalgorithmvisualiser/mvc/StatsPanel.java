package sortalgorithmvisualiser.mvc;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel {
    private Model model;

    private JLabel barCountLabel;
    private JLabel delayLabel;
    private JLabel accessesLabel;
    private JLabel comparisonsLabel;
    private JLabel assignmentsLabel;

    public StatsPanel(Model m) {
        model = m;

        setLayout(new GridLayout(0, 2));

        add(new JLabel("Bar count:", JLabel.LEFT));
        barCountLabel = new JLabel("", JLabel.RIGHT);
        add(barCountLabel);

        add(new JLabel("Delay:", JLabel.LEFT));
        delayLabel = new JLabel("", JLabel.RIGHT);
        add(delayLabel);

        add(new JLabel("Array accesses:", JLabel.LEFT));
        accessesLabel = new JLabel("", JLabel.RIGHT);
        add(accessesLabel);

        add(new JLabel("Comparisons:", JLabel.LEFT));
        comparisonsLabel = new JLabel("", JLabel.RIGHT);
        add(comparisonsLabel);

        add(new JLabel("Assignments:", JLabel.LEFT));
        assignmentsLabel = new JLabel("", JLabel.RIGHT);
        add(assignmentsLabel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int barCount = model.getArrayLength();
        barCountLabel.setText(Integer.toString(barCount));

        String delayText = getDelayText();
        delayLabel.setText(delayText);
    }

    private String getDelayText() {
        double delay = Controller.currentDelay;
        if (delay >= 1000) {
            delay /= 1000;
            return String.format("%.1f", delay) + "s";
        }

        if (delay >= 0.01) {
            return String.format("%.1f", delay) + "ms";
        }

        delay *= 1000;
        if (delay >= 0.01) {
            return String.format("%.1f", delay) + "μs";
        }

        delay *= 1000;
        return String.format("%.1f", delay) + "ns";
    }
}