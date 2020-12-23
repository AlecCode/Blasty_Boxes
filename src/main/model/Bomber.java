package model;

import ui.Audio;

import java.util.ArrayList;

public class Bomber {
    private int posX;
    private int posY;
    private BomberManGame game;
    private Audio placeSound = new Audio("Sounds/bombPlaceSound.wav");
    private ArrayList<Bomb> bombs;

    // MODIFIES: this
    // EFFECTS: creates a new Bomber object and initializes its values
    public Bomber(BomberManGame g) {
        this.game = g;
        this.bombs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: takes a direction and Arena, moves Bomber in direction if Bomber will not collide with something
    public void move(String d) {
        if (d.equalsIgnoreCase("up")) {
            if (!collides(this.posX, this.posY - 1)) {
                this.posY -= 1;
            }
        } else if (d.equalsIgnoreCase("right")) {
            if (!collides(this.posX + 1, this.posY)) {
                this.posX += 1;
            }
        } else if (d.equalsIgnoreCase("down")) {
            if (!collides(this.posX, this.posY + 1)) {
                this.posY += 1;
            }
        } else if (d.equalsIgnoreCase("left")) {
            if (!collides(this.posX - 1, this.posY)) {
                this.posX -= 1;
            }
        }
    }

    // EFFECTS: returns if the Bomber collides with something
    private boolean collides(int x, int y) {
        boolean collision = false;
        Player p = this.game.getPlayer();
        Arena a = this.game.getArena();
        ArrayList<Enemy> es = this.game.getEnemies();

        if (a.getTile(x, y).equalsIgnoreCase("w")) {
            collision = true;
        }

        if (enemyCollides(x, y, es)) {
            collision = true;
        }

        if (playerCollides(x, y, p)) {
            collision = true;
        }

        return collision;
    }

    // EFFECTS: returns true if the Bomber collides with an enemy or one of their bombs
    private boolean enemyCollides(int x, int y, ArrayList<Enemy> es) {
        boolean collision = false;

        for (Enemy e : es) {
            if (e.getPosX() == x && e.getPosY() == y) {
                collision = true;
            }
            for (Bomb b : e.getBombs()) {
                if (b.getPosX() == x && b.getPosY() == y) {
                    collision = true;
                }
            }
        }

        return collision;
    }

    // EFFECTS: returns true if the Bomber collides with the player or one of their bombs
    private boolean playerCollides(int x, int y, Player p) {
        boolean collision = false;

        if (p.getPosX() == x && p.getPosY() == y) {
            collision = true;
        }

        for (Bomb b : p.getBombs()) {
            if (b.getPosX() == x && b.getPosY() == y) {
                collision = true;
            }
        }

        return collision;
    }

    // MODIFIES: this
    // EFFECTS: creates a Bomb and adds it to the bombs ArrayList
    public void dropBomb() {
        this.placeSound.play();
        this.bombs.add(new Bomb(this.posX, this.posY));
    }

    // MODIFIES: this
    // EFFECTS: removes a Bomb from the bombs ArrayList
    public void removeBomb(Bomb b) {
        this.bombs.remove(b);
    }

    // MODIFIES: this
    // EFFECTS:  sets the character's x position
    public void setPosX(int x) {
        this.posX = x;
    }

    // MODIFIES: this
    // EFFECTS:  sets the character's y position
    public void setPosY(int y) {
        this.posY = y;
    }

    // EFFECTS:  returns the character's x position
    public int getPosX() {
        return this.posX;
    }

    // EFFECTS:  returns the character's y position
    public int getPosY() {
        return this.posY;
    }

    // EFFECTS:  returns the character's dropped bombs
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }
}
