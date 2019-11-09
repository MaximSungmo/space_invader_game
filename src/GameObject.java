import java.awt.*;

abstract class GameObject { // 생성자

    // 인스턴스 변수
    private int posX;
    private int posY;
    private Image image;
    private boolean visible;
    protected boolean dying;


    public GameObject() {
        visible = true;
    }

    public GameObject(int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void moveX(int num) {
        posX += num;
    }

    public void moveY(int num) {
        posY += num;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }


    public void die() {
        visible = false;
    }
}
