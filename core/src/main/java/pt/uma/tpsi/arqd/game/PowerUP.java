package pt.uma.tpsi.arqd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pt.uma.tpsi.arqd.entities.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.utils.Timer;

public class PowerUP {
    private int posX, posY;
    private boolean active;
    private Animator animator;
    private Rectangle boundingBox;

    public static ArrayList<PowerUP> powerUps = new ArrayList<>();



    public PowerUP(SpriteBatch batch, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.active = true;
        this.animator = new Animator(batch, "power-up.png", 2, 2);
        create();
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

    public void update(int speed) {
        if (active) {
            posY -= speed;
            boundingBox.setPosition(posX, posY);

            if (posY < 0) {
                active = false;

            }
        }
    }


    public static void spwan(SpriteBatch batch) {
    Timer.schedule(new Timer.Task() {
        @Override
        public void run() {

            int posX = new Random().nextInt(Gdx.graphics.getWidth() - 50);
            PowerUP powerUp = new PowerUP(batch, posX, Gdx.graphics.getHeight());
            powerUp.create();
            powerUps.add(powerUp);


        }
    }, 0, 5);
}

    public static void handleCollisions(Player player) {
        Iterator<PowerUP> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            PowerUP powerUP = iterator.next();
            if (player.getBoundingBox().overlaps(powerUP.boundingBox)) {

                player.setHealth(100);
                player.setFastShootTrue();
                iterator.remove();
            }
        }
    }

    public static void show() {
        Iterator<PowerUP> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            PowerUP powerUP = iterator.next();
            powerUP.render();
            powerUP.update(3);
            if (!powerUP.active) {
                iterator.remove();
            }
        }
    }
}
