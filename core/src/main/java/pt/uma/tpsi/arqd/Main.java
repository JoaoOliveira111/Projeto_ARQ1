package pt.uma.tpsi.arqd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.entities.Fleet;
import pt.uma.tpsi.arqd.entities.Player;
import pt.uma.tpsi.arqd.entities.Ship;
import pt.uma.tpsi.arqd.game.PowerUP;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private pt.uma.arq.game.BackgroundManagement backgroundManagement;
//    private pt.uma.arq.game.BitmapFont font;

    private Player player;
    private Fleet fleet;




    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(1280, 800);
        batch = new SpriteBatch();
        backgroundManagement = new pt.uma.arq.game.BackgroundManagement(batch);

        player = new Player(batch, 100, 100);
        player.create();

        fleet = new Fleet(batch);
        fleet.create();
        fleet.shoot ();
        fleet.update();


        PowerUP.spwan(batch);


    }

    public void restartGame()   {
        pt.uma.arq.game.BitmapFont.drawText(Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2, "Game Over", batch);
        pt.uma.arq.game.BitmapFont.drawText(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 +100, "Press space to restart", batch);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.setHealth(100);
            fleet = new Fleet(batch);
            fleet.create();
            fleet.shoot ();
            fleet.update();
        }
    }

    @Override
    public void render() {




        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (player.getHealth() <= 0) {
            restartGame();
            batch.end();
            return;
        }

        backgroundManagement.render();

        player.render();

        fleet.renderFleet();

        fleet.render();

        fleet.handleCollisions(player);

        PowerUP.show();

        PowerUP.handleCollisions(player);

        if (fleet.isEmpty()) {
            pt.uma.arq.game.BitmapFont.drawText(600, 700, "Victory!", batch);
            restartGame();
            batch.end();
            return;
        }

        if( Player.getFastShoot() ){
            pt.uma.arq.game.BitmapFont.drawText(10, 720, "Power Up is active", batch);
        }

        pt.uma.arq.game.BitmapFont.drawText(10,750,"Health: " + player.getHealth() + "%" ,batch);
        pt.uma.arq.game.BitmapFont.drawText(10,780,"Points: " + player.points ,batch);

        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
