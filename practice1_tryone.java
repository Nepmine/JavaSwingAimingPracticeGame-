import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class practice1_tryone {

    int score=0;
    boolean countState=true;
    private JFrame frame; // Make frame a class-level instance variable
    // private volatile boolean stopThread = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new practice1_tryone().initialize();
        });
    }

    public void initialize() {
        frame = new JFrame("Custom Layout Example");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Check your mouse moment");
        l1.setBounds(350, 250, 500, 30);
        frame.add(l1);

        Font labelFont = l1.getFont();
        l1.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));

        JLabel l2 = new JLabel("WITH A SIMPLE GAME");
        l2.setBounds(410, 280, 200, 15);
        frame.add(l2);

        JButton button1 = new JButton("  PLAY  ");
        button1.setBounds(415, 320, 100, 30);

        button1.addActionListener(e -> {
            l1.setVisible(false);
            l2.setVisible(false);
            button1.setVisible(false);
            
            startGame();
        });
        frame.add(button1);

        frame.setVisible(true);
    }
JButton btn1 ;
    public void startGame() {
        frame.revalidate();
        frame.repaint();
        threadCounter(); // Start a new thread for countdown
        score++;

        Random random = new Random();
        int randomWidth = random.nextInt(900) + 10;
        int randomHeight = random.nextInt(600) + 10;

        btn1 = new JButton("");
        btn1.setBounds(randomWidth, randomHeight, 50, 50);
        frame.add(btn1);

        btn1.addActionListener(e -> {
            btn1.setVisible(false);
            startGame();
        });
    }

    public void threadCounter() {
        Thread countdown = new Thread(new Runnable() {
            int p = 5;
            boolean x;
            




            @Override
            public void run() {
                if(countState)
            {
                countState=false;
            }
            else
            {
                countState=true;
            }
            
            x=countState;
            
                for (int i = 5; i > 0; i--) {
                    
                    if(x!=countState)
                    return;
                    final int count = i;

                    JLabel lk = new JLabel(Integer.toString(count));
                    lk.setBounds(p, 3, 500, 30);

                    // Update Swing components on the EDT using SwingUtilities.invokeLater
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            p += 7;
                            frame.add(lk);
                            frame.revalidate();
                            frame.repaint();
                        }
                    });

                    try {
                        if(score>10)
                        {
                            Thread.sleep(200);
                            JLabel bigPlus = new JLabel("Level 2: Speed++");
                            bigPlus.setBounds(750, 5, 500, 30);
                            frame.add(bigPlus);

                            Font originalFont = bigPlus.getFont();
                            Font newFont = originalFont.deriveFont(originalFont.getSize() * 2.0F); // Increase font size by a factor (2.0F in this case)

                            bigPlus.setFont(newFont);

                        }
                        else
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        System.out.println("Error: " + e);
                    }
                    lk.setVisible(false);
                }
                btn1.setVisible(false);
                

                JLabel endGame = new JLabel("Game End");
        endGame.setBounds(400, 250, 500, 30);
        frame.add(endGame);
        Font labelFont = endGame.getFont();
        endGame.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));


        score-=1;
        JLabel finalScore = new JLabel("Score :"+ score);
        finalScore.setBounds(400, 300, 500, 30);
        frame.add(finalScore);
        Font labelFont2 = finalScore.getFont();
        finalScore.setFont(new Font(labelFont2.getName(), Font.PLAIN, 20));


            }
        });
        countdown.start();
    }

}
