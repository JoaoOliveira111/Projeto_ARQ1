// Player.java
package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pt.uma.tpsi.arqd.game.Animator;
import pt.uma.tpsi.arqd.game.Laser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends Ship {

    public int points;
    private static boolean fastShoot;

    public Player(SpriteBatch batch, int posX, int posY) {
        super(posX, posY, batch, "ship.png", 5, 2, 100, 20, 40);
        this.points = 0;
        this.fastShoot = false;
    }

    public int getHealth(){
        return health;
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && posX > 0) {
            posX -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && posX < Gdx.graphics.getWidth() - this.animator.getWidth()) {
            posX += 10;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoot();
        }
        boundingBox.setPosition(posX,posY);

    }

    @Override
    public void render() {
        handleInput();
        animator.render(posX, posY);
        renderLasers();
    }

    @Override
    public void shoot() {
        if (fastShoot) {
            for (int i = 0; i < 2; i++) {
                Laser laser = new Laser(batch, posX, posY + i * 70, false);
                laser.create();
                lasers.add(laser);
            }
        }
        else {

                Laser laser = new Laser(batch, posX, posY, false);
                laser.create();
                lasers.add(laser);

        }

    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public static boolean getFastShoot() {
        return fastShoot;
    }

    public void setHealth(int i) {
        health = i;
    }

    public void setFastShootTrue() {
        fastShoot = true;
    }

    public void setFastShootFalse() {
        fastShoot = false;
    }
}


