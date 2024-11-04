package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.game.Animator;
import com.badlogic.gdx.utils.Timer;

public class Explosion {
    private Animator animator;
    private boolean finished;
    private int posX;
    private int posY;
    private Timer.Task timerTask;
    private static final float EXPLOSION_DURATION = 2f;

    public Explosion(SpriteBatch batch, int posX, int posY) {
        this.animator = new Animator(batch, "explosion.png", 5, 1);
        this.animator.create();
        this.posX = posX;
        this.posY = posY;
        this.finished = false;


        timerTask = new Timer.Task() {
            @Override
            public void run() {
                finished = true;
            }
        };
        Timer.schedule(timerTask, EXPLOSION_DURATION);
    }

    public void update() {
        if (!finished) {
            animator.update();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void render() {
        if (!finished) {
            animator.render(posX, posY);
        }
    }

}
