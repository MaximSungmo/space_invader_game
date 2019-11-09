import javax.swing.ImageIcon;

public class EnemyBulletObject extends GameObject {

    private final String bomb = "/images/EnemyBullet_Original.PNG";
    private boolean destroyed;

    public EnemyBulletObject(int x, int y) {
        setDestroyed(true);
        this.setPosX(x);
        this.setPosY(y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
        setImage(ii.getImage());
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public String getBomb() {
        return bomb;
    }
}
