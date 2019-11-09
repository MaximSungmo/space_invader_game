import javax.swing.*;
import java.awt.*;

class BulletObject extends GameObject {

    private boolean destroyed;

    private final String BULLET_IMAGE = "/images/Bullet_Original.PNG";
    private final int H_SPACE = 1;
    private final int V_SPACE = 1;

    public BulletObject() {
        super();
    }

    public BulletObject(int posX, int posY) {
        setPosX(posX + H_SPACE);
        setPosY(posY + V_SPACE);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(BULLET_IMAGE));
        setImage(ii.getImage());
    }

//    public BulletObject(int posX, int posY, String image) {
//        super(posX, posY, image);
//    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
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
}