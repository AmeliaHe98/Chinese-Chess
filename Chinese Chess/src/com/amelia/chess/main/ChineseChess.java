package com.amelia.chess.main;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import com.amelia.chess.controller.Controller;
import com.amelia.chess.entity.ChessBoard;
import com.amelia.chess.entity.ChessMan;
import com.amelia.chess.menu.ChessMenu;
import com.amelia.chess.panel.GamePanel;

public class ChineseChess extends JFrame {

    public ChineseChess() throws IOException{
        ChessBoard chessBoard = new ChessBoard();
        ChessMan chessMan = new ChessMan();
        GamePanel gamePanel = new GamePanel();
        Controller controller = new Controller(gamePanel, chessBoard, chessMan);

        this.add(gamePanel);
        this.setJMenuBar(new ChessMenu().getChessMenu());
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setTitle("Chinese Chess");
        this.addMouseListener(controller);
        this.setIconImage(ImageIO.read(new File("res/chess.jpg")));
        this.setSize(605, 710);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new ChineseChess();
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
