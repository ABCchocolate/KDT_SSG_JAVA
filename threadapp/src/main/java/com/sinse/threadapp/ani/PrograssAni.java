package com.sinse.threadapp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class PrograssAni extends JFrame {

    private List<ProgressBarWorker> workers = new ArrayList<>();
    private JButton startButton;

    public PrograssAni() {
        setTitle("Progress Animation");
        setLayout(new FlowLayout());

        int[] speeds = {2, 5, 7}; 

        for (int speed : speeds) {
            JProgressBar bar = new JProgressBar();
            bar.setPreferredSize(new Dimension(750, 45));
            add(bar);
            workers.add(new ProgressBarWorker(bar, speed));
        }

        startButton = new JButton("Start");
        add(startButton);

        startButton.addActionListener((ActionEvent e) -> {
            for (ProgressBarWorker worker : workers) {
                worker.start();
            }
            startButton.setEnabled(false);
        });

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PrograssAni();
    }
}

