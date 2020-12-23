package model;

import java.util.ArrayList;

public class Arena implements Constants {
    private ArrayList<ArrayList<String>> arena;

    // MODIFIES: this
    // EFFECTS: creates a new arena object and forms the arena itself
    public Arena(String type) {
        this.arena = makeArena(type);
    }

    // EFFECTS: checks which arena type should be built and calls the appropriate builder method
    private ArrayList<ArrayList<String>> makeArena(String type) {
        ArrayList<ArrayList<String>> map = new ArrayList<>();

        if (type.equalsIgnoreCase("checker")) {
            map.addAll(makeCheckeredArena());
        }  else if (type.equalsIgnoreCase("lines")) {
            map.addAll(makeLinesArena());
        } else {
            map.addAll(makeOpenArena());
        }

        return closeArena(map);
    }

    // EFFECTS: builds the "checker" arena
    private ArrayList<ArrayList<String>> makeCheckeredArena() {
        ArrayList<ArrayList<String>> checkeredMap = new ArrayList<ArrayList<String>>();
        ArrayList<String> slice1 = new ArrayList<String>();
        ArrayList<String> slice2 = new ArrayList<String>();

        for (int i = 0; i < G_WIDTH; i++) {
            if ((i % 2) == 0) {
                slice2.add("W");
            } else {
                slice2.add(" ");
            }

            if (i == 0 || i == (G_WIDTH - 1)) {
                slice1.add("W");
            } else {
                slice1.add(" ");
            }
        }

        for (int i = 0; i < ((G_HEIGHT - 2) / 3); i++) {
            checkeredMap.add(slice1);
            checkeredMap.add(slice2);
            checkeredMap.add(slice1);
        }

        return checkeredMap;
    }

    // EFFECTS: builds the "open" arena
    private ArrayList<ArrayList<String>> makeOpenArena() {
        ArrayList<ArrayList<String>> openMap = new ArrayList<>();
        ArrayList<String> slice1 = new ArrayList<>();

        for (int i = 0; i < G_WIDTH; i++) {
            if (i == 0 || i == (G_WIDTH - 1)) {
                slice1.add("W");
            } else {
                slice1.add(" ");
            }
        }

        for (int i = 0; i < (G_HEIGHT - 2); i++) {
            openMap.add(slice1);
        }

        return openMap;
    }

    // EFFECTS: builds the "lines" arena
    private ArrayList<ArrayList<String>> makeLinesArena() {
        ArrayList<ArrayList<String>> linesMap = new ArrayList<>();
        ArrayList<String> slice1 = makeLinesArenaSlices(1);
        ArrayList<String> slice2 = makeLinesArenaSlices(2);
        ArrayList<String> slice3 = makeLinesArenaSlices(3);

        for (int i = 0; i < (G_HEIGHT - 2); i++) {
            if ((i < 2) || (12 < i)) {
                linesMap.add(slice1);
            } else if ((i < 6) || (8 < i)) {
                linesMap.add(slice2);
            } else {
                linesMap.add(slice3);
            }
        }

        return linesMap;
    }

    // EFFECTS: builds the each layer of the "lines" arena
    private ArrayList<String> makeLinesArenaSlices(int s) {
        ArrayList<String> slice = new ArrayList<>();

        for (int i = 0; i < G_WIDTH; i++) {
            if (s == 1 && (i == 0 || i == (G_WIDTH - 1))) {
                slice.add("W");
            } else if (s == 2 && (i == 4 || i == 10 || i == 16 || i == 0 || i == (G_WIDTH - 1))) {
                slice.add("W");
            } else if (s == 3 && (i == 10 || i == 0 || i == (G_WIDTH - 1))) {
                slice.add("W");
            } else {
                slice.add(" ");
            }
        }

        return slice;
    }

    // EFFECTS: puts a wall on the top and bottom of the arena
    private ArrayList<ArrayList<String>> closeArena(ArrayList<ArrayList<String>> m) {
        ArrayList<ArrayList<String>> map = new ArrayList<>();
        ArrayList<String> top = new ArrayList<>();
        ArrayList<String> bottom = new ArrayList<>();

        for (int i = 0; i < G_WIDTH; i++) {
            top.add("W");
            bottom.add("W");
        }

        map.add(top);
        map.addAll(m);
        map.add(bottom);

        return map;
    }

    // EFFECTS: returns the arena ArrayList
    public ArrayList<ArrayList<String>> getArena() {
        return this.arena;
    }

    // EFFECTS: returns the string at the index in the arena ArrayList
    public String getTile(int x, int y) {
        return this.arena.get(y).get(x);
    }
}
