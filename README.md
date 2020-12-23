# CPSC 210 Final Project

## Super Bomber Man

This project aims to be a game in a similar style to the *Bomber Man* games.
The game should be able to be played by anyone who has two hands. I make
this project because I want to see how more efficient coding technique that
I have learned in class can be used to make a game and compare it to games
that I have previously made.

This game will include:
- Multiple game fields to play on
- Computer players to play against
- A battle mode
- A way to save, load, and view high-scores

## User Stories
- As a user, I want to be able to pick the difficulty of the computers I
play against
- As a user, I want to be able to place bombs to attack the computers
- As a user, I want to be able to pick which map I want to play on
- As a user, I want to be able to see graphics
- As a user, I want to be able to toggle on/off the annoying music

## Instructions for Grader
- You can generate the first required event by selecting a difficulty by clicking on a difficulty below the PLAY icon.
Then select a map on the MAP menu. Finally, by pressing the spacebar your character (blue square with a P) will place
a bomb that explodes after a time, multiple bombs can be placed on the map by both the player and the enemies.
- You can generate the second required event by having all enemies move into the blast of a bomb (winning the game) and
by moving the player into the blast of the bomb via the "w", "a", "s", and "d" keys (losing the game).
- You can locate my visual component by running the program (screen should load on start up) or You can trigger my audio
component by pressing the speaker button in the bottom right of the main menu (the menu with the Blasty Box logo).
- You can save the state of my application by eliminating all enemies in a shorter time than those times saved in the
high-scores.
- You can reload the state of my application by selecting the high-scores button on the main menu (the menu with the
Blasty Box logo). There is a bug where the game will not update the high-scores menu after a session, to fix you will
need to restart the program.

## Phase 4: Task 2
Implemented "Test and design a class that is robust." In the HighScore class writeToFile() throws a
FileNotFoundException in order to prevent an error from writing new files every instance.

## Phase 4: Task 3
- Changed how Screen class accesses high-scores, takes HighScore class from BomberManApp instead of creating a new
identical instance.
- Changed how in game rendering is handled, moved rendering to a separate class to better adhere to Single
Responsibility Principle.