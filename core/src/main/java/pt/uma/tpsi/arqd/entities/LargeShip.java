package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.game.Laser;

public class LargeShip extends Ship {



    public LargeShip(int posX, int posY, SpriteBatch batch, String path) {
        super(posX, posY, batch, "enemy-big.png", 2, 1, 60, 20, 2);
    }


   @Override
    public void shoot() {
        for (int i = 0; i < 3; i++) {
            Laser laser = new Laser(batch, posX, posY + i * 15, true);
            laser.create();
            lasers.add(laser);
        }
    }


}
