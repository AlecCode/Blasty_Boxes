package model;

import java.awt.event.KeyEvent;

public class Player extends Bomber {
    private boolean inAction = false;

    // MODIFIES: super, this
    // EFFECTS: creates a Player object and initializes values
    public Player(BomberManGame g) {
        super(g);
        super.setPosX(1);
        super.setPosY(1);
    }

    // MODIFIES: super
    // EFFECTS: takes input from the user and converts it into an action.
    public void move(int k) {
        if (k == KeyEvent.VK_W) {
            super.move("up");
        }
        if (k == KeyEvent.VK_S) {
            super.move("down");
        }
        if (k == KeyEvent.VK_D) {
            super.move("right");
        }
        if (k == KeyEvent.VK_A) {
            super.move("left");
        }
        if (k == KeyEvent.VK_SPACE) {
            super.dropBomb();
        }
    }

    // MODIFIES: this
    // EFFECTS: checks that the player is not currently inAction and passes keystroke onto the move function
    public void keyPressed(int k) {
        if (!this.inAction) {
            move(k);
            this.inAction = true;
        }
    }

    // EFFECTS: changes the inAction to false when a key is released
    public void keyReleased() {
        this.inAction = false;
    }

    // EFFECTS: returns inAction
    public boolean getInAction() {
        return this.inAction;
    }
}
