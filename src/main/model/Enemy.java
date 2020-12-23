package model;

import java.util.Random;

public class Enemy extends Bomber {
    private boolean hard;
    private int act = 0;
    private Random rand;

    // MODIFIES: super, this
    // EFFECTS: creates an Enemy object and initializes values
    public Enemy(BomberManGame g, String d) {
        super(g);

        this.rand = new Random();
        this.hard = d.equalsIgnoreCase("hard");
    }

    // EFFECTS: calls move behaviour based on what difficulty the computer is set to
    public void move(Player p) {
        if (this.hard) {
            hardMove(p);
        } else {
            easyMove();
        }
    }

    // MODIFIES: super
    // EFFECTS: randomly selects an action to preform
    private void easyMove() {
        if (this.act == 0) {
            super.move("up");
        } else if (this.act == 1) {
            super.move("right");
        } else if (this.act == 2) {
            super.move("down");
        } else if (this.act == 3) {
            super.move("left");
        } else {
            super.dropBomb();
        }

        this.act = this.rand.nextInt(5);
    }

    // MODIFIES: super
    // EFFECTS: randomly selects a direction to move towards the player or drops a bomb
    private void hardMove(Player p) {
        if (this.act == 0 && !(p.getPosX() == super.getPosX())) {
            if (p.getPosX() > super.getPosX()) {
                super.move("right");
            } else {
                super.move("left");
            }
        } else if (this.act == 1 && !(p.getPosY() == super.getPosY())) {
            if (p.getPosY() > super.getPosY()) {
                super.move("down");
            } else {
                super.move("up");
            }
        } else if (this.act == 2 && withinThree(p)) {
            super.dropBomb();
        }

        this.act = this.rand.nextInt(3);
    }

    // EFFECTS: returns whether or not the player is within 3 coordinates in the x and y axis
    private boolean withinThree(Player p) {
        return Math.abs(this.getPosX() - p.getPosX()) < 3 && Math.abs(this.getPosY() - p.getPosY()) < 3;
    }

    // MODIFIES: this
    // EFFECTS: sets this.act to be a
    public void setAct(int a) {
        this.act = a;
    }
}
