package com.amelia.chess.entity;
public class ChessFlag implements java.io.Serializable {

    private int[][] chessFlag = {
            {1, 2, 3, 4, 5, 4, 3, 2, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 0, 6, 0},
            {7, 0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {8, 0, 8, 0, 8, 0, 8, 0, 8},
            {0, 9, 0, 0, 0, 0, 0, 9, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {10, 11, 12, 13, 14, 13, 12, 11, 10}};

    public int[][] getChessFlag() {
        return chessFlag;
    }

    public void setChessFlag(int[][] chessFlag) {
        this.chessFlag = chessFlag;
    }
}

