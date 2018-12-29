package com.amelia.chess.entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import com.amelia.chess.util.FontUtil;


public class ChessBoard {
    public void drawChess(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Stroke stroke = g2d.getStroke();
        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(4));
        g2d.drawRect(49, 49, 501, 561);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(60, 60, 540, 60);
        g2d.drawLine(60, 300, 540, 300);
        g2d.drawLine(60, 360, 540, 360);
        g2d.drawLine(60, 600, 540, 600);
        g2d.drawLine(60, 60, 60, 600);
        g2d.drawLine(540, 60, 540, 600);

        // draw black 炮
        drawFlag(g2d, true, true, 120, 180);
        drawFlag(g2d, true, true, 480, 180);
        // draw red 炮
        drawFlag(g2d, true, true, 120, 480);
        drawFlag(g2d, true, true, 480, 480);

        // draw兵
        drawFlag(g2d, false, true, 60, 240);
        for (int x = 180; x <= 480; x += 120) {
            drawFlag(g2d, true, true, x, 240);
        }
        drawFlag(g2d, true, false, 540, 240);
        g2d.setStroke(stroke);
        g.setColor(Color.BLACK);
        for (int i = 1; i <= 10; i++) {
            if (i <= 9) {
                g.drawLine(60 * i, 60, 60 * i, 600);
            }
            g.drawLine(60, 60 * i, 540, 60 * i);
        }
        g.setColor(new Color(216, 196, 160));
        g2d.fillRect(61, 301, 478, 58);
        g2d.setColor(Color.BLACK);
        g2d.setFont(FontUtil.myFont2);
        g2d.drawString("河", 135, 340);
        g2d.drawString("界", 435, 340);

        g2d.drawLine(240, 60, 360, 180);
        g2d.drawLine(240, 180, 360, 60);
        g2d.drawLine(240, 480, 360, 600);
        g2d.drawLine(240, 600, 360, 480);

    }


        private void drawFlag(Graphics2D g2d, boolean leftFlag, boolean rightFlag, int x, int y){
            if(leftFlag){
                g2d.drawLine(x-5, y-20, x-5, y-5);
                g2d.drawLine(x-5, y+20, x-5, y+5);
                g2d.drawLine(x-20, y-5, x-5, y-5);
                g2d.drawLine(x-20, y+5, x-5, y+5);
            }
            if(rightFlag){
                g2d.drawLine(x+5, y-20, x+5, y-5);
                g2d.drawLine(x+5, y+20, x+5, y+5);
                g2d.drawLine(x+5, y+5, x+20, y+5);
                g2d.drawLine(x+5, y-5, x+20, y-5);
            }
    }





}
