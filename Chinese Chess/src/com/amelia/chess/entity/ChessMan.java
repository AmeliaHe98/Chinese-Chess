package com.amelia.chess.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.Serializable;

import com.amelia.chess.util.FontUtil;
public class ChessMan implements Serializable{
    private int oldX = -1;
    private int oldY = -1;
    private int newX = -1;
    private int newY = -1;
    private boolean redMove = true;
    private String[] chessStr = { "车", "马", "象", "士", "将", "炮", "卒", "兵", "炮", "车", "马", "相", "士", "帅" };
    private static int[][] chessFlag = new ChessFlag().getChessFlag();
    private boolean[][] select = new boolean[10][9];

    public void drawChessMan(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Stroke stroke = g2d.getStroke();

        for (int i = 0; i < chessFlag.length; i++) {
            for (int j = 0; j < chessFlag[i].length; j++) {
                if (chessFlag[i][j] != 0) {
                    int x = (j + 1) * 60;
                    int y = (i + 1) * 60;

                    if (select[i][j]) {
                        g2d.setColor(new Color(0, 200, 0));
                        g2d.setStroke(new BasicStroke(3));
                        g2d.drawLine(x - 25, y - 20, x - 25, y - 25);
                        g2d.drawLine(x - 25, y + 20, x - 25, y + 25);
                        g2d.drawLine(x - 20, y - 25, x - 25, y - 25);
                        g2d.drawLine(x - 20, y + 25, x - 25, y + 25);
                        g2d.drawLine(x + 25, y - 20, x + 25, y - 25);
                        g2d.drawLine(x + 25, y + 20, x + 25, y + 25);
                        g2d.drawLine(x + 25, y + 25, x + 20, y + 25);
                        g2d.drawLine(x + 25, y - 25, x + 20, y - 25);
                        g2d.setStroke(stroke);

                        select[i][j] = !select[i][j];
                    }
                    g2d.setColor(new Color(219, 196, 154));
                    g2d.fillOval(x - 25, y - 25, 50, 50);

                    g2d.setColor(new Color(109, 98, 77));
                    g2d.drawOval(x - 25, y - 25, 50, 50);

                    if (chessFlag[i][j] < 8) {
                        g2d.setColor(Color.RED);
                    } else {
                        g2d.setColor(Color.BLACK);
                    }

                    g2d.drawOval(x - 20, y - 20, 40, 40);

                    g2d.setFont(FontUtil.myFont2);
                    String newstring = new String(chessStr[chessFlag[i][j] - 1]);
                    g2d.drawString(newstring, x - 20, y + 10);
                }
            }
        }
    }

    public int getOldX(){
        return oldX;
    }

    public boolean isRedMove() {
        return redMove;
    }

    public void setRedMove(boolean redMove) {
        this.redMove = redMove;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public int getNewX() {
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    public String[] getChessStr() {
        return chessStr;
    }

    public static int[][] getChessFlag() {
        return chessFlag;
    }

    public static void setChessFlag(int[][] chessFlag) {
        ChessMan.chessFlag = chessFlag;
    }

    public static void setChessFlag(int i, int j, int z) {
        chessFlag[i][j] = z;
    }

    public boolean[][] getSelect() {
        return select;
    }

    public void setSelect(int i, int j) {
        select[i][j] = !select[i][j];
    }
}
