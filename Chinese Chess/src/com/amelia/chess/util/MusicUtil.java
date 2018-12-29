package com.amelia.chess.util;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicUtil {

	private static boolean bgMusic = false;
	private static boolean gameMusic = true;
	private static AudioInputStream inputStream;
	private static Clip clip = null;
	

	public static void playMusic(String str) {
		try {
			inputStream = AudioSystem.getAudioInputStream(new File("src/res/" + str + ".wav"));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
				
			if(str.equals("gameMusic")){
				System.out.println("gameMusic"+clip.hashCode());
				clip.loop(-1);
			}else{
				clip.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeMusic(){
		System.out.println("No music"+clip.hashCode());
		clip.close();
	}
	
	
	public static boolean isBgMusic() {
		return bgMusic;
	}

	public static void setBgMusic(boolean bgMusic) {
		MusicUtil.bgMusic = bgMusic;
	}

	public static boolean isGameMusic() {
		return gameMusic;
	}

	public static void setGameMusic(boolean gameMusic) {
		MusicUtil.gameMusic = gameMusic;
	}

}
