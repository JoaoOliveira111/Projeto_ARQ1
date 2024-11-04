// Laser.java
package pt.uma.tpsi.arqd.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pt.uma.tpsi.arqd.entities.Ship;

public class Laser {
    private int posX, posY;
    private boolean active;
    private Animator animator;
    private Rectangle boundingBox;
    private boolean flip;

    public Laser(SpriteBatch batch, int posX, int posY, boolean flip) {

        this.posX = posX;
        this.posY = posY;
        this.active = true;
        this.flip = flip;
        this.animator = new Animator(batch, "laser-bolts.png", 2, 1);
        this.animator.setFlip(this.flip);

    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void create() {
        animator.create();
        this.boundingBox = new Rectangle(posX, posY, animator.getWidth(), animator.getHeight());
    }

    public void render() {
        if (active) {
            animator.render(posX, posY);
        }
    }

    public void update(int speedOfLaser) {

        if (active) {
            posY = flip ? posY - speedOfLaser : posY + speedOfLaser;

            boundingBox.setPosition(posX, posY);

            if (posY > 800 || posY < 0) {
                active = false;
            }
        }
    }

    public boolean isActive() {
        return active;
    }
}
