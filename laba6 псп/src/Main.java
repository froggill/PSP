
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JFrame {

    private final int cloudWidth = 1000;
    private int cloudHeight = 150;
    private int snowBankHeight = 200;
    private int snowBankY = 400;
    private int snowflakeX = 200;
    private int snowflakeY = 50;
    private int snowflakeX1 = 500;
    private int snowflakeY1 = 505;
    private int snowflakeX2 = 550;
    private int snowflakeY2 = 505;
    private int snowflakeX3 = 640;
    private int snowflakeY3 = 415;
    private int snowflakeX4 = 752;
    private int snowflakeY4 = 420;
    private int snowflakeX5 = 670;
    private int snowflakeY5 = 385;
    private int snowflakeX6 = 480;
    private int snowflakeY6 = 5;

    private static Image background;
    private static Image snowflake;
    private static Image snowbank;
    private static Image cloud;
    JButton bt;

    public Main() {
        setTitle("Demo app");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new Background()); // панель
        Container content = getContentPane(); //теперь
        bt = new JButton("Старт");
        bt.setPreferredSize(new Dimension(100, 20));
        bt.setBackground(Color.white);
        bt.setForeground(Color.BLACK);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt.setVisible(false);
                Thread snowMove = new Thread(new SnowThread());
                snowMove.start();
                Thread snowMove1 = new Thread(new SnowFlakeThread());
                snowMove1.start();
            }
        });
        content.add(bt);
        content.add(new CloudSnowbank());
    }

    private static class Background extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("src/2.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(background, 0, 0, null);
        }
    }

    private class CloudSnowbank extends JPanel {
        public CloudSnowbank() {
            setOpaque(false);
            setPreferredSize(new Dimension(1000, 600));
            try {
                cloud = ImageIO.read(new File("src/pngegg.png"));
                snowbank = ImageIO.read(new File("src/з.png"));
                snowflake = ImageIO.read(new File("src/снежинка.png"));
            } catch (IOException ignored) {
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2d = (Graphics2D) g;
            graphics2d.drawImage(cloud, 0, 0, cloudWidth, cloudHeight, this);
            int snowBankWidth = 1000;
            graphics2d.drawImage(snowbank, 0, snowBankY, snowBankWidth, snowBankHeight, this);
            graphics2d.drawImage(snowflake, snowflakeX, snowflakeY, 25, 25, this);
            graphics2d.drawImage(snowflake, snowflakeX1, snowflakeY1, 55, 55, this);
            graphics2d.drawImage(snowflake, snowflakeX2, snowflakeY2, 35, 35, this);
            graphics2d.drawImage(snowflake, snowflakeX3, snowflakeY3, 45, 45, this);
            graphics2d.drawImage(snowflake, snowflakeX4, snowflakeY4, 35, 35, this);
            graphics2d.drawImage(snowflake, snowflakeX5, snowflakeY5, 45, 45, this);
            graphics2d.drawImage(snowflake, snowflakeX6, snowflakeY6, 25, 25, this);
        }
    }

    public class SnowThread implements Runnable {
        @Override
        public void run() {
            while (cloudHeight > 0) {
                cloudHeight -= 2;
                snowBankHeight += 4;
                snowBankY -= 4;
                repaint();
                try {
                    Thread.sleep(130);
                } catch (Exception ignored) {
                }
            }
        }
    }

    public class SnowFlakeThread implements Runnable {
        @Override
        public void run() {
            while (cloudHeight > 0) {
                snowflakeX = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                snowflakeX1 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY1 = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                snowflakeX2 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY2 = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                snowflakeX3 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY3 = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                snowflakeX4 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY4 = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                snowflakeX5 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY5 = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                snowflakeX6 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                snowflakeY6 = ThreadLocalRandom.current().nextInt(cloudHeight, snowBankY + 1);

                try {
                    Thread.sleep(150);
                } catch (Exception ignored) {
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().setVisible(true);
    }
}