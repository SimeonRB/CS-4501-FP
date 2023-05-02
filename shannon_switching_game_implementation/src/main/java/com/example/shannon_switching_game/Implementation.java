package com.example.shannon_switching_game;

import java.util.*;
import java.io.*;
public class Implementation {
    private int numPoints;
    private List<List<Integer>> playerLines;
    private  List<List<Integer>> cutLines;
    private List<Integer> special;
    private List<Integer> line;
    private boolean isOver;
    private String player;
    private boolean haveWon;
    public Implementation(String file) throws FileNotFoundException {
        numPoints = -1;
        playerLines = new ArrayList<>();
        special = new ArrayList<>();
        File gameFile = new File(file);
        Scanner s = new Scanner (gameFile);
        while (s.hasNextLine()) {
            String reader = s.nextLine();
            String[] values = reader.split(" ");
            if (values[0].equals("P")) {
                numPoints = Integer.parseInt(values[1]);
            } else if (values[0].equals("L")) {
                List<Integer> line;
                if (values.length > 3) {
                    int numLines = Integer.parseInt(values[3]);
                    line = new ArrayList<>();
                    for(int j = 1; j <= 2; j++){
                        line.add(Integer.parseInt(values[j]));
                    }
                    for (int i = 0; i < numLines; i++) {
                        playerLines.add(line);
                    }
                } else {
                    line = new ArrayList<>();
                    for(int j = 1; j <= 2; j++){
                        line.add(Integer.parseInt(values[j]));
                    }
                    playerLines.add(line);
                }
            } else if (values[0].equals("S")) {
                special.add(Integer.parseInt(values[1]));
                special.add(Integer.parseInt(values[2]));
            }
        }
        s.close();
        if(numPoints < 0) {
            System.err.println("Points not specified");
            System.exit(1);
        }
        if(special.size() < 2) {
            System.err.println("Special Nodes are not specified");
            System.exit(1);
        }
        if(playerLines.size() == 0) {
            System.err.println("No links are specified");
            System.exit(1);
        }
        this.cutLines = new ArrayList<>();
        this.player = "Short";
        this.isOver = false;
        this.haveWon = false;
        this.line = new ArrayList<>();
    }
    public Implementation() {
        this.numPoints = 0;
        this.playerLines = new ArrayList<>();
        this.cutLines = new ArrayList<>();
        this.special = new ArrayList<>();
        this.player = "Short";
        this.isOver = false;
        this.haveWon = false;
        this.line = new ArrayList<>();
    }
    public int getNumPoints() {
        return numPoints;
    }
    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }
    public List<List<Integer>> getPlayerLines() {
        return playerLines;
    }
    public List<List<Integer>> getCutLines() {
        return cutLines;
    }
    public void setPlayerLines(List<List<Integer>> playerLines) {
        this.playerLines = playerLines;
    }
    public void setCutLines(List<List<Integer>> playerLines) {
        this.cutLines = cutLines;
    }
    public List<Integer> getSpecial() {
        return special;
    }
    public void setSpecial(int numPoints) {
        if(this.special.size() == 0) {
            this.special.add(1);
            int special = 2 + (int) (Math.random()*(numPoints-1));
            this.special.add(special);
        }
    }
    public List<Integer> getLine(int lineNumber) {
        return playerLines.get(lineNumber);
    }
    public void addLine(Integer point) {
        if (line.size() == 0) {
            line.add(point);
        } else if (line.size() == 1) {
            if(line.get(0) != point){
                line.add(point);
                this.playerLines.add(line);
                setPlayerLines(playerLines);
                line.clear();
            }
        }
    }
    public int getNumPlayerLines() {
        return playerLines.size();
    }
    public int getNumCutLines() {
        return cutLines.size();
    }
    public void checkWin() {
        List<Integer> buffer = new ArrayList<>();
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        for(int i = 0; i < playerLines.size(); i++) {
            if(playerLines.get(i).get(0) == special.get(0) && playerLines.get(i).get(1) == special.get(1)){
                isOver = true;
                haveWon = true;
                break;
            } else if(playerLines.get(i).get(1) == special.get(0) && playerLines.get(i).get(0) == special.get(1)){
                isOver = true;
                haveWon = true;
                break;
            } else {
                for(int j = 0; j < 2; j++) {
                    buffer.add(playerLines.get(i).get(j));
                }
                for(int k = 0; k < buffer.size(); k++) {
                    if (buffer.get(k) == 1) {
                        one++;
                    }
                    if (buffer.get(k) == 2) {
                        two++;
                    }
                    if (buffer.get(k) == 3) {
                        three++;
                    }
                    if (buffer.get(k) == 4) {
                        four++;
                    }
                }
                if(special.get(1) == 2) {
                    if(one > 0 && two > 0) {
                        if(three >= 2 || four >= 2) {
                            isOver = true;
                            haveWon = true;
                            break;
                        }
                        if(three == 1 ^ four == 1) {
                            isOver = true;
                            haveWon = true;
                            break;
                        }
                    }
                }
                if(special.get(1) == 3) {
                    if(one > 0 && three > 0) {
                        if(two >= 2 || four >= 2) {
                            isOver = true;
                            haveWon = true;
                            break;
                        }
                        if(two == 1 ^ four == 1) {
                            isOver = true;
                            haveWon = true;
                            break;
                        }
                    }
                }
                if(special.get(1) == 4) {
                    if(one > 0 && four > 0) {
                        if(three >= 2 || two >= 2) {
                            isOver = true;
                            haveWon = true;
                            break;
                        }
                        if(three == 1 ^ two == 1) {
                            isOver = true;
                            haveWon = true;
                            break;
                        }
                    }
                }
            }
        }
        List <Integer> specialOne = new ArrayList<>();
        boolean addSpecialOne = true;
        List <Integer> specialTwo = new ArrayList<>();
        boolean addSpecialTwo = true;
        if (!isOver) {
            for (int i = 0; i < cutLines.size(); i++) {
                int connection = 0;
                if(cutLines.get(i).get(0) != special.get(0) && cutLines.get(i).get(1) != special.get(1) && cutLines.get(i).get(0) != special.get(0) && cutLines.get(i).get(1) != special.get(1)){
                    continue;
                } else {
                    if (cutLines.get(i).get(0) == special.get(0) || cutLines.get(i).get(0) == special.get(1)) {
                        connection = cutLines.get(i).get(1);
                        if(cutLines.get(i).get(0) == special.get(0)) {
                            for(int j = 0; j < specialTwo.size(); j++) {
                                if(specialTwo.get(j) == connection) {
                                    addSpecialTwo = false;
                                }
                            }
                            if (addSpecialTwo) {
                                specialTwo.add(connection);
                                if (specialTwo.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            }
                        } else {
                            for(int j = 0; j < specialOne.size(); j++) {
                                if(specialOne.get(j) == connection) {
                                    addSpecialOne = false;
                                }
                            }
                            if (addSpecialOne) {
                                specialOne.add(connection);
                                if (specialOne.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            }
                        }
                    } else if (cutLines.get(i).get(1) == special.get(0) || cutLines.get(i).get(1) == special.get(1)) {
                        connection = cutLines.get(i).get(0);
                        if(cutLines.get(i).get(1) == special.get(0)) {
                            for(int j = 0; j < specialTwo.size(); j++) {
                                if(specialTwo.get(j) == connection) {
                                    addSpecialTwo = false;
                                }
                            }
                            if (addSpecialTwo) {
                                specialTwo.add(connection);
                                if (specialTwo.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            } else {
                                addSpecialTwo = true;
                            }
                        } else {
                            for(int j = 0; j < specialOne.size(); j++) {
                                if(specialOne.get(j) == connection) {
                                    addSpecialOne = false;
                                }
                            }
                            if (addSpecialOne) {
                                specialOne.add(connection);
                                if (specialOne.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            } else {
                                addSpecialOne = true;
                            }
                        }
                    } else {
                        if (cutLines.get(i).get(0) == special.get(0)) {
                            for (int j = 0; j < specialTwo.size(); j++) {
                                if (specialTwo.get(j) == special.get(0)) {
                                    addSpecialTwo = false;
                                }
                            }
                            if (addSpecialTwo) {
                                specialTwo.add(special.get(0));
                                if (specialTwo.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            } else {
                                addSpecialTwo = true;
                            }
                            for (int j = 0; j < specialOne.size(); j++) {
                                if (specialOne.get(j) == special.get(1)) {
                                    addSpecialOne = false;
                                }
                            }
                            if (addSpecialOne) {
                                specialOne.add(special.get(1));
                                if (specialOne.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            } else {
                                addSpecialOne = true;
                            }
                        } else {
                            for (int j = 0; j < specialTwo.size(); j++) {
                                if (specialTwo.get(j) == special.get(1)) {
                                    addSpecialTwo = false;
                                }
                            }
                            if (addSpecialTwo) {
                                specialTwo.add(special.get(1));
                                if (specialTwo.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            } else {
                                addSpecialTwo = true;
                            }
                            for (int j = 0; j < specialOne.size(); j++) {
                                if (specialOne.get(j) == special.get(0)) {
                                    addSpecialOne = false;
                                }
                            }
                            if (addSpecialOne) {
                                specialOne.add(special.get(0));
                                if (specialOne.size() == numPoints - 1) {
                                    isOver = true;
                                    haveWon = false;
                                    break;
                                }
                            } else {
                                addSpecialOne = true;
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean isOver() {
        return isOver;
    }
    public boolean haveWon() {
        return haveWon;
    }
    public String getPlayer() {
        return player;
    }
    public void clearLine() {
        if(player.equals("Cut")) {
            playerLines.add(line);
        }
        if(player.equals("Short")) {
            cutLines.add(line);
        }
        line.clear();
    }
    public void setPlayer(String player) {
        this.player = player;
    }
    public void deleteLine(int point){
        if (line.size() == 0) {
            line.add(point);
        } else if (line.size() >= 1) {
            if(line.get(0) != point){
                cutLines.add(line);
                setCutLines(cutLines);
                line.clear();
            }
        }
    }
}
