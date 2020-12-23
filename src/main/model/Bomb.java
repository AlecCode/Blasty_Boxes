package model;

import ui.Audio;
import java.util.ArrayList;

public class Bomb {
    private int posX;
    private int posY;
    private int timer;
    private Audio explosionSound = new Audio("Sounds/explosionSound.wav");

    // REQUIRES: 0 < x < WIDTH, 0 < y < HEIGHT
    // MODIFIES: this
    // EFFECTS: creates a new bomb object and initializes its values
    public Bomb(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.timer = 10;
    }

    // MODIFIES: this
    // EFFECTS: decrements the timer on the bomb by one
    public void tick() {
        this.timer = this.timer - 1;
    }

    // EFFECTS: returns a list of explosions that start from the bomb and move up, down, left, right
    public ArrayList<Explosion> explode(Arena a) {
        ArrayList<Explosion> blasts = new ArrayList<Explosion>();

        this.explosionSound.play();
        blasts.add(new Explosion(this.posX, this.posY));

        blasts.addAll(makeExplosionsRight(a));
        blasts.addAll(makeExplosionsDown(a));
        blasts.addAll(makeExplosionsLeft(a));
        blasts.addAll(makeExplosionsUp(a));

        return blasts;
    }

    // EFFECTS: returns a list of explosions that start from the bomb and move right 3 tiles or until a wall is reached
    public ArrayList<Explosion> makeExplosionsRight(Arena arena) {
        ArrayList<Explosion> blasts = new ArrayList<Explosion>();

        for (int i = 1; i < 4; i++) {
            if (arena.getTile(this.posX + i, this.posY).equalsIgnoreCase("w")) {
                break;
            } else {
                blasts.add(new Explosion(this.posX + i, this.posY));
            }
        }

        return blasts;
    }

    // EFFECTS: returns a list of explosions that start from the bomb and move down 3 tiles or until a wall is reached
    public ArrayList<Explosion> makeExplosionsDown(Arena arena) {
        ArrayList<Explosion> blasts = new ArrayList<Explosion>();

        for (int i = 1; i < 4; i++) {
            if (arena.getTile(this.posX, this.posY + i).equalsIgnoreCase("w")) {
                break;
            } else {
                blasts.add(new Explosion(this.posX, this.posY + i));
            }
        }

        return blasts;
    }

    // EFFECTS: returns a list of explosions that start from the bomb and move left 3 tiles or until a wall is reached
    public ArrayList<Explosion> makeExplosionsLeft(Arena arena) {
        ArrayList<Explosion> blasts = new ArrayList<Explosion>();

        for (int i = 1; i < 4; i++) {
            if (arena.getTile(this.posX - i, this.posY).equalsIgnoreCase("w")) {
                break;
            } else {
                blasts.add(new Explosion(this.posX - i, this.posY));
            }
        }

        return blasts;
    }

    // EFFECTS: returns a list of explosions that start from the bomb and move up 3 tiles or until a wall is reached
    public ArrayList<Explosion> makeExplosionsUp(Arena arena) {
        ArrayList<Explosion> blasts = new ArrayList<Explosion>();

        for (int i = 1; i < 4; i++) {
            if (arena.getTile(this.posX, this.posY - i).equalsIgnoreCase("w")) {
                break;
            } else {
                blasts.add(new Explosion(this.posX, this.posY - i));
            }
        }

        return blasts;
    }

    // EFFECTS: returns the posX of the bomb
    public int getPosX() {
        return this.posX;
    }

    // EFFECTS: returns the posY of the bomb
    public int getPosY() {
        return this.posY;
    }

    // EFFECTS: returns the timer of the bomb
    public int getTimer() {
        return this.timer;
    }
}
