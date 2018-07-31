package scene;


import constants.Entity;
import entity.Ellipse;
import entity.Image;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.RepeatBehavior;
import org.pushingpixels.trident.TimelineScenario;
import org.pushingpixels.trident.swing.SwingRepaintTimeline;
import org.w3c.dom.Document;
import scenario.MovingImage;
import util.XMLConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public final class Aquarium extends JFrame {
    private final Set<Image> fishes = new HashSet<>();

    private final Map<Set<Image>, TimelineScenario> fishScenarios;

    private final JPanel mainPanel;

    private final Document config;


    private Aquarium() {
        super("Aquarium");

        this.config = new XMLConfig("src/main/resources/config.xml").getConfig();

        this.fishScenarios = new HashMap<>();

        this.mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                synchronized (fishes) {
                    for (Image fish : fishes) {
                        fish.paint(g);
                        for (Ellipse bubble: fish.getEllipses()) {
                            bubble.paint(g);
                        }
                    }

                }
            }
        };
        this.mainPanel.setBackground(Color.black);
        this.mainPanel.setPreferredSize(new Dimension(600, 600));

        Timeline repaint = new SwingRepaintTimeline(this);
        repaint.playLoop(RepeatBehavior.LOOP);

        this.mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                synchronized (fishes) {
                    for (TimelineScenario scenario : fishScenarios.values())
                        scenario.suspend();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                synchronized (fishes) {
                    for (TimelineScenario scenario : fishScenarios.values())
                        scenario.resume();
                }
            }
        });

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if ((mainPanel.getWidth() == 0) || (mainPanel.getHeight() == 0))
                    return;
                new Thread(() -> addFish()).start();
            }
        });

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addFish() {
        final CountDownLatch latch = new CountDownLatch(1);

        MovingImage movingImage = new MovingImage(this.config.getElementsByTagName(Entity.IMAGE.name().toLowerCase()));
        TimelineScenario scenario = movingImage.getScenario();


        this.fishes.addAll(movingImage.getImages());

        synchronized (this.fishes) {
            scenario.addCallback(() -> {
                synchronized (this.fishes) {
                    this.fishes.clear();
                    this.fishScenarios.clear();
                    latch.countDown();
                }
            });
            this.fishScenarios.put(this.fishes, scenario);
            scenario.play();
        }

        try {
            latch.await();
        } catch (Exception ignored) { }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Aquarium().setVisible(true));
    }
}