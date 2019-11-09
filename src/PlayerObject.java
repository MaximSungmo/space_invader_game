import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class PlayerObject extends GameObject implements Commons {

    private int width;
    private int dx;
    private int dy;
    private final int START_X = 300;
    private final int START_Y = 300;
    private final String PLAYER_IMAGE = "/images/Player_Original.PNG";

    public PlayerObject() {
        super();
        // 플레이어 스타트 위치 지정
        setPosX(START_X);
        setPosY(START_Y);
        //  플레이어 이미지 생성
        ImageIcon ii = new ImageIcon(this.getClass().getResource(PLAYER_IMAGE));
        setImage(ii.getImage());
        width = ii.getImage().getWidth(null);
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX(posX);
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY(posY);
    }

    @Override
    public Image getImage() {
        return super.getImage();
    }

    @Override
    public void setImage(Image image) {
        super.setImage(image);
    }

    @Override
    public void moveX(int num) {
        super.moveX(num);
    }

    @Override
    public void moveY(int num) {
        super.moveY(num);
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    @Override
    public boolean isDying() {
        return super.isDying();
    }

    @Override
    public void setDying(boolean dying) {
        super.setDying(dying);
    }

    @Override
    public void die() {
        super.die();
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void act() {
        this.setPosX(this.getPosX() + this.getDx());
        this.setPosY(this.getPosY() + this.getDy());

        // 플레이어 이동 범위 제한하기.
        if (this.getPosX() <= 1)
            this.setPosX(1);
        if (this.getPosX() >= BOARD_WIDTH - 70)
            this.setPosX(BOARD_WIDTH - 70);
        if (this.getPosY() >= BOARD_HEIGTH - 70)
            this.setPosY(BOARD_HEIGTH - 70);
        if (this.getPosY() < 70)
            this.setPosY(70);

    }

    // 키 입력에 따른 player 움직임
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); // 키 입력을 받아 확인
        int speed = 3;
        if (key == KeyEvent.VK_LEFT) {
            setDx(-speed);
        }
        if (key == KeyEvent.VK_RIGHT) {
            setDx(+speed);
        }
        if(key == KeyEvent.VK_UP){
            setDy(-speed);
        }
        if(key == KeyEvent.VK_DOWN){
            setDy(+speed);
        }
    }

    // 키를 놓았을 때 자리에서 멈춤.
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode(); // 키 입력을 받아 확인

        if (key == KeyEvent.VK_LEFT) {
            setDx(0);
        }
        if (key == KeyEvent.VK_RIGHT) {
            setDx(0);
        }
        if(key == KeyEvent.VK_UP){
            setDy(0);
        }
        if(key == KeyEvent.VK_DOWN){
            setDy(0);
        }
    }
}
