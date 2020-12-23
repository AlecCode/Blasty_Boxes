package model;

import java.io.*;
import java.util.ArrayList;

public class HighScore {
    private ArrayList<String> easyHighScore = new ArrayList<>();
    private ArrayList<String> easyHighScoreName = new ArrayList<>();
    private ArrayList<String> hardHighScore = new ArrayList<>();
    private ArrayList<String> hardHighScoreName = new ArrayList<>();
    private String filePath;

    // MODIFIES: this
    // EFFECTS: creates a new HighScore object and loads high-scores from a file
    // REQUIRES: fileName must be path from content root
    public HighScore(String fileName) {
        this.filePath = fileName;

        loadFromFile();
    }

    // MODIFIES: this
    // EFFECTS: loads scores from file and stores the values as ArrayLists
    private void loadFromFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(this.filePath));
            String line;

            for (int i = 0; i < 10; i++) {
                line = in.readLine();
                String[] parts = line.split(" ");
                if (i < 5) {
                    this.easyHighScoreName.add(parts[0]);
                    this.easyHighScore.add(parts[1]);
                } else {
                    this.hardHighScoreName.add(parts[0]);
                    this.hardHighScore.add(parts[1]);
                }
            }
        } catch (IOException e) {
            for (int i = 0; i < 5; i++) {
                this.easyHighScoreName.add("?????");
                this.easyHighScore.add("0");
                this.hardHighScoreName.add("?????");
                this.hardHighScore.add("0");
            }
        }
    }

    // EFFECTS: returns whether or not the given score is good enough to be put on the leaderboard
    public boolean highScoreEligible(int score, String diff) {
        int lastEasyScore = Integer.parseInt(this.easyHighScore.get(4));
        int lastHardScore = Integer.parseInt(this.hardHighScore.get(4));

        if (diff.equalsIgnoreCase("easy")) {
            return score < lastEasyScore || lastEasyScore == 0;
        } else {
            return score < lastHardScore || lastHardScore == 0;
        }
    }

    // EFFECTS: writes all high-scores to a file
    public void writeToFile() throws FileNotFoundException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);

            for (int i = 0; i < 5; i++) {
                fileWriter.write(this.easyHighScoreName.get(i) + " " + this.easyHighScore.get(i) + "\n");
            }
            for (int i = 0; i < 5; i++) {
                fileWriter.write(this.hardHighScoreName.get(i) + " " + this.hardHighScore.get(i) + "\n");
            }

            fileWriter.close();
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    // MODIFIES: this
    // EFFECTS: takes a score and its associated name and inserts both into correct slots in their respective ArrayLists
    public void insertHighScore(String name, int score, String diff) {
        for (int i = 0; i < 5; i++) {
            int currentEasyScore = Integer.parseInt(this.easyHighScore.get(i));
            int currentHardScore = Integer.parseInt(this.hardHighScore.get(i));

            if (diff.equalsIgnoreCase("easy")) {
                if (score < currentEasyScore || currentEasyScore == 0) {
                    this.easyHighScore = insert(this.easyHighScore, i, Integer.toString(score));
                    this.easyHighScoreName = insert(this.easyHighScoreName, i, name);

                    break;
                }
            } else {
                if (score < currentHardScore || currentHardScore == 0) {
                    this.hardHighScore = insert(this.hardHighScore, i, Integer.toString(score));
                    this.hardHighScoreName = insert(this.hardHighScoreName, i, name);

                    break;
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS; takes an ArrayList, index, and item and inserts the item in the ArrayList at the given index
    public ArrayList<String> insert(ArrayList<String> a, int i, String item) {
        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.addAll(a.subList(0, i));
        returnArray.add(item);
        returnArray.addAll(a.subList(i, a.size() - 1));
        return returnArray;
    }

    // EFFECTS: returns easyHighScore
    public ArrayList<String> getEasyHighScore() {
        return this.easyHighScore;
    }

    // EFFECTS: returns easyHighScoreName
    public ArrayList<String> getEasyHighScoreName() {
        return this.easyHighScoreName;
    }

    // EFFECTS: returns hardHighScore
    public ArrayList<String> getHardHighScore() {
        return this.hardHighScore;
    }

    // EFFECTS: returns hardHighScoreName
    public ArrayList<String> getHardHighScoreName() {
        return this.hardHighScoreName;
    }
}
