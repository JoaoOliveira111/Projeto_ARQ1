package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.game.Laser;

public class MediumShip extends Ship {

    public MediumShip(int posX, int posY, SpriteBatch batch, String path) {
        super(posX, posY, batch, "enemy-medium.png", 2, 1, 40, 10, 3);
    }


    @Override
    public void shoot() {
        for (int i = 0; i < 3; i++) {
            Laser laser = new Laser(batch, posX + i * 10, posY, true);
            laser.create();
            lasers.add(laser);
        }
    }
}
