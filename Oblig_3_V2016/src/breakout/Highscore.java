package breakout;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.layout.StackPane;

public class Highscore extends StackPane implements Settings {
	static String[][] players;
	static ArrayList<Highscore> scores = new ArrayList<>();
	static String name;
    static String scoreValue;
	
	
	
    public Highscore(String scoreValue, String name) {
    	this.scoreValue = scoreValue;
    	this.name = name;
	}
    
	public static void loadScores(){
    	java.io.File file = new java.io.File(HIGHSCORE_FILE);
    	try {
			Scanner input = new Scanner(file);
			int i = 0;
			while (input.hasNextLine()) {
				players[i][0] = input.next();
				players[i][1] = input.next();
				players[i][2] = input.next();
				name = players[i][0] + " " + players[i][1];
				scoreValue = players[i][2];
				scores.add(new Highscore(scoreValue, name));
				i++;
			}
				input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
	public static ArrayList<Highscore> getScores(){
		return scores;
	}

    public static void saveScores(String filename){
    	
    }	
    
}
