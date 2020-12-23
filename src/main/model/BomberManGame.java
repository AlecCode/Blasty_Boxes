package model;

import ui.BomberManApp;
import ui.Drawer;
import java.util.ArrayList;

public class BomberManGame implements Constants {
    private int turns;
    private boolean stop = false;
    private String difficulty;
    private Player player;
    private Arena arena;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private ArrayList<String> drawnMap;
    private Drawer draw;
    private BomberManApp app;

    // MODIFIES: this
    // EFFECTS: creates a new BomberManGame object and initializes values
    public BomberManGame(String a, String d, BomberManApp app) {
        this.difficulty = d;
        this.app = app;
        this.turns = 0;

        gameSetUp(a, d);
    }

    // MODIFIES: this
    // EFFECTS: takes an arena and initializes the rest of BomberManGame's values
    private void gameSetUp(String a, String d) {
        this.arena = new Arena(a);
        this.player = new Player(this);
        this.draw = new Drawer();
        this.enemies = new ArrayList<>();
        this.explosions = new ArrayList<>();
        this.drawnMap = draw.drawMap(this.player, this.arena, this.enemies, this.explosions);

        makeEnemies(d);
    }

    // MODIFIES: this
    // EFFECTS: creates 3 enemies of difficulty "d" and places them in the corners of the map
    private void makeEnemies(String d) {
        this.enemies = new ArrayList<>();
        Enemy e1 = new Enemy(this, d);
        Enemy e2 = new Enemy(this, d);
        Enemy e3 = new Enemy(this, d);

        e1.setPosX(1);
        e1.setPosY(G_HEIGHT - 2);
        e2.setPosX(G_WIDTH - 2);
        e2.setPosY(1);
        e3.setPosX(G_WIDTH - 2);
        e3.setPosY(G_HEIGHT - 2);

        this.enemies.add(e1);
        this.enemies.add(e2);
        this.enemies.add(e3);
    }

    // MODIFIES: this
    // EFFECTS: makes changes to all enemies, bombs, explosions
    public void update() {
        if (!this.stop) {
            this.drawnMap = draw.drawMap(this.player, this.arena, this.enemies, this.explosions);
            this.turns += 1;

            moveEnemies(this.player);
            tickBombs();
            explodeBombs();
            tickExplosions();
            checkCollisions();
        }
    }

    // MODIFIES: this
    // EFFECTS: makes each enemy execute a move
    private void moveEnemies(Player p) {
        for (Enemy e : this.enemies) {
            e.move(p);
        }
    }

    // MODIFIES: this
    // EFFECTS: ticks all bombs associated to the player and each enemy
    private void tickBombs() {
        for (Bomb b : this.player.getBombs()) {
            b.tick();
        }
        for (Enemy e : this.enemies) {
            for (Bomb b : e.getBombs()) {
                b.tick();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: checks which bombs explode, calls for the creation of an explosion, call remove bomb from player/enemy
    private void explodeBombs() {
        ArrayList<Bomb> remove = new ArrayList<Bomb>();

        for (Bomb b : this.player.getBombs()) {
            if (b.getTimer() <= 0) {
                remove.add(b);
                this.explosions.addAll(b.explode(this.arena));
            }
        }
        removeBombs(this.player, remove);
        for (Enemy e : this.enemies) {
            remove = new ArrayList<>();

            for (Bomb b : e.getBombs()) {
                if (b.getTimer() <= 0) {
                    remove.add(b);
                    this.explosions.addAll(b.explode(this.arena));
                }
            }
            removeBombs(e, remove);
        }
    }

    //MODIFIES: this
    // EFFECTS: removes all Bombs in bombs from the Player's ArrayList of Bombs
    private void removeBombs(Bomber b, ArrayList<Bomb> bombs) {
        for (Bomb r : bombs) {
            b.removeBomb(r);
        }
    }

    // EFFECTS: decrements each explosions timer by one
    private void tickExplosions() {
        ArrayList<Explosion> remove = new ArrayList<>();

        for (Explosion e : this.explosions) {
            e.tick();
            if (e.getTimer() <= 0) {
                remove.add(e);
            }
        }

        removeExplosions(remove);
    }

    // MODIFIES: this
    // EFFECTS: removes explosions who's timer has reached 0
    private void removeExplosions(ArrayList<Explosion> explosions) {
        for (Explosion e : explosions) {
            this.explosions.remove(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all Enemy in enemies from this.enemies
    public void removeEnemies(ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            this.enemies.remove(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if a Bomber is in an explosion, if so the Bomber "dies"
    private void checkCollisions() {
        ArrayList<Enemy> remove = new ArrayList<>();

        for (Explosion x : this.explosions) {
            if (x.getPosX() == this.player.getPosX() && x.getPosY() == this.player.getPosY()) {
                this.app.setInGame(false);
                this.app.setWin(false);
            }

            for (Enemy e : this.enemies) {
                if (x.getPosX() == e.getPosX() && x.getPosY() == e.getPosY()) {
                    remove.add(e);
                }
            }
        }

        removeEnemies(remove);

        if (this.enemies.isEmpty()) {
            this.app.setWin(true);
            this.app.setInGame(false);

            this.stop = true;
        }
    }

    // EFFECTS: returns the Player
    public Player getPlayer() {
        return this.player;
    }

    // EFFECTS: returns the Arena
    public Arena getArena() {
        return this.arena;
    }

    // EFFECTS: returns the list of Enemy's
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    // EFFECTS: returns the list of Explosions
    public ArrayList<Explosion> getExplosions() {
        return this.explosions;
    }

    // EFFECTS: returns the drawnMap
    public ArrayList<String> getDrawnMap() {
        return this.drawnMap;
    }

    // EFFECTS: returns the difficulty
    public String getDifficulty() {
        return this.difficulty;
    }

    // EFFECTS: returns the number of turns
    public int getTurns() {
        return this.turns;
    }
}
