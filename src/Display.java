import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Display extends JPanel implements Runnable, Commons {

    public static int SCORE = 0;
    public int speed = 1;
    private Dimension d;
    private List<EnemyObject> enemies;
    private PlayerObject player;
    private List<BulletObject> bullets;

    private int bulletNo = 10;
    private EnemyBulletObject bomb;
    private final String expl = "/images/explosion.png";

    private int enemyX = 300;
    private int enemyY = 25;
    private int direction = -1;
    private int deaths = 0;

    private boolean ingame = true;
    private boolean havewon = true;
    private Thread animator;

    private String message = "";

    /*
     * Constructor
     */
    public Display() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH + 200, BOARD_HEIGTH + 100);
        setBackground(Color.black);
        gameInit();
        setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {
        int enemyXNo = 4;
        int enemyYNo = 2;
        int xTerm = 20;
        enemies = new ArrayList<EnemyObject>();

        // invader 생성 위치
        for (int i = 0; i < enemyXNo; i++) {
            for (int j = 0; j < enemyYNo; j++) {
                EnemyObject enemy = new EnemyObject(enemyX + ((i * xTerm * 4) + (j * xTerm * 2)), enemyY + (j * xTerm));
                enemies.add(enemy);
            }
        }

        player = new PlayerObject();
        bullets = new ArrayList<BulletObject>();

        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    // enimies arrayList로 가져와서 그리기
    public void drawEnemy(Graphics g) {
        // arrayList의 iterator;
        Iterator it = enemies.iterator();

        while (it.hasNext()) {
            EnemyObject enemy = (EnemyObject) it.next();

            if (enemy.isVisible()) {
                g.drawImage(enemy.getImage(), enemy.getPosX(), enemy.getPosY(), this);
            }

            if (enemy.isDying()) {
                enemy.die();
            }
        }
    }


    public void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), this);
        }

        if (player.isDying()) {
            player.die();
            ingame = false;
        }
    }


    public void drawBullet(Graphics g) {
        Iterator bt = bullets.iterator();
        while (bt.hasNext()) {
            BulletObject bullet = (BulletObject) bt.next();

            if (bullet.isVisible())
                g.drawImage(bullet.getImage(), bullet.getPosX(), bullet.getPosY(), this);
        }

    }


    public void drawBombing(Graphics g) {
        Iterator it = enemies.iterator();

        while (it.hasNext()) {
            EnemyObject e = (EnemyObject) it.next();

            EnemyBulletObject b = e.getBomb();

            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getPosX(), b.getPosY(), this);
            }
        }
    }

    public void drawMap(Graphics g) {

        g.setColor(Color.black);
        Font font = new Font("gothic", Font.PLAIN, 20);
        g.setFont(font);
        for (int i = 10; i < BOARD_HEIGTH - 20; i = i + 20) {
            g.drawString("..#", 10, i);
            if (i == 30 || i == 50 || i == 70) {
                g.drawString("#....┌──────┐", 600, 30);
                if (SCORE == 0) {
                    g.drawString("#....│Score " + "00" + "──│", 600, 50);
                } else {
                    g.drawString("#....│Score " + SCORE + "──│", 600, 50);
                }
                g.drawString("#....└──────┘", 600, 70);
            } else if (i == BOARD_HEIGTH - 50) {
                g.drawString("#..........By Eun.........", 600, i);
            } else {
                g.drawString("#.............................", 600, i);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {
            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawEnemy(g);
            drawPlayer(g);
            drawBullet(g);
            drawBombing(g);
            drawMap(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver() {

        JOptionPane op = new JOptionPane();
        op.setSize(100, 100);
        int result = op.showConfirmDialog(null, message, "title", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            ingame=false;
            isClose=true;
            System.out.println("종료되었습니다.");
            System.exit(0);
        } else {
            SCORE=0;
            deaths=0;
            speed=1;
            System.out.println("재시작되었습니다..");
            this.removeAll();
            gameInit();
            revalidate();
            repaint();
            ingame = true;
        }

    }


    public void animationCycle() {
        // 총 8마리 다 죽였는 지 확인,
        if (deaths == NUMBER_OF_ENIMIES_TO_DESTROY) {
            message = "YOU WIN \n PLAY AGAIN ? (Y/N).";
            ingame=false;
        }

        // player
        player.act();

        // bullet
        Iterator bt1 = bullets.iterator();
        while (bt1.hasNext()) {
            BulletObject bullet = (BulletObject) bt1.next();
            if (bullet.isVisible()) {
                Iterator it = enemies.iterator();
                int bulletX = bullet.getPosX();
                int bulletY = bullet.getPosY();

                while (it.hasNext()) {
                    EnemyObject EnemyObject = (EnemyObject) it.next();
                    int enemyX = EnemyObject.getPosX();
                    int enemyY = EnemyObject.getPosY();

                    if (EnemyObject.isVisible() && bullet.isVisible()) {
                        // 총알이 닿으면 enemyObject 죽음 처리
                        if (bulletX >= (enemyX) && bulletX <= (enemyX + ENEMY_WIDTH)
                                && bulletY >= (enemyY)
                                && bulletY <= (enemyY + ENEMY_HEIGHT)) {
                            ImageIcon ii = new ImageIcon(getClass().getResource(expl));
                            Image ii2 = ii.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                            EnemyObject.setImage(ii2);
                            EnemyObject.setDying(true);
                            deaths++;
                            SCORE = SCORE + 10;
                            bullet.die();
                        }
                    }
                }
                // 불렛이 화면을 벗아나면 사라짐.
                int y = bullet.getPosY();
                y -= 10;
                if (y < 0)
                    bullet.die();
                else
                    bullet.setPosY(y);
            }
        }

        // enemies
        Iterator it1 = enemies.iterator();

        // 하나씩 돌면서 getPosX값을 얻기
        while (it1.hasNext()) {
            EnemyObject a1 = (EnemyObject) it1.next();
            int x = a1.getPosX();
            // 좌우로 돌면서 왔다갔다 내려오게 만들기. 벽만들기
            if (x >= BOARD_WIDTH - BORDER_RIGHT * 2 && direction != -1) {
                direction = -1;
                Iterator i1 = enemies.iterator();
                while (i1.hasNext()) {
                    EnemyObject a2 = (EnemyObject) i1.next();
                    a2.setPosY(a2.getPosY() + GO_DOWN);
                }
                speed++;
            }

            if (x <= BORDER_LEFT && direction != 1) {
                direction = 1;
                Iterator i2 = enemies.iterator();
                while (i2.hasNext()) {
                    EnemyObject a = (EnemyObject) i2.next();
                    a.setPosY(a.getPosY() + GO_DOWN);
                }
            }
        }


        // 만약 적이 화면 밑 까지 내려오면 당신의 패배
        Iterator it = enemies.iterator();
        while (it.hasNext()) {
            EnemyObject enemy = (EnemyObject) it.next();
            if (enemy.isVisible()) {
                int y = enemy.getPosY();
                // 바닥에 닿으면 게임 끝 패배
                if (y > GROUND - ENEMY_HEIGHT) {
                    message = "You LOSE!! \n Play AGAIN? (Y/N)";
                    ingame=false;
                }
                enemy.act(direction, speed);
            }
        }

        // bombs

        Iterator i3 = enemies.iterator();
        Random generator = new Random();

        while (i3.hasNext()) {
            int bullet = generator.nextInt(15);
            EnemyObject a = (EnemyObject) i3.next();
            EnemyBulletObject b = a.getBomb();
            if (bullet == CHANCE && a.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setPosX(a.getPosX());
                b.setPosY(a.getPosY());
            }

            int bombX = b.getPosX();
            int bombY = b.getPosY();
            int playerX = player.getPosX();
            int playerY = player.getPosY();

            if (player.isVisible() && !b.isDestroyed()) {
                if (bombX >= (playerX) && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + PLAYER_HEIGHT)) {
                    ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/Bullet_1.jpg"));
                    Image ii2 = ii.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                    player.setImage(ii2);
                    player.setDying(true);
                    b.setDestroyed(true);
                    message = "You LOSE!! \n Play AGAIN? (Y/N)";
                }
            }

            if (!b.isDestroyed()) {
                b.setPosY(b.getPosY() + 1);
                if (b.getPosY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
    }


    boolean isClose = false;
    public boolean isClose(){
        return isClose;
    }
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getPosX();
            int y = player.getPosY();

            if (ingame) {
                int key = e.getKeyCode();
                // 총알 이벤트 발생 (array collection으로 처리하기)
                int i = 0;
                if (key == KeyEvent.VK_SPACE) {
                    bullets.add(new BulletObject(x, y));
                }
            }
        }

    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 1;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
        gameOver();
    }
}
