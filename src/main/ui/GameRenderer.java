package ui;

import model.BomberManGame;
import model.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

public class GameRenderer extends JPanel implements ActionListener, Constants {
    private ArrayList<String> drawnMap;
    private BomberManGame game;
    private Timer timer;

    private Image bombIcon;
    private Image easyEnemyIcon;
    private Image hardEnemyIcon;
    private Image playerIcon;
    private Image explosionIcon;
    private Image wallIcon;

    // MODIFIES: this
    // EFFECTS: loads a new GameRenderer
    public GameRenderer(BomberManGame game) {
        this.game = game;
        this.timer = new Timer(DELAY, this);
        this.drawnMap = this.game.getDrawnMap();

        loadIcons();
        addKeyListener(new IOAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
        this.timer.start();
    }

    // MODIFIES: this
    // EFFECTS: loads all icons used in game
    private void loadIcons() {
        try {
            this.bombIcon = ImageIO.read(new File("Images/BomberImages/bomb.png"));
            this.easyEnemyIcon = ImageIO.read(new File("Images/BomberImages/enemyeasy.png"));
            this.hardEnemyIcon = ImageIO.read(new File("Images/BomberImages/enemyhard.png"));
            this.playerIcon = ImageIO.read(new File("Images/BomberImages/player.png"));
            this.explosionIcon = ImageIO.read(new File("Images/BomberImages/explosion.png"));
            this.wallIcon = ImageIO.read(new File("Images/MiscImages/wall.png"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // MODIFIES: this, game
    // EFFECTS: handles input from the user and refreshes the game board
    @Override
    public void actionPerformed(ActionEvent e) {
        requestFocus();
        this.game.update();

        this.drawnMap = this.game.getDrawnMap();

        repaint();
    }

    private class IOAdapter extends KeyAdapter {
        // MODIFIES: player
        // EFFECTS: passes keystrokes to the Player
        @Override
        public void keyPressed(KeyEvent k) {
            try {
                game.getPlayer().keyPressed(k.getKeyCode());
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        // MODIFIES: player
        // EFFECTS: passes keystroke releases to the Player
        @Override
        public void keyReleased(KeyEvent k) {
            game.getPlayer().keyReleased();
        }
    }

    // MODIFIES: super
    // EFFECTS: renders graphics
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < G_HEIGHT; y++) {
            for (int x = 0; x < G_WIDTH; x++) {
                if (this.drawnMap.get(x + (y * G_WIDTH)).equalsIgnoreCase("p")) {
                    g.drawImage(this.playerIcon, x * 30, y * 30, this);
                } else if (this.drawnMap.get(x + (y * G_WIDTH)).equalsIgnoreCase("e")) {
                    if (this.game.getDifficulty().equalsIgnoreCase("hard")) {
                        g.drawImage(this.hardEnemyIcon, x * 30, y * 30, this);
                    } else {
                        g.drawImage(this.easyEnemyIcon, x * 30, y * 30, this);
                    }
                } else if (this.drawnMap.get(x + (y * G_WIDTH)).equalsIgnoreCase("x")) {
                    g.drawImage(this.explosionIcon, x * 30, y * 30, this);
                } else if (this.drawnMap.get(x + (y * G_WIDTH)).equalsIgnoreCase("w")) {
                    g.drawImage(this.wallIcon, x * 30, y * 30, this);
                } else if (this.drawnMap.get(x + (y * G_WIDTH)).equalsIgnoreCase("b")) {
                    g.drawImage(this.bombIcon, x * 30, y * 30, this);
                }
            }
        }
    }
}
