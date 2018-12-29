package com.amelia.chess.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.amelia.chess.controller.Controller;
import com.amelia.chess.entity.ChessFlag;
import com.amelia.chess.entity.ChessMan;
import com.amelia.chess.util.FontUtil;
import com.amelia.chess.util.MusicUtil;

public class ChessMenu implements ActionListener{

	public JMenuBar getChessMenu(){

		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Menu");
		JMenu helpMenu = new JMenu("Help");

		JMenuItem startItem = new JMenuItem("Start");
		startItem.addActionListener(this);
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(this);
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		JMenu soundItem = new JMenu("Sound");
			JMenuItem bgmusic = new JMenuItem("BgMusic");
			bgmusic.addActionListener(this);
			soundItem.add(bgmusic);
			JMenuItem gamemusic = new JMenuItem("GameMusic");
			gamemusic.addActionListener(this);
			soundItem.add(gamemusic);	
		JMenuItem gameItem = new JMenuItem("Game");
		gameItem.addActionListener(this);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		JMenuItem updateItem = new JMenuItem("Update");
		updateItem.addActionListener(this);
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);

		gameMenu.add(startItem);
		gameMenu.add(openItem);
		gameMenu.add(saveItem);
		gameMenu.addSeparator();
		gameMenu.add(soundItem);
		gameMenu.add(gameItem);
		gameMenu.addSeparator();
		gameMenu.add(exitItem);
		helpMenu.add(updateItem);
		helpMenu.add(aboutItem);

		menuBar.add(gameMenu);
		menuBar.add(helpMenu);

		return menuBar;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")){
			startGame();
		}else if(e.getActionCommand().equals("Open")){
			openGame();
		}else if(e.getActionCommand().equals("Save")){
			saveGame();
		}else if(e.getActionCommand().equals("BgMusic")){
			bgMusic();
		}else if(e.getActionCommand().equals("GameMusic")){
			gameMusic();
		}else if(e.getActionCommand().equals("Help")){
			JOptionPane.showMessageDialog(null, "Help Panel");
		}else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}else if(e.getActionCommand().equals("Update")){
			updateGame();
		}else if(e.getActionCommand().equals("About")){
			aboutGame();
		}
	}


	public void startGame(){
				int[][] initFlag = { { 1,  2, 3, 4 , 5 , 4, 3 , 2 , 1 }, 
						   { 0, 0 , 0 , 0 , 0, 0 , 0 , 0 , 0 },
						   { 0,  6, 0 , 0 , 0 , 0 , 0 , 6 , 0 },
						   { 7 , 0 , 7 , 0 , 7 , 0 , 7 , 0, 7 } ,
						   { 0, 0 , 0 , 0 , 0, 0 , 0 , 0 , 0 },
						   
						   { 0, 0 , 0 , 0 , 0, 0 , 0 , 0 , 0 },
						   { 8 , 0 , 8 , 0 , 8 , 0 , 8 , 0, 8 } ,
						   { 0,  9, 0 , 0 , 0 , 0 , 0 , 9 , 0 },
						   { 0, 0 , 0 , 0 , 0, 0 , 0 , 0 , 0 },
						   { 10,  11, 12, 13 , 14 , 13, 12 , 11 , 10 }
				};

				ChessMan chessMan = new ChessMan();
				chessMan.setChessFlag(initFlag);
				Controller.setGameover(false);
				Controller.showGame();
	}

	public void openGame(){
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(null);
			String filepath = fileChooser.getSelectedFile().getAbsolutePath();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
			ChessFlag chessFlag = (ChessFlag) ois.readObject();

			ChessMan chessMan = new ChessMan();
			chessMan.setChessFlag(chessFlag.getChessFlag());
			Controller.setGameover(false);
			Controller.showGame();
			ois.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot open game");
		} 
	}
	

	public void saveGame(){
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(null);
			String filename = fileChooser.getSelectedFile().getAbsolutePath();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			ChessFlag chessFlag = new ChessFlag();
			chessFlag.setChessFlag(new ChessMan().getChessFlag());
			oos.writeObject(chessFlag);
			oos.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot save game");
		} 
	}

	public void bgMusic(){
		if(MusicUtil.isBgMusic() == false){
			MusicUtil.setBgMusic(true);
			MusicUtil.playMusic("bgmusic");		
		}else{
			MusicUtil.setBgMusic(false);
			MusicUtil.closeMusic();
		}
			
	}
	

	public void gameMusic(){
		if(MusicUtil.isGameMusic()){
			MusicUtil.setGameMusic(false);
		}else{
			MusicUtil.setGameMusic(true);
		}	
	}
	

	public void updateGame(){
		try {
			Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE http://blog.csdn.net/yy873259480/article/details/7947963"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aboutGame(){
		try {
			JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("src/res/chess.jpg"))));
			JTextArea ablutText = new JTextArea();
			ablutText.setEditable(false);
			ablutText.setFont(FontUtil.myFont1);
			ablutText.setText("my game");
			JDialog dialog = new JDialog();
			dialog.add(label,BorderLayout.WEST);
			dialog.add(new JScrollPane(ablutText),BorderLayout.CENTER);
			dialog.setIconImage(ImageIO.read(new File("src/res/chess.jpg")));
			dialog.setTitle("Chinese Chess");
			dialog.setSize(480, 160);
			dialog.setVisible(true);
			dialog.setAlwaysOnTop(true);
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}







