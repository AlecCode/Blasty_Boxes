package ui;

import model.*;
import java.util.ArrayList;

public class Drawer implements Constants {
    // MODIFIES: this
    // EFFECTS: creates a new Drawer object
    public Drawer() {
    }

    // EFFECTS: takes elements of BomberManGame and outputs it into the console
    public ArrayList<String> drawMap(Player p, Arena m, ArrayList<Enemy> es, ArrayList<Explosion> ex) {
        ArrayList<String> combinedMap = new ArrayList<>();
        ArrayList<Bomb> bombs = p.getBombs();

        for (Enemy e : es) {
            bombs.addAll(e.getBombs());
        }

        for (int y = 0; y < G_HEIGHT; y++) {
            for (int x = 0; x < G_WIDTH; x++) {
                combinedMap.add(placeCharacter(x, y, bombs, es, p, m, ex));
            }
        }

        return combinedMap;
    }

    // EFFECTS: places special characters that are not walls or empty space
    private String placeCharacter(int x, int y, ArrayList<Bomb> bs, ArrayList es, Player p, Arena m, ArrayList ex) {
        if (checkIfBomb(x, y, bs)) {
            return "B";
        } else if (checkIfExplosion(x, y, ex)) {
            return "X";
        } else if (checkIfEnemy(x, y, es)) {
            return "E";
        } else if (checkIfPlayer(x, y, p)) {
            return "P";
        } else {
            return m.getArena().get(y).get(x);
        }
    }

    // EFFECTS: returns if the coordinates are a Player or not
    private boolean checkIfPlayer(int x, int y, Player p) {
        return x == p.getPosX() && y == p.getPosY();
    }

    // EFFECTS: returns if the coordinates are an Enemy or not
    private boolean checkIfEnemy(int x, int y, ArrayList<Enemy> e) {
        for (Bomber b : e) {
            if (x == b.getPosX() && y == b.getPosY()) {
                return true;
            }
        }

        return false;
    }

    // EFFECTS: returns if the coordinates are a Bomb or not
    private boolean checkIfBomb(int x, int y, ArrayList<Bomb> bs) {
        for (Bomb b : bs) {
            if (x == b.getPosX() && y == b.getPosY()) {
                return true;
            }
        }

        return false;
    }

    // EFFECTS: returns if the coordinates are an Explosion or not
    private boolean checkIfExplosion(int x, int y, ArrayList<Explosion> es) {
        for (Explosion e : es) {
            if (x == e.getPosX() && y == e.getPosY()) {
                return true;
            }
        }

        return false;
    }
}
