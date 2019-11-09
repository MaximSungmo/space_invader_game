import javax.swing.*;
import java.awt.*;

class EnemyObject extends GameObject {

    private EnemyBulletObject bomb;
    private final String ENEMY_IMAGE = "/images/Enemy_Original.PNG";

    public EnemyObject() {
        super();
    }

    public EnemyObject(int posX, int posY) {
        this.setPosX(posX);
        this.setPosY(posY);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(ENEMY_IMAGE));
        setImage(ii.getImage());

        bomb = new EnemyBulletObject(posX, posY);
    }

//    public EnemyObject(int posX, int posY, String image) {
//        super(posX, posY, image);
//    }

    public EnemyBulletObject getBomb() {
        return bomb;
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

    public void act(int direction, int speed) {
        this.setPosX(this.getPosX() + direction * speed);
    }
}