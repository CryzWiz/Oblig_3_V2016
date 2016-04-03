package breakout;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Highscore extends StackPane implements Settings {
	static String[][] players = new String[3][3];
	static ArrayList<Highscore> scores = new ArrayList<>();
	String name;
    String scoreValue;
    //static Pane pane;
	
	
	
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
				String name = players[i][0] + " " + players[i][1];
				String scoreValue = players[i][2];
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
    
    public static void drawScores(Pane pane){
    	VBox scoreList = new VBox();
    	for(Highscore highscore : scores){
    		Text text = new Text(highscore.toString());
        	text.setFont(FONT_TEXT);
        	text.setFill(TEXT_COLOR);
        	scoreList.getChildren().add(text);
    	}
    	pane.getChildren().add(scoreList);
    	scoreList.setAlignment(Pos.CENTER);
    	//pane = new BorderPane(scoreList, new Text("Scores:"), null, null, null);
    }
    
    public String toString(){
    	return name + " " + scoreValue;
    }
}
