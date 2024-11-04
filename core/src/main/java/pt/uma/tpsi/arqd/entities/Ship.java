package pt.uma.tpsi.arqd.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.tpsi.arqd.game.Animator;
import pt.uma.tpsi.arqd.game.Laser;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;


public abstract class Ship  {

    protected int posX, posY, health, damage, speedOfLaser;
   protected int initialPosX;
    protected Animator animator;
    protected Rectangle boundingBox;
    protected boolean collided;
    protected ArrayList<Laser> lasers;
    protected SpriteBatch batch;



    public Ship(int posX, int posY, SpriteBatch batch, String path, int columns, int rows, int health, int damage, int speedOfLaser){
        this.collided = false;
        this.initialPosX = posX;
        this.posX = posX;
        this.posY = posY;
        this.batch = batch;
        this.animator = new Animator(batch, path, columns, rows);
        this.lasers = new ArrayList<>();
        this.health = health;
        this.damage = damage;
        this.speedOfLaser = speedOfLaser;
        this.boundingBox = new Rectangle(posX, posY, animator.getWidth(), animator.getHeight());
    }


    public void create(){
        animator.create();
        this.boundingBox = new Rectangle(posX, posY, animator.getWidth(), animator.getHeight());
    }

    public void render(){
        animator.render(posX,posY);
        renderLasers();
    }

    public abstract void shoot();

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    protected void renderLasers() {
        for (Laser laser : lasers) {
            laser.update( this.speedOfLaser);
        }
        lasers.removeIf(laser -> !laser.isActive());
        for (Laser laser : lasers) {
            laser.render();
        }
}}
