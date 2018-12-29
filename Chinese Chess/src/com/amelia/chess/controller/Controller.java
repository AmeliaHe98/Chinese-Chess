package com.amelia.chess.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.amelia.chess.entity.ChessBoard;
import com.amelia.chess.entity.ChessMan;
import com.amelia.chess.panel.GamePanel;
import com.amelia.chess.util.MusicUtil;

public class Controller extends MouseAdapter{
    private static ChessBoard chessBoard;
    private static ChessMan chessMan;
    private static GamePanel gamePanel;
    private static boolean gameover = false;

    public Controller(GamePanel gamePanel, ChessBoard chess, ChessMan chessMan) {
        Controller.chessBoard = chess;
        Controller.gamePanel = gamePanel;
        Controller.chessMan = chessMan;

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-20;

        if((x>=40&&x<=570)&&(y>=40&&x<=630)&&!gameover){
            int newi = (y+25)/60 -1;
            int newj = (x+25)/60 -1;

            int oldi = chessMan.getOldX();
            int oldj = chessMan.getOldY();

            int[][]chessFlag = chessMan.getChessFlag();
            //if there is piece in the current position
            if(chessFlag[newi][newj]==0){
                if(oldi!=-1&&oldj!=-1){
                    if(isChessManMove(chessFlag,newi,newj)){
                        if(MusicUtil.isGameMusic()){
                            MusicUtil.playMusic("go");
                        }
                        chessMan.setChessFlag(newi, newj, chessFlag[oldi][oldj]);
                        chessMan.setChessFlag(oldi, oldj, 0);

                        chessMan.setOldX(-1);
                        chessMan.setOldY(-1);
                        gamePanel.dispaly(chessBoard,chessMan);

                        chessMan.setRedMove(!chessMan.isRedMove());
                    }
                }
            }
            else {
                //if a piece has been here previously
                if (oldi == -1 && oldj == -1) {
                    //if the piece is yourself
                    if ((oldi == newi && oldj == newj)) {
                    }
                    //select current piece
                    else {
                        chessMan.setSelect(newi, newj);
                        //store current piece as old piece
                        chessMan.setOldX(newi);
                        chessMan.setOldY(newj);
                        showGame();
                    }
                }
                else {  //if a piece is already on the board
                    //if you can eat the piece
                    if(isChessManMove(chessFlag,newi,newj)){
                        //if the piece is on your side
                        if ((chessFlag[oldi][oldj] >= 8 && chessFlag[newi][newj] < 8) ||
                                (chessFlag[oldi][oldj] < 8 && chessFlag[newi][newj] >= 8)) {
                            if (chessFlag[newi][newj] == 5) {
                                gamePanel.setMessage(1);
                                gameover = true;
                            } else if (chessFlag[newi][newj] == 14) {
                                gamePanel.setMessage(2);
                                gameover = true;
                            }
                            if(MusicUtil.isGameMusic()){
                                MusicUtil.playMusic("eat");
                            }
                            chessMan.setChessFlag(newi,newj,chessFlag[oldi][oldj]);
                            chessMan.setChessFlag(oldi, oldj, 0);
                            // next move
                            chessMan.setRedMove(!chessMan.isRedMove());
                        }
                    }
                    chessMan.setOldX(-1);
                    chessMan.setOldY(-1);
                    showGame();
                }
            }
        }
    }

    public boolean isChessManMove(int[][] chessFlag, int newi, int newj) {

        // set moving to false
        boolean b = false;

        // get coordiantes of chess from last move
        int oldi = chessMan.getOldX();
        int oldj = chessMan.getOldY();

        // set default as red
        boolean redFlag = true;

        // if the player is not read
        if (chessFlag[oldi][oldj] >= 8) {
            redFlag = false;
        }

        // if it is red's turn but black is playing，return false
        if(redFlag != chessMan.isRedMove()){
            System.out.println("Not your turn yet！");
            return false;
        }

        // get all the chinese pieces in the game
        String[] chessStr = chessMan.getChessStr();

        // get name of the last piece
        String name = chessStr[chessFlag[oldi][oldj] - 1];

        // find and its move method
        if(name.equals("兵")) {
            if (oldi >= 5) { // before crossing the river
                if ((oldi - newi == 1) && (newj == oldj)) {
                    b = true;
                }
            } else { // crossed the river
                if ((oldi - newi == 1) && (newj == oldj)) {
                    b = true;
                } else if ((Math.abs(newj - oldj) == 1) && (newi == oldi)) {
                    b = true;
                } else {
                    return false;
                }
            }
        }
        else if(name.equals("卒")) {
            if (oldi < 5) { // before crossing the river
                if ((newi - oldi == 1) && (newj == oldj)) {
                    b = true;
                }
            } else { // crossed the river
                if ((newi - oldi == 1) && (newj == oldj)) {
                    b = true;
                } else if ((Math.abs(newj - oldj) == 1) && (newi == oldi)) {
                    b = true;
                } else {
                    return false;
                }
            }
        }
        else if(name.equals("车")) {
            if (newi == oldi) {
                int k = 0;
                if (newj > oldj) { // moving toward right
                    for (int i = oldj; i <= newj; i++) {
                        if (chessFlag[oldi][i] > 0) {
                            k++;
                        }
                    }
                } else {
                    for (int i = oldj; i >= newj; i--) { // moving toward left
                        if (chessFlag[oldi][i] > 0) {
                            k++;
                        }
                    }
                }
                if (k <= 2) {
                    b = true;
                }
            } else if (newj == oldj) { // moving down
                int k = 0;
                if (newi > oldi) {
                    for (int i = oldi; i <= newi; i++) {
                        if (chessFlag[i][oldj] > 0) {
                            k++;
                        }
                    }
                } else {
                    for (int i = oldi; i >= newi; i--) { // moving up
                        if (chessFlag[i][oldj] > 0) {
                            k++;
                        }
                    }
                }
                if (k <= 2) {
                    b = true;
                }
            }
        }
        else if(name.equals("炮")) {
            int s = 0;
            if (newi == oldi) {
                if (newj > oldj) {
                    for (int i = oldj; i <= newj; i++) {
                        if (chessFlag[oldi][i] > 0) {
                            s++;
                        }
                    }
                } else {
                    for (int i = oldj; i >= newj; i--) {
                        if (chessFlag[oldi][i] > 0) {
                            s++;
                        }
                    }
                }
            } else if (newj == oldj) {
                if (newi > oldi) {
                    for (int i = oldi; i <= newi; i++) {
                        if (chessFlag[i][oldj] > 0) {
                            s++;
                        }
                    }
                } else {
                    for (int i = oldi; i >= newi; i--) {
                        if (chessFlag[i][oldj] > 0) {
                            s++;
                        }
                    }
                }
            }
            if (s == 1 || (s == 3 && chessFlag[newi][newj] > 0)) {
                b = true;
            }
        }
        else if(name.equals("帅")||name.equals("将")) {
            if (newj == oldj && (Math.abs(newi - oldi) >= 5)) {
                int flag = 0;
                if (newi > oldi) { // 将eat帅
                    for (int i = oldi; i <= newi; i++) {
                        if ((chessFlag[oldi][oldj] == 5)
                                && (chessFlag[newi][newj] == 14)) {
                            if (chessFlag[i][oldj] > 0) {
                                flag++;
                            }
                        }
                    }
                } else { // 帅eat将
                    for (int i = newi; i <= oldi; i++) {
                        if ((chessFlag[oldi][oldj] == 14)
                                && (chessFlag[newi][newj] == 5)) {
                            if (chessFlag[i][oldj] > 0) {
                                flag++;
                            }
                        }
                    }
                }
                if (flag == 2) {
                    b = true;
                }
            } else if (((newi <= 2 && redFlag == true) || (newi >= 7 && redFlag == false))
                    && (newj <= 5 && newj >= 3)) {
                if (Math.abs(newi - oldi) == 1 && newj == oldj) {
                    b = true;
                } else if (Math.abs(newj - oldj) == 1 && newi == oldi) {
                    b = true;
                }
            }
        }
        else if(name.equals("士")) {
            if (redFlag) { //red side
                if (newi <= 2 && newj <= 5 && newj >= 3) {
                    if (Math.abs(newi - oldi) == 1
                            && Math.abs(newj - oldj) == 1) {
                        b = true;
                    }
                }
            } else { //black side
                if (newi >= 7 && newj <= 5 && newj >= 3) {
                    if (Math.abs(newi - oldi) == 1
                            && Math.abs(newj - oldj) == 1) {
                        b = true;
                    }
                }
            }
        }
        else if(name.equals("象")||name.equals("相")) {
            if ((newi >= 5 && redFlag == false)
                    || (newi < 5 && redFlag == true)) {
                if ((newi - oldi) == 2 && (newj - oldj) == 2) { // move lower right
                    if (chessFlag[oldi + 1][oldj + 1] == 0) {
                        b = true;
                    }
                } else if ((newi - oldi) == -2 && (newj - oldj) == 2) { // move upper right
                    if (chessFlag[oldi - 1][oldj + 1] == 0) {
                        b = true;
                    }
                } else if ((newi - oldi) == 2 && (newj - oldj) == -2) { // move lower left
                    if (chessFlag[oldi + 1][oldj - 1] == 0) {
                        b = true;
                    }
                } else if ((newi - oldi) == -2 && (newj - oldj) == -2) { // move upper le
                    if (chessFlag[oldi - 1][oldj - 1] == 0) {
                        b = true;
                    }
                }
            }
        }
        else if(name.equals("马")) {
            if ((newi - oldi) == -2 && (newj - oldj) == 1) { // move to north east
                if (chessFlag[oldi - 1][oldj] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == -2 && (newj - oldj) == -1) { // move to north west
                if (chessFlag[oldi - 1][oldj] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == 2 && (newj - oldj) == 1) { // move to south east
                if (chessFlag[oldi + 1][oldj] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == 2 && (newj - oldj) == -1) { // move to north west
                if (chessFlag[oldi + 1][oldj] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == -1 && (newj - oldj) == 2) { // move to east north
                if (chessFlag[oldi][oldj + 1] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == 1 && (newj - oldj) == 2) { // move to east south
                if (chessFlag[oldi][oldj + 1] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == -1 && (newj - oldj) == -2) { // move to west north
                if (chessFlag[oldi][oldj - 1] == 0) {
                    b = true;
                }
            } else if ((newi - oldi) == 1 && (newj - oldj) == -2) { // move to west south
                if (chessFlag[oldi][oldj - 1] == 0) {
                    b = true;
                }
            }
        }
        return b;
    }


    public static void showGame(){
        gamePanel.dispaly(chessBoard, chessMan);
    }

    public static void setGameover(boolean gameover) {
        Controller.gameover = gameover;
    }
}