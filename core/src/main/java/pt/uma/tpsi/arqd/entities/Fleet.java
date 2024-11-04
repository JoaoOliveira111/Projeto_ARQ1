package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.game.Laser;


import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.badlogic.gdx.utils.Timer;

public class Fleet   {
    private ArrayList<Ship> fleet;
    protected SpriteBatch batch;
    private Timer timer;
    private ArrayList<Explosion> explosionArrayList;
    private Random random;

    public  Fleet (SpriteBatch batch){
        this.batch = batch;
        this.fleet = new ArrayList<>();
        this.timer = new Timer();
        this.explosionArrayList = new ArrayList<>();
        this.random = new Random();
    }

    public void addShip(Ship ship) {
        ship.create();
        fleet.add(ship);
    }

    public void renderFleet() {
        for (Ship ship : fleet) {
            ship.render();
            ship.renderLasers();
        }


        Iterator<Explosion> explosionIterator = explosionArrayList.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.update();
            explosion.render();

            if (explosion.isFinished()) {
                explosionIterator.remove();
            }
        }


    }



    public void create() {
        int smallShipY = 500;
        int mediumShipY = 600;
        int largeShipY = 700;
        int spacing = 110;
        int totalWidth = 9 * spacing;

        int startX = (Gdx.graphics.getWidth() - totalWidth) / 2;

        for (int i = 0; i < 10; i++) {
            addShip(new SmallShip(startX + i * spacing, smallShipY, batch, "small-ship.png"));
            addShip(new MediumShip(startX + i * spacing, mediumShipY, batch, "medium-ship.png"));
            addShip(new LargeShip(startX + i * spacing, largeShipY, batch, "large-ship.png"));
        }

    }




    public void render(){

        for (Ship ship : fleet){
            ship.render();
        }


    }


    public void handleCollisions(Player player) {
        Iterator<Ship> shipIterator = fleet.iterator();
        while (shipIterator.hasNext()) {
            Ship ship = shipIterator.next();
            for (Laser l : player.getLasers()) {
                if (ship.boundingBox.overlaps(l.getBoundingBox())) {
                    ship.health -= player.damage;

                    if (ship.health <= 0){
                        ship.collided = true;
                        explosionArrayList.add(new Explosion(batch, ship.posX, ship.posY));
                        player.points += 10;
                        shipIterator.remove();
                    }

                    player.getLasers().remove(l);
                    break;

             }
            }
            for (Laser l : ship.getLasers()) {
                if (player.boundingBox.overlaps(l.getBoundingBox())) {
                    player.health -= ship.damage;
                    player.setFastShootFalse();
                    pt.uma.arq.game.BitmapFont.drawText(ship.posX,ship.posY,"-"+ship.damage,batch);
                    if (player.health <= 0){
                        ship.collided = true;


                    }

                    ship.getLasers().remove(l);
                    break;

                }
            }

        }
    }

    public void shoot() {
        timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (!fleet.isEmpty()) {
                    int randomIndex = random.nextInt(fleet.size());
                    Ship randomShip = fleet.get(randomIndex);
                    randomShip.shoot();
                }
            }
        }, 0, 0.5f);
    }


    public boolean isEmpty() {
        return fleet.isEmpty();
    }

    public void update() {
    Timer.schedule(new Timer.Task() {
        @Override
        public void run() {
            for (Ship ship : fleet) {
                int moveX = random.nextInt(31) - 15;
                ship.posX += moveX;

                if (ship.posX > ship.initialPosX + 30) {
                    ship.posX = ship.initialPosX + 30;
                } else if (ship.posX < ship.initialPosX - 30) {
                    ship.posX = ship.initialPosX - 30;
                }

                ship.boundingBox.setPosition(ship.posX, ship.posY);
            }
        }
    }, 0, 1);
}

}

