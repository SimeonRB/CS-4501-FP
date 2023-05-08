package com.example.shannon_switching_game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class ShannonController {
    public Implementation playGame;
    @FXML
    private Label welcomeText;
    @FXML
    private Label startPlayer;
    int numPoints = 0;
    @FXML
    Button button1 = new Button();
    @FXML
    Button button2= new Button();
    @FXML
    Button button3= new Button();
    @FXML
    Button button4= new Button();
    @FXML
    Button startButton= new Button();
    @FXML
    Line line1to2 = new Line();
    @FXML
    Line line1to3 = new Line();
    @FXML
    Line line1to4 = new Line();
    @FXML
    Line line2to3 = new Line();
    @FXML
    Line line2to4 = new Line();
    @FXML
    Line line3to4 = new Line();
    int numPlayerLines;
    int numCutLines;
    int lastButtonPressed = 0;
    public ShannonController (){
        playGame = new Implementation();
    }
    @FXML
    protected void onStartButtonClick() {
        playGame = new Implementation();
        playGame.setNumPoints(4);
        numPlayerLines = playGame.getNumPlayerLines();
        numCutLines = playGame.getNumCutLines();
        numPoints = playGame.getNumPoints();
        playGame.setSpecial(numPoints);
        startPlayer.setText("First Move: " + playGame.getPlayer());
        int r2 = playGame.getSpecial().get(1);
        for(int j = 1; j <= numPoints; j++) {
            if (j == 1) {
                button1.setText("s");
                button1.setStyle("-fx-background-color: #808080; ");
            }
            if (j == 2) {
                if (j == r2) {
                    button2.setText("t");
                } else {
                    button2.setText(Integer.toString(j));
                }
                button2.setStyle("-fx-background-color: #808080; ");
            }
            if (j == 3) {
                if (j == r2) {
                    button3.setText("t");
                } else {
                    button3.setText(Integer.toString(j));
                }
                button3.setStyle("-fx-background-color: #808080; ");
            }
            if (j == 4) {
                if (j == r2) {
                    button4.setText("t");
                } else {
                    button4.setText(Integer.toString(j));
                }
                button4.setStyle("-fx-background-color: #808080; ");
            }
            line1to2.setStartX(button1.getLayoutX());
            line1to2.setStartY(button1.getLayoutY());
            line1to2.setEndX(button2.getLayoutX());
            line1to2.setEndY(button2.getLayoutY());
            line1to2.setStyle("-fx-stroke: white; ");
            line1to3.setStartX(button1.getLayoutX());
            line1to3.setStartY(button1.getLayoutY());
            line1to3.setEndX(button3.getLayoutX());
            line1to3.setEndY(button3.getLayoutY());
            line1to3.setStyle("-fx-stroke: white; ");
            line1to4.setStartX(button1.getLayoutX());
            line1to4.setStartY(button1.getLayoutY());
            line1to4.setEndX(button4.getLayoutX());
            line1to4.setEndY(button4.getLayoutY());
            line1to4.setStyle("-fx-stroke: white; ");
            line2to3.setStartX(button2.getLayoutX());
            line2to3.setStartY(button2.getLayoutY());
            line2to3.setEndX(button3.getLayoutX());
            line2to3.setEndY(button3.getLayoutY());
            line2to3.setStyle("-fx-stroke: white; ");
            line2to4.setStartX(button2.getLayoutX());
            line2to4.setStartY(button2.getLayoutY());
            line2to4.setEndX(button4.getLayoutX());
            line2to4.setEndY(button4.getLayoutY());
            line2to4.setStyle("-fx-stroke: white; ");
            line3to4.setStartX(button3.getLayoutX());
            line3to4.setStartY(button3.getLayoutY());
            line3to4.setEndX(button4.getLayoutX());
            line3to4.setEndY(button4.getLayoutY());
            line3to4.setStyle("-fx-stroke: white; ");
        }
        startButton.setText("Restart");
        welcomeText.setText("Shannon Switching Game");
    }
    @FXML
    protected void onFirstButtonClick() {
        if (!playGame.isOver()) {
            int buttonNumber = 1;
                if (playGame.getPlayer().equals("Short")) {
                    button1.setStyle("-fx-background-color: #0000FF; ");
                    if (lastButtonPressed != 0 && lastButtonPressed != 1) {
                        playGame.getPlayerLines().add(new ArrayList<>());
                        playGame.getPlayerLines().get(numPlayerLines).add(lastButtonPressed);
                        playGame.getPlayerLines().get(numPlayerLines).add(buttonNumber);
                        if(lastButtonPressed == 2) {
                            line1to2.setStyle("-fx-stroke: #0000FF; ");
                            button2.setStyle("-fx-background-color: #808080; ");

                        } else if(lastButtonPressed == 3) {
                            line1to3.setStyle("-fx-stroke: #0000FF; ");
                            button3.setStyle("-fx-background-color: #808080; ");
                        } else {
                            line1to4.setStyle("-fx-stroke: #0000FF; ");
                            button4.setStyle("-fx-background-color: #808080; ");
                        }
                        button1.setStyle("-fx-background-color: #808080; ");
                        playGame.setPlayer("Cut");
                        startPlayer.setText("Next Move: " + playGame.getPlayer());
                        lastButtonPressed = 0;
                        numPlayerLines = playGame.getNumPlayerLines();
                    } else {
                        lastButtonPressed = 1;
                    }
                } else if (playGame.getPlayer().equals("Cut")) {
                   button1.setStyle("-fx-background-color: #FF0000; ");
                    if (lastButtonPressed != 0 && lastButtonPressed != 1) {
                        playGame.getCutLines().add(new ArrayList<>());
                        playGame.getCutLines().get(numCutLines).add(lastButtonPressed);
                        playGame.getCutLines().get(numCutLines).add(buttonNumber);
                        if(lastButtonPressed == 2) {
                            line1to2.setStyle("-fx-stroke: #FF0000; ");
                            button2.setStyle("-fx-background-color: #808080; ");

                        } else if(lastButtonPressed == 3) {
                            line1to3.setStyle("-fx-stroke: #FF0000; ");
                            button3.setStyle("-fx-background-color: #808080; ");
                        } else {
                            line1to4.setStyle("-fx-stroke: #FF0000; ");
                            button4.setStyle("-fx-background-color: #808080; ");
                        }
                        button1.setStyle("-fx-background-color: #808080; ");
                        playGame.setPlayer("Short");
                        numCutLines = playGame.getNumCutLines();
                        startPlayer.setText("Next Move: " + playGame.getPlayer());
                        lastButtonPressed = 0;
                    } else {
                        lastButtonPressed = 1;
                    }
                }
            }
        gameStatus();
    }
    @FXML
    protected void onSecondButtonClick() {
        if (!playGame.isOver()) {
            int buttonNumber = 2;
            if (playGame.getPlayer().equals("Short")) {
                button2.setStyle("-fx-background-color: #0000FF; ");
                if (lastButtonPressed != 0 && lastButtonPressed != 2) {
                    playGame.getPlayerLines().add(new ArrayList<>());
                    playGame.getPlayerLines().get(numPlayerLines).add(lastButtonPressed);
                    playGame.getPlayerLines().get(numPlayerLines).add(buttonNumber);
                    if(lastButtonPressed == 1) {
                        line1to2.setStyle("-fx-stroke: #0000FF; ");
                        button1.setStyle("-fx-background-color: #808080; ");

                    } else if(lastButtonPressed == 3) {
                        line2to3.setStyle("-fx-stroke: #0000FF; ");
                        button3.setStyle("-fx-background-color: #808080; ");
                    } else {
                        line2to4.setStyle("-fx-stroke: #0000FF; ");
                        button4.setStyle("-fx-background-color: #808080; ");
                    }
                    button2.setStyle("-fx-background-color: #808080; ");
                    playGame.setPlayer("Cut");
                    lastButtonPressed = 0;
                    numPlayerLines = playGame.getNumPlayerLines();
                    startPlayer.setText("Next Move: " + playGame.getPlayer());
                } else {
                    lastButtonPressed = 2;
                }
            } else if (playGame.getPlayer().equals("Cut")) {
                button2.setStyle("-fx-background-color: #FF0000; ");
                if (lastButtonPressed != 0 && lastButtonPressed != 2) {
                    playGame.getCutLines().add(new ArrayList<>());
                    playGame.getCutLines().get(numCutLines).add(lastButtonPressed);
                    playGame.getCutLines().get(numCutLines).add(buttonNumber);
                    if(lastButtonPressed == 1) {
                        line1to2.setStyle("-fx-stroke: #FF0000; ");
                        button1.setStyle("-fx-background-color: #808080; ");

                    } else if(lastButtonPressed == 3) {
                        line2to3.setStyle("-fx-stroke: #FF0000; ");
                        button3.setStyle("-fx-background-color: #808080; ");
                    } else {
                        line2to4.setStyle("-fx-stroke: #FF0000; ");
                        button4.setStyle("-fx-background-color: #808080; ");
                    }
                    button2.setStyle("-fx-background-color: #808080; ");
                    playGame.setPlayer("Short");
                    numCutLines = playGame.getNumCutLines();
                    lastButtonPressed = 0;
                    startPlayer.setText("Next Move: " + playGame.getPlayer());
                } else {
                    lastButtonPressed = 2;
                }
            }
            gameStatus();
        }
    }
    @FXML
    protected void onThirdButtonClick() {
        if (!playGame.isOver()) {
            int buttonNumber = 3;
            if (playGame.getPlayer().equals("Short")) {
                button3.setStyle("-fx-background-color: #0000FF; ");
                if (lastButtonPressed != 0 && lastButtonPressed != 3) {
                    playGame.getPlayerLines().add(new ArrayList<>());
                    playGame.getPlayerLines().get(numPlayerLines).add(lastButtonPressed);
                    playGame.getPlayerLines().get(numPlayerLines).add(buttonNumber);
                    if(lastButtonPressed == 1) {
                        line1to3.setStyle("-fx-stroke: #0000FF; ");
                        button1.setStyle("-fx-background-color: #808080; ");

                    } else if(lastButtonPressed == 2) {
                        line2to3.setStyle("-fx-stroke: #0000FF; ");
                        button2.setStyle("-fx-background-color: #808080; ");
                    } else {
                        line3to4.setStyle("-fx-stroke: #0000FF; ");
                        button4.setStyle("-fx-background-color: #808080; ");
                    }
                    button3.setStyle("-fx-background-color: #808080; ");
                    playGame.setPlayer("Cut");
                    numPlayerLines = playGame.getNumPlayerLines();
                    startPlayer.setText("Next Move: " + playGame.getPlayer());
                    lastButtonPressed = 0;
                } else {
                    lastButtonPressed = 3;
                }
            } else if (playGame.getPlayer().equals("Cut")) {
                button3.setStyle("-fx-background-color: #FF0000; ");
                if (lastButtonPressed != 0 && lastButtonPressed != 3) {
                    playGame.getCutLines().add(new ArrayList<>());
                    playGame.getCutLines().get(numCutLines).add(lastButtonPressed);
                    playGame.getCutLines().get(numCutLines).add(buttonNumber);
                    if(lastButtonPressed == 1) {
                        line1to3.setStyle("-fx-stroke: #FF0000; ");
                        button1.setStyle("-fx-background-color: #808080; ");

                    } else if(lastButtonPressed == 2) {
                        line2to3.setStyle("-fx-stroke: #FF0000; ");
                        button2.setStyle("-fx-background-color: #808080; ");
                    } else {
                        line3to4.setStyle("-fx-stroke: #FF0000; ");
                        button4.setStyle("-fx-background-color: #808080; ");
                    }
                    button3.setStyle("-fx-background-color: #808080; ");
                    playGame.setPlayer("Short");
                    startPlayer.setText("Next Move: " + playGame.getPlayer());
                    numCutLines = playGame.getNumCutLines();
                    lastButtonPressed = 0;
                } else {
                    lastButtonPressed = 3;
                }
            }
        }
        gameStatus();
    }
    @FXML
    protected void onFourthButtonClick() {
        if (!playGame.isOver()) {
            int buttonNumber = 4;
            if (playGame.getPlayer().equals("Short")) {
                button4.setStyle("-fx-background-color: #0000FF; ");
                if (lastButtonPressed != 0 && lastButtonPressed != 4) {
                    playGame.getPlayerLines().add(new ArrayList<>());
                    playGame.getPlayerLines().get(numPlayerLines).add(lastButtonPressed);
                    playGame.getPlayerLines().get(numPlayerLines).add(buttonNumber);
                    if(lastButtonPressed == 1) {
                        line1to4.setStyle("-fx-stroke: #0000FF; ");
                        button1.setStyle("-fx-background-color: #808080; ");

                    } else if(lastButtonPressed == 2) {
                        line2to4.setStyle("-fx-stroke: #0000FF; ");
                        button2.setStyle("-fx-background-color: #808080; ");
                    } else {
                        line3to4.setStyle("-fx-stroke: #0000FF; ");
                        button3.setStyle("-fx-background-color: #808080; ");
                    }
                    button4.setStyle("-fx-background-color: #808080; ");
                    playGame.setPlayer("Cut");
                    numPlayerLines = playGame.getNumPlayerLines();
                    startPlayer.setText("Next Move: " + playGame.getPlayer());
                    lastButtonPressed = 0;
                } else {
                    lastButtonPressed = 4;
                }
            } else if (playGame.getPlayer().equals("Cut")) {
                button4.setStyle("-fx-background-color: #FF0000; ");
                if (lastButtonPressed != 0 && lastButtonPressed != 4) {
                    playGame.getCutLines().add(new ArrayList<>());
                    playGame.getCutLines().get(numCutLines).add(lastButtonPressed);
                    playGame.getCutLines().get(numCutLines).add(buttonNumber);
                    if(lastButtonPressed == 1) {
                        line1to4.setStyle("-fx-stroke: #FF0000; ");
                        button1.setStyle("-fx-background-color: #808080; ");
                    } else if(lastButtonPressed == 2) {
                        line2to4.setStyle("-fx-stroke: #FF0000; ");
                        button2.setStyle("-fx-background-color: #808080; ");
                    } else {
                        line3to4.setStyle("-fx-stroke: #FF0000; ");
                        button3.setStyle("-fx-background-color: #808080; ");
                    }
                    button4.setStyle("-fx-background-color: #808080; ");
                    playGame.setPlayer("Short");
                    numCutLines = playGame.getNumCutLines();
                    startPlayer.setText("Next Move: " + playGame.getPlayer());
                    lastButtonPressed = 0;
                } else {
                    lastButtonPressed = 4;
                }
            }
        }
        gameStatus();
    }
    @FXML
    protected void gameStatus() {
        playGame.checkWin();
        if(line1to2.getStroke().equals(Color.WHITE) || line1to3.getStroke().equals(Color.WHITE) || line1to4.getStroke().equals(Color.WHITE) || line2to3.getStroke().equals(Color.WHITE) || line2to4.getStroke().equals(Color.WHITE) || line3to4.getStroke().equals(Color.WHITE)) {
        } else {
            if(!playGame.haveWon()){
                playGame.setHaveWon(false);
                playGame.setOver(true);
            }
        }
        if (playGame.isOver()) {
            if(playGame.haveWon()) {
                welcomeText.setText("YOU WIN!");
                startButton.setText("Play again?");
            } else {
                welcomeText.setText("YOU LOSE :(");
                startButton.setText("Play again?");
            }
        }
    }
}
