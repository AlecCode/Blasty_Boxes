package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BomberManApp extends JFrame implements ActionListener, Constants {
    private boolean inGame = true;
    private boolean win = false;
    private boolean inMenu = true;
    private String difficulty = "easy";
    private String currentScreen;

    private Timer timer;
    private BomberManGame game;
    private GameRenderer gameRenderer;
    private HighScore highScore;
    private Audio backgroundMusic = new Audio("Sounds/background.wav");
    private Screen screen;
    private JPanel cards;
    private CardLayout cardLayout = new CardLayout();

    // EFFECTS: creates an instance of BomberManApp
    public static void main(String[] args) {
        new BomberManApp();
    }

    // MODIFIES: this
    // EFFECTS: creates a new BomberManApp object and loops until the user quits
    public BomberManApp() {
        super("Blasty Boxes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(S_WIDTH, S_HEIGHT);
        setLocationRelativeTo(null);

        this.timer = new javax.swing.Timer(DELAY, this);
        this.currentScreen = "mainScreen";
        this.highScore = new HighScore("HIGHSCORES");

        this.backgroundMusic.loop();
        loadScreens();
        requestFocus();
        setResizable(false);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a new Screen class and adds menus to the cardLayout
    private void loadScreens() {
        this.screen = new Screen(this);
        this.cards = new JPanel(this.cardLayout);

        this.cards.add(this.screen.getMainScreen(), "mainScreen");
        this.cards.add(this.screen.getMapSelectScreen(), "mapSelectScreen");
        this.cards.add(this.screen.getHighScoreScreen(), "highScoreScreen");
        this.cards.add(this.screen.getGameScreen(), "gameScreen");
        this.cards.add(this.screen.getLoseScreen(), "loseScreen");
        this.cards.add(this.screen.getWinScreen(), "winScreen");
        this.cards.add(this.screen.getSaveSelectionScreen(), "saveSelectionScreen");
        this.cards.add(this.screen.getNameScreen(), "nameScreen");
        this.cards.add(this.screen.getPlayAgainScreen(), "playAgainScreen");
        this.add(cards);
    }

    // MODIFIES: this
    // EFFECTS: creates an empty BomberManApp object
    public BomberManApp(boolean debug) {
        //this is for test purposes only
    }

    // MODIFIES: this
    // EFFECTS: handles inputs from buttons on mainScreen
    private void handleMainMenu(Object source) {
        if (source == this.screen.getButtons().getEasyButton()) {
            this.difficulty = "easy";
            this.currentScreen = "mapSelectScreen";
        } else if (source == this.screen.getButtons().getHardButton()) {
            this.difficulty = "hard";
            this.currentScreen = "mapSelectScreen";
        } else if (source == this.screen.getButtons().getSoundButton()) {
            this.backgroundMusic.togglePause();
        } else if (source == this.screen.getButtons().getHighScoreButton()) {
            this.currentScreen = "highScoreScreen";
        }
    }

    // MODIFIES: this
    // EFFECTS: handle inputs from buttons on the mapSelectScreen
    private void handleMapSelect(Object source) {
        JButton checker = this.screen.getButtons().getCheckerMapButton();
        JButton open = this.screen.getButtons().getOpenMapButton();
        JButton lines = this.screen.getButtons().getLinesMapButton();

        if (source == checker || source == open || source == lines) {
            if (source == checker) {
                this.game = new BomberManGame("checker", this.difficulty, this);
            } else if (source == open) {
                this.game = new BomberManGame("open", this.difficulty, this);
            } else {
                this.game = new BomberManGame("lines", this.difficulty, this);
            }

            this.currentScreen = "gameScreen";
            this.inGame = true;
            this.win = false;
            this.inMenu = false;
            this.gameRenderer = new GameRenderer(this.game);

            this.add(this.gameRenderer);
            this.cards.add(this.gameRenderer, "gameScreen");
            this.timer.start();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles input from both instances of back buttons
    private void handleBackButtons(Object source) {
        if (source == this.screen.getButtons().getMapSelectBackButton()) {
            this.currentScreen = "mainScreen";
        }
        if (source == this.screen.getButtons().getHighScoreBackButton()) {
            this.currentScreen = "mainScreen";
        }
    }

    // MODIFIES: this
    // EFFECTS: handles input at the end of the game
    private void handleEndGame() {
        if (!this.inGame && !this.inMenu) {
            if (this.win) {
                this.currentScreen = "winScreen";
            } else {
                this.currentScreen = "loseScreen";
            }

            this.inMenu = true;
            this.win = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles input from the menus after the game
    private void handleAfterGame(Object source) {
        if (source == this.screen.getButtons().getWinOkButton()) {
            if (this.highScore.highScoreEligible(this.game.getTurns(), this.difficulty)) {
                this.currentScreen = "saveSelectionScreen";
            } else {
                this.currentScreen = "playAgainScreen";
            }
        } else if (source == this.screen.getButtons().getLoseOkButton()) {
            this.currentScreen = "playAgainScreen";
        }

        if (source == this.screen.getButtons().getSaveYesButton()) {
            this.currentScreen = "nameScreen";
        } else if (source == this.screen.getButtons().getSaveNoButton()) {
            this.currentScreen = "playAgainScreen";
        }

        if (source == this.screen.getButtons().getPlayAgainYesButton()) {
            this.currentScreen = "mapSelectScreen";
        } else if (source == this.screen.getButtons().getPlayAgainNoButton()) {
            this.currentScreen = "mainScreen";
        }
    }

    // MODIFIES: this
    // EFFECTS: handles saving high-scores
    private void handleSaveScore(Object source) {
        if (source == this.screen.getButtons().getNameOkButton()) {
            this.highScore.insertHighScore(this.screen.getNameIn().getText(), this.game.getTurns(), this.difficulty);
            try {
                this.highScore.writeToFile();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            this.currentScreen = "playAgainScreen";
        }
    }

    // MODIFIES: this
    // EFFECTS: handles input from buttons on menus from Screen
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        handleMainMenu(source);
        handleMapSelect(source);
        handleBackButtons(source);
        handleEndGame();
        handleAfterGame(source);
        handleSaveScore(source);

        cardLayout.show(cards, this.currentScreen);
    }

    // MODIFIES: this
    // EFFECTS: sets inGame to inGame
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    // MODIFIES: this
    // EFFECTS: sets win to win
    public void setWin(boolean win) {
        this.win = win;
    }

    // EFFECTS: returns inGame
    public boolean isInGame() {
        return this.inGame;
    }

    // EFFECTS: returns isWin
    public boolean isWin() {
        return this.win;
    }

    // EFFECTS: returns highScore
    public HighScore getHighScore() {
        return this.highScore;
    }
}
