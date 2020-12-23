package ui;

import model.Constants;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Screen extends JFrame implements Constants {
    private JTextArea nameIn;

    private JLayeredPane mainScreen = new JLayeredPane();
    private JLayeredPane mapSelectScreen = new JLayeredPane();
    private JLayeredPane highScoreScreen = new JLayeredPane();
    private JLayeredPane gameScreen = new JLayeredPane();
    private JLayeredPane loseScreen = new JLayeredPane();
    private JLayeredPane winScreen = new JLayeredPane();
    private JLayeredPane saveSelectionScreen = new JLayeredPane();
    private JLayeredPane nameScreen = new JLayeredPane();
    private JLayeredPane playAgainScreen = new JLayeredPane();
    private Buttons buttons;
    private BomberManApp app;

    // MODIFIES: this
    // EFFECTS: creates a new Screen object and initializes values
    public Screen(BomberManApp app) {
        this.app = app;
        this.buttons = new Buttons(this.app);

        loadMainScreen();
        loadMapSelectScreen();
        loadHighScoreScreen();
        loadGameScreen();
        loadLoseScreen();
        loadWinScreen();
        loadSaveSelectionScreen();
        loadNameScreen();
        loadPlayAgainScreen();
    }

    // EFFECTS: resizes a given image to a given set of dimensions
    private ImageIcon resizeImageIcon(ImageIcon img, int w, int h) {
        return new ImageIcon(img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }

    // MODIFIES: this
    // EFFECTS: resizes an image from file and adds it to a given screen
    private void addToScreen(JLayeredPane s, String f, int x, int y, int w, int h, int i) {
        ImageIcon img = new ImageIcon(f);
        JLabel label = new JLabel(this.resizeImageIcon(img, w, h));

        label.setSize(w, h);
        label.setLocation(x, y);
        s.add(label, i);
    }

    // MODIFIES: this
    // EFFECTS: loads the mainScreen
    private void loadMainScreen() {
        this.mainScreen.add(this.buttons.getEasyButton(), 1);
        this.mainScreen.add(this.buttons.getHardButton(), 1);
        this.mainScreen.add(this.buttons.getHighScoreButton(), 2);
        this.mainScreen.add(this.buttons.getSoundButton(), 2);

        JLabel name = new JLabel("Alec Mai");

        name.setFont(new Font("LeagueSpartan-Bold.ttf", Font.PLAIN, 15));
        name.setSize(400,100);
        name.setLocation(10,S_HEIGHT - 90);
        name.setForeground(Color.BLACK);
        this.mainScreen.add(name, 4);

        addToScreen(this.mainScreen, "Images/TextImages/play.png", (S_WIDTH / 2) - 125,150, 250, 150, 9);
        addToScreen(this.mainScreen, "Images/TextImages/title.png", (S_WIDTH / 2) - 125, 0, 250, 167, 9);
        addToScreen(this.mainScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the mapSelectScreen
    private void loadMapSelectScreen() {
        this.mapSelectScreen.add(this.buttons.getCheckerMapButton());
        this.mapSelectScreen.add(this.buttons.getOpenMapButton());
        this.mapSelectScreen.add(this.buttons.getLinesMapButton());
        this.mapSelectScreen.add(this.buttons.getMapSelectBackButton());

        addToScreen(this.mapSelectScreen, "Images/TextImages/map.png", (S_WIDTH / 2) - 100, 20, 200, 100, 1);
        addToScreen(this.mapSelectScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the highScoreScreen
    public void loadHighScoreScreen() {
        this.highScoreScreen.add(this.buttons.getHighScoreBackButton(), 1);

        JLabel name = new JLabel("High-Scores:");
        JLabel easy = new JLabel("Easy:");
        JLabel hard = new JLabel("Hard:");

        name.setFont(new Font("LeagueSpartan-Bold.ttf", Font.BOLD, 40));
        name.setSize(400,100);
        name.setLocation((S_WIDTH / 2) - 145,20);
        name.setForeground(Color.BLACK);
        this.highScoreScreen.add(name, 4);
        easy.setFont(new Font("LeagueSpartan-Bold.ttf", Font.BOLD, 25));
        easy.setSize(400,100);
        easy.setLocation(120,100);
        easy.setForeground(Color.BLACK);
        this.highScoreScreen.add(easy, 4);
        hard.setFont(new Font("LeagueSpartan-Bold.ttf", Font.BOLD, 25));
        hard.setSize(400,100);
        hard.setLocation(S_WIDTH - 230,100);
        hard.setForeground(Color.BLACK);
        this.highScoreScreen.add(hard, 4);

        renderHighScores();
        addToScreen(this.highScoreScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: renders high-scores onto the highScoreScreen
    private void renderHighScores() {
        ArrayList<String> easyHighScore = this.app.getHighScore().getEasyHighScore();
        ArrayList<String> easyHighScoreName = this.app.getHighScore().getEasyHighScoreName();
        ArrayList<String> hardHighScore = this.app.getHighScore().getHardHighScore();
        ArrayList<String> hardHighScoreName = this.app.getHighScore().getHardHighScoreName();

        for (int i = 0; i < 5; i++) {
            JLabel e = new JLabel((i + 1) + ". " + easyHighScoreName.get(i) + " " + easyHighScore.get(i));
            JLabel h = new JLabel((i + 1) + ". " + hardHighScoreName.get(i) + " " + hardHighScore.get(i));

            e.setFont(new Font("LeagueSpartan-Bold.ttf", Font.BOLD, 15));
            e.setSize(100,20);
            e.setLocation(120,180 + 40 * i);
            e.setForeground(Color.BLACK);
            this.highScoreScreen.add(e);
            h.setFont(new Font("LeagueSpartan-Bold.ttf", Font.BOLD, 15));
            h.setSize(100,20);
            h.setLocation(S_WIDTH - 230,180 + 40 * i);
            h.setForeground(Color.BLACK);
            this.highScoreScreen.add(h);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the gameScreen
    private void loadGameScreen() {
        addToScreen(this.gameScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the loseScreen
    private void loadLoseScreen() {
        this.loseScreen.add(this.buttons.getLoseOkButton());

        addToScreen(this.loseScreen, "Images/TextImages/lose.png", (S_WIDTH / 2) - 120, 20, 240, 100, -1);
        addToScreen(this.loseScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the winScreen
    private void loadWinScreen() {
        this.winScreen.add(this.buttons.getWinOkButton());

        addToScreen(this.winScreen, "Images/TextImages/winner.png", (S_WIDTH / 2) - 169, 20, 338, 100, -1);
        addToScreen(this.winScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the saveSelectionScreen
    private void loadSaveSelectionScreen() {
        this.saveSelectionScreen.add(this.buttons.getSaveYesButton());
        this.saveSelectionScreen.add(this.buttons.getSaveNoButton());

        addToScreen(this.saveSelectionScreen, "Images/TextImages/save.png", (S_WIDTH / 2) - 137, 20, 274, 100, -1);
        addToScreen(this.saveSelectionScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the nameScreen
    private void loadNameScreen() {
        addToScreen(this.nameScreen, "Images/TextImages/name.png", (S_WIDTH / 2) - 145, 20, 290, 100, -1);

        nameIn = new JTextArea();

        nameIn.setSize(300, 50);
        nameIn.setLocation((S_WIDTH / 2) - 150, 200);
        nameIn.setFont(new Font("LeagueSpartan-Bold.ttf", Font.PLAIN, 30));

        this.nameScreen.add(nameIn);
        this.nameScreen.add(this.buttons.getNameOkButton());
        addToScreen(this.nameScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // MODIFIES: this
    // EFFECTS: loads the playAgainScreen
    private void loadPlayAgainScreen() {
        JLabel playAgain = new JLabel("Play Again?");

        playAgain.setFont(new Font("LeagueSpartan-Bold.ttf", Font.BOLD, 60));
        playAgain.setSize(400,100);
        playAgain.setLocation((S_WIDTH / 2) - 175,20);
        playAgain.setForeground(Color.BLACK);
        this.playAgainScreen.add(playAgain);

        this.playAgainScreen.add(this.buttons.getPlayAgainYesButton());
        this.playAgainScreen.add(this.buttons.getPlayAgainNoButton());
        addToScreen(this.playAgainScreen, "Images/MiscImages/background.png", 0, 0, S_WIDTH, S_HEIGHT, -1);
    }

    // EFFECTS: returns the mainScreen
    public JLayeredPane getMainScreen() {
        return mainScreen;
    }

    // EFFECTS: returns the mapSelectScreen
    public JLayeredPane getMapSelectScreen() {
        return mapSelectScreen;
    }

    // EFFECTS: returns the highScoreScreen
    public JLayeredPane getHighScoreScreen() {
        return highScoreScreen;
    }

    // EFFECTS: returns the gameScreen
    public JLayeredPane getGameScreen() {
        return gameScreen;
    }

    // EFFECTS: returns the loseScreen
    public JLayeredPane getLoseScreen() {
        return loseScreen;
    }

    // EFFECTS: returns the winScreen
    public JLayeredPane getWinScreen() {
        return winScreen;
    }

    // EFFECTS: returns the saveSelectionScreen
    public JLayeredPane getSaveSelectionScreen() {
        return saveSelectionScreen;
    }

    // EFFECTS: returns the nameScreen
    public JLayeredPane getNameScreen() {
        return nameScreen;
    }

    // EFFECTS: returns the playAgainScreen
    public JLayeredPane getPlayAgainScreen() {
        return playAgainScreen;
    }

    // EFFECTS: returns the buttons
    public Buttons getButtons() {
        return buttons;
    }

    // EFFECTS: returns the nameIn
    public JTextArea getNameIn() {
        return nameIn;
    }
}
