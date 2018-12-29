package com.amelia.chess.panel;
import com.amelia.chess.entity.ChessBoard;
import com.amelia.chess.entity.ChessMan;
import com.amelia.chess.util.FontUtil;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	private ChessBoard chess = new ChessBoard();
	private ChessMan chessMan = new ChessMan();
	private int message = 0;

	public void dispaly(ChessBoard chess, ChessMan chessMan) {
		this.chess = chess;
		this.chessMan = chessMan;
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(216,196,160));
		g.fillRect(0, 0, 605, 690);

		if (chess != null && chessMan != null) {
			chess.drawChess(g);
			chessMan.drawChessMan(g);
		}

		if(message != 0){
			g.setColor(Color.RED);
			g.setFont(FontUtil.myFont3);
			if(message == 1){
				g.drawString("Red loses!", getWidth()/2-150, getHeight()/2+15);
			}else if(message == 2){
				g.drawString("Red wins!", getWidth()/2-150, getHeight()/2+15);
			}
			message = 0;
		}
	}
	
	public void setMessage(int message){
		this.message = message;
	}

}

 
