package model;

public class Explosion {
    private int posX;
    private int posY;
    private int timer;

    // MODIFIES: this
    // EFFECTS: creates an Explosion object and initializes values
    public Explosion(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.timer = 2;
    }

    // MODIFIES: this
    // EFFECTS: decrements timer by one
    public void tick() {
        this.timer -= 1;
    }

    // EFFECTS: returns the posX
    public int getPosX() {
        return this.posX;
    }

    // EFFECTS: returns the posY
    public int getPosY() {
        return this.posY;
    }

    // EFFECTS: returns the time
    public int getTimer() {
        return this.timer;
    }
}
