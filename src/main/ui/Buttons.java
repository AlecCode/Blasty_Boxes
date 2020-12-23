package ui;

import model.Constants;

import javax.swing.*;
import java.awt.*;

public class Buttons implements Constants {
    private JButton easyButton;
    private JButton hardButton;
    private JButton highScoreButton;
    private JButton soundButton;

    private JButton checkerMapButton;
    private JButton openMapButton;
    private JButton linesMapButton;

    private JButton saveYesButton;
    private JButton saveNoButton;
    private JButton playAgainYesButton;
    private JButton playAgainNoButton;

    private JButton winOkButton;
    private JButton loseOkButton;
    private JButton nameOkButton;

    private JButton mapSelectBackButton;
    private JButton highScoreBackButton;

    private BomberManApp app;


    // MODIFIES: this
    // EFFECTS: loads all buttons used
    public Buttons(BomberManApp bma) {
        this.app = bma;

        loadEasyButton();
        loadHardButton();
        loadHighScoreButton();
        loadSoundButton();

        loadCheckerMapButton();
        loadOpenMapButton();
        loadLinesMapButton();

        loadSaveYesButton();
        loadSaveNoButton();
        loadPlayAgainYesButton();
        loadPlayAgainNoButton();

        loadWinOkButton();
        loadLoseOkButton();
        loadNameOkButton();

        loadMapSelectBackButton();
        loadHighScoreBackButton();
    }

    // EFFECTS: resizes a given image to the given dimensions
    private ImageIcon resizeImg(String f, int w, int h) {
        ImageIcon img = new ImageIcon(f);

        return new ImageIcon(img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }

    // EFFECTS: loads the easy button
    private void loadEasyButton() {
        this.easyButton = new JButton(resizeImg("Images/TextImages/easy.png", 50, 30));

        this.easyButton.addActionListener(this.app);
        this.easyButton.setSize(50,30);
        this.easyButton.setLocation((S_WIDTH / 2) - 110,250);
        this.easyButton.setContentAreaFilled(false);
        this.easyButton.setFocusPainted(false);
        this.easyButton.setBorderPainted(false);
    }

    // EFFECTS: loads the hard button
    private void loadHardButton() {
        this.hardButton = new JButton(resizeImg("Images/TextImages/hard.png", 50, 30));

        this.hardButton.addActionListener(this.app);
        this.hardButton.setSize(50,30);
        this.hardButton.setLocation((S_WIDTH / 2) - 60,250);
        this.hardButton.setContentAreaFilled(false);
        this.hardButton.setFocusPainted(false);
        this.hardButton.setBorderPainted(false);
    }

    // EFFECTS: loads the high-score button
    private void loadHighScoreButton() {
        this.highScoreButton = new JButton(resizeImg("Images/TextImages/highscores.png", 250, 70));

        this.highScoreButton.addActionListener(this.app);
        this.highScoreButton.setSize(250,100);
        this.highScoreButton.setLocation((S_WIDTH / 2) - 125,290);
        this.highScoreButton.setContentAreaFilled(false);
        this.highScoreButton.setFocusPainted(false);
        this.highScoreButton.setBorderPainted(false);
    }

    // EFFECTS: loads the sound button
    private void loadSoundButton() {
        this.soundButton = new JButton(resizeImg("Images/MiscImages/sound.png", 40, 40));

        this.soundButton.addActionListener(this.app);
        this.soundButton.setSize(40, 40);
        this.soundButton.setLocation(S_WIDTH - 50, S_HEIGHT - 70);
        this.soundButton.setContentAreaFilled(false);
        this.soundButton.setFocusPainted(false);
        this.soundButton.setBorderPainted(false);
    }

    // EFFECTS: loads the checker map button
    private void loadCheckerMapButton() {
        this.checkerMapButton = new JButton(resizeImg("Images/TextImages/checker.png", 120, 40));

        this.checkerMapButton.addActionListener(this.app);
        this.checkerMapButton.setSize(120,40);
        this.checkerMapButton.setLocation(105,300);
        this.checkerMapButton.setContentAreaFilled(false);
        this.checkerMapButton.setFocusPainted(false);
        this.checkerMapButton.setBorderPainted(false);
    }

    // EFFECTS: loads the open map button
    private void loadOpenMapButton() {
        this.openMapButton = new JButton(resizeImg("Images/TextImages/open.png", 100, 50));

        this.openMapButton.addActionListener(this.app);
        this.openMapButton.setSize(100, 50);
        this.openMapButton.setLocation((S_WIDTH / 2) - 50, 300);
        this.openMapButton.setContentAreaFilled(false);
        this.openMapButton.setFocusPainted(false);
        this.openMapButton.setBorderPainted(false);
    }

    // EFFECTS: loads the lines map button
    private void loadLinesMapButton() {
        this.linesMapButton = new JButton(resizeImg("Images/TextImages/lines.png", 100, 50));

        this.linesMapButton.addActionListener(this.app);
        this.linesMapButton.setSize(100, 35);
        this.linesMapButton.setLocation((S_WIDTH / 2) + 100, 305);
        this.linesMapButton.setContentAreaFilled(false);
        this.linesMapButton.setFocusPainted(false);
        this.linesMapButton.setBorderPainted(false);
    }

    // EFFECTS: loads the yes button on the saveScreen
    private void loadSaveYesButton() {
        this.saveYesButton = new JButton(resizeImg("Images/TextImages/yes.png", 105, 55));

        this.saveYesButton.addActionListener(this.app);
        this.saveYesButton.setSize(105, 55);
        this.saveYesButton.setLocation((S_WIDTH / 2) - 112,300);
        this.saveYesButton.setContentAreaFilled(false);
        this.saveYesButton.setFocusPainted(false);
        this.saveYesButton.setBorderPainted(false);
    }

    // EFFECTS: loads the no button on the saveScreen
    private void loadSaveNoButton() {
        this.saveNoButton = new JButton(resizeImg("Images/TextImages/no.png", 83, 55));

        this.saveNoButton.addActionListener(this.app);
        this.saveNoButton.setSize(83, 55);
        this.saveNoButton.setLocation((S_WIDTH / 2) + 12,301);
        this.saveNoButton.setContentAreaFilled(false);
        this.saveNoButton.setFocusPainted(false);
        this.saveNoButton.setBorderPainted(false);
    }

    // EFFECTS: loads the yes button on the playAgainScreen
    private void loadPlayAgainYesButton() {
        this.playAgainYesButton = new JButton(resizeImg("Images/TextImages/yes.png", 105, 55));

        this.playAgainYesButton.addActionListener(this.app);
        this.playAgainYesButton.setSize(105, 55);
        this.playAgainYesButton.setLocation((S_WIDTH / 2) - 112,300);
        this.playAgainYesButton.setContentAreaFilled(false);
        this.playAgainYesButton.setFocusPainted(false);
        this.playAgainYesButton.setBorderPainted(false);
    }

    // EFFECTS: loads the no button on the playAgainScreen
    private void loadPlayAgainNoButton() {
        this.playAgainNoButton = new JButton(resizeImg("Images/TextImages/no.png", 83, 55));

        this.playAgainNoButton.addActionListener(this.app);
        this.playAgainNoButton.setSize(83, 55);
        this.playAgainNoButton.setLocation((S_WIDTH / 2) + 12,301);
        this.playAgainNoButton.setContentAreaFilled(false);
        this.playAgainNoButton.setFocusPainted(false);
        this.playAgainNoButton.setBorderPainted(false);
    }

    // EFFECTS: loads the ok button on the winScreen
    private void loadWinOkButton() {
        this.winOkButton = new JButton(resizeImg("Images/TextImages/ok.png", 100, 55));

        this.winOkButton.addActionListener(this.app);
        this.winOkButton.setSize(100,55);
        this.winOkButton.setLocation((S_WIDTH / 2) - 50,300);
        this.winOkButton.setContentAreaFilled(false);
        this.winOkButton.setFocusPainted(false);
        this.winOkButton.setBorderPainted(false);
    }

    // EFFECTS: loads the ok button on the nameScreen
    private void loadNameOkButton() {
        this.nameOkButton = new JButton(resizeImg("Images/TextImages/ok.png", 100, 55));

        this.nameOkButton.addActionListener(this.app);
        this.nameOkButton.setSize(100,55);
        this.nameOkButton.setLocation((S_WIDTH / 2) - 50,300);
        this.nameOkButton.setContentAreaFilled(false);
        this.nameOkButton.setFocusPainted(false);
        this.nameOkButton.setBorderPainted(false);
    }

    // EFFECTS: loads the ok button on the loseScreen
    private void loadLoseOkButton() {
        this.loseOkButton = new JButton(resizeImg("Images/TextImages/ok.png", 100, 55));

        this.loseOkButton.addActionListener(this.app);
        this.loseOkButton.setSize(100,55);
        this.loseOkButton.setLocation((S_WIDTH / 2) - 50,300);
        this.loseOkButton.setContentAreaFilled(false);
        this.loseOkButton.setFocusPainted(false);
        this.loseOkButton.setBorderPainted(false);
    }

    // EFFECTS: loads the back button on the mapSelectScreen
    private void loadMapSelectBackButton() {
        this.mapSelectBackButton = new JButton(resizeImg("Images/MiscImages/back.png", 35, 35));

        this.mapSelectBackButton.addActionListener(this.app);
        this.mapSelectBackButton.setSize(50,50);
        this.mapSelectBackButton.setLocation(10,10);
        this.mapSelectBackButton.setContentAreaFilled(false);
        this.mapSelectBackButton.setFocusPainted(false);
        this.mapSelectBackButton.setBorderPainted(false);
    }

    // EFFECTS: loads the back button on the highScoreScreen
    private void loadHighScoreBackButton() {
        this.highScoreBackButton = new JButton(resizeImg("Images/MiscImages/back.png", 35, 35));

        this.highScoreBackButton.addActionListener(this.app);
        this.highScoreBackButton.setSize(50,50);
        this.highScoreBackButton.setLocation(10,10);
        this.highScoreBackButton.setContentAreaFilled(false);
        this.highScoreBackButton.setFocusPainted(false);
        this.highScoreBackButton.setBorderPainted(false);
    }

    // EFFECTS: returns the easyButton
    public JButton getEasyButton() {
        return this.easyButton;
    }

    // EFFECTS: returns the hardButton
    public JButton getHardButton() {
        return this.hardButton;
    }

    // EFFECTS: returns the highScoreButton
    public JButton getHighScoreButton() {
        return this.highScoreButton;
    }

    // EFFECTS: returns the soundButton
    public JButton getSoundButton() {
        return this.soundButton;
    }

    // EFFECTS: returns the checkerMapButton
    public JButton getCheckerMapButton() {
        return this.checkerMapButton;
    }

    // EFFECTS: returns the openMapButton
    public JButton getOpenMapButton() {
        return this.openMapButton;
    }

    // EFFECTS: returns the linesMapButton
    public JButton getLinesMapButton() {
        return this.linesMapButton;
    }

    // EFFECTS: returns the saveYesButton
    public JButton getSaveYesButton() {
        return this.saveYesButton;
    }

    // EFFECTS: returns the playAgainYesButton
    public JButton getPlayAgainYesButton() {
        return this.playAgainYesButton;
    }

    // EFFECTS: returns the playAgainNoButton
    public JButton getPlayAgainNoButton() {
        return this.playAgainNoButton;
    }

    // EFFECTS: returns the saveNoButton
    public JButton getSaveNoButton() {
        return this.saveNoButton;
    }

    // EFFECTS: returns the winOkButton
    public JButton getWinOkButton() {
        return this.winOkButton;
    }

    // EFFECTS: returns the loseOkButton
    public JButton getLoseOkButton() {
        return this.loseOkButton;
    }

    // EFFECTS: returns the nameOkButton
    public JButton getNameOkButton() {
        return this.nameOkButton;
    }

    // EFFECTS: returns the mapSelectBackButton
    public JButton getMapSelectBackButton() {
        return this.mapSelectBackButton;
    }

    // EFFECTS: returns the highScoreBackButton
    public JButton getHighScoreBackButton() {
        return this.highScoreBackButton;
    }
}