package com.sinse.threadapp.ani;

import javax.swing.JProgressBar;

public class ProgressBarWorker {
    private final JProgressBar bar;
    private final int speed;
    private int value = 0;
    private  boolean running = true;

    public ProgressBarWorker(JProgressBar bar, int speed) {
        this.bar = bar;
        this.speed = speed;
    }

    public void start() {
        new Thread(() -> {
            while (running && value < 100) {
                value += speed;
                bar.setValue(Math.min(value, 100));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (value >= 100) {
                    running = false;
                }
            }
        }).start();
    }
}