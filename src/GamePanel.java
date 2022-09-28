import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    int ballX = (600 / 2) - (20 / 2); // Расположение мяча
    int ballY = (400/2) - (20 / 2); // Расположение мяча

    int player1Score = 0;
    int player2Score = 0;

    int bit1Y = 120;
    int bit2Y = 120;

    int locateX = 4; // Скорость мяча
    int locateY = -4; // Скорость мяча


    Timer timer;

    public GamePanel(){
        init();
    }

    public void init(){
        addKeyListener(this);
        timer = new Timer(10, this);
        setFocusable(true);
    }

    public void paint(Graphics graphics){
        //Поле
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, 750, 450);

        //Мяч
        graphics.setColor(Color.white);
        graphics.fillOval(ballX, ballY, 20, 20);

        //Полоса по середине экрана
        graphics.setColor(Color.white);
        graphics.drawLine(290, 0, 290, 400);

        //Игрок1
        graphics.setColor(Color.white);
        graphics.fillRect(0, bit1Y, 10, 100);

        //Игрок2
        graphics.setColor(Color.white);
        graphics.fillRect(572, bit2Y, 10, 100);

        //Счетчик очков
        Font font = new Font("Arial", Font.BOLD, 25);
        graphics.setFont(font);
        graphics.drawString(String.valueOf(player1Score), 250, 30);
        graphics.drawString(String.valueOf(player2Score), 315, 30);

    }

    public void updateBall(){
        ballX += locateX;
        ballY += locateY;
    }

    public void ballCollision(){
        if (ballY <= 0){
            locateY =-locateY;
        }
        if (ballY > 340){
            locateY =-locateY;
        }
        // Ограничение мяча сверху и снизу
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballCollision();
        updateBit2();
        updateBall();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Ракетка вверх
        if (key == KeyEvent.VK_UP){
            bit1Y -= 5;
            if (bit1Y <= 0){
                bit1Y = 0;
            }
        }
        // Ракетка вниз
        if (key == KeyEvent.VK_DOWN){
            bit1Y += 5;
            if (bit1Y >= 260){
                bit1Y = 260;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER){
            timer.start();
        }
        // Запуск по нажатию на ENTER
    }

    public void updateBit2(){
        if (ballY - 50 <= bit2Y && ballX >= 500 && locateX > 0){ // 500 - ограничение реагирвания ракетки 2
            bit2Y -= 5;
            // Скорость ракетки 2
        }
        if (bit2Y >= 260){
            bit2Y = 260;
        }
        if (bit2Y <= 0){
            bit2Y = 0;
        }
        if (ballY - 50 >= bit2Y && ballX >= 500 && locateX > 0){ // 500 - ограничение реагирвания ракетки 2
            bit2Y += 5;
            // Скорость ракетки 2
        }

        if (new Rectangle(ballX, ballY, 14, 14).intersects(new Rectangle(572, bit2Y, 10, 100))){
            locateX =-Math.abs(locateX + 1); // Увеличение скорости мяча при касании ракеткой 2
        }

        if (new Rectangle(ballX, ballY, 14, 14).intersects(new Rectangle(0, bit1Y, 10, 100))){
            locateX =-(locateX - 1); // Увеличение скорости мяча при касании ракеткой 1
        }

        score();
    }

    public void score(){
        if (ballX < -50){
            player2Score++; // Очко игроку 2
            timer.stop(); // Остановка игры
            ballX = (600 / 2) - (20 / 2); // Мяч на место
            ballY = (400/2) - (20 / 2); // Мяч на место
            locateX = 3; // Установка первоначальной скорости мяча
            locateY = 3; // Установка первоначальной скорости мяча

            bit1Y = 120; // Установка первоначального положения ракетки 1
            bit2Y = 120; // Установка первоначального положения ракетки 2
        }
        if (ballX > 620){
            player1Score++;
            timer.stop();
            ballX = (600 / 2) - (20 / 2);
            ballY = (400/2) - (20 / 2);
            locateX = -3;
            locateY = -3;

            bit1Y = 120;
            bit2Y = 120;

        }
    }
}
