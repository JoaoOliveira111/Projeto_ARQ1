package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.game.Laser;

public class SmallShip extends Ship{

    public SmallShip(int posX, int posY, SpriteBatch batch, String path) {
        super(posX, posY, batch, "enemy-small.png", 2, 1, 20, 5, 4);
         }


    @Override
    public void shoot() {
        Laser laser = new Laser(batch, posX, posY, true);
        laser.create();
        lasers.add(laser);
    }
}
