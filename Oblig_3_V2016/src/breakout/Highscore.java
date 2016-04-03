package breakout;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Highscore extends StackPane implements Settings, Comparable<Highscore> {
	static ArrayList<Highscore> scores = new ArrayList<>();
	String name;
    Timer scoreValue;
    //static Pane pane;
	
    public Highscore(Timer scoreValue, String name) {
    	this.scoreValue = scoreValue;
    	this.name = name;
    	scores.add(this);
    	Collections.sort(scores);
	}
    
	public static ArrayList<Highscore> getScores(){
		return scores;
	}

	public static void loadScores() throws ParseException{
    	java.io.File file = new java.io.File(HIGHSCORE_FILE);
    	try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				String[] inputs = new String[3];
				inputs[0] = input.next();
				inputs[1] = input.next();
				inputs[2] = input.next();
				String name = inputs[0] + " " + inputs[1];
				Timer scoreValue = Timer.parse(inputs[2]);
				new Highscore(scoreValue, name);
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void saveScores(String filename){
    	try {
    		PrintWriter writer = new PrintWriter(filename, "UTF-8");
    		for(Highscore score : scores){
    			writer.println(score.toString());
    		}
    		writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
    public static void drawScores(Pane pane){
    	VBox scoreList = new VBox();
    	for(Highscore highscore : scores){
    		Text text = new Text(highscore.toString());
        	text.setFont(FONT_TEXT);
        	text.setFill(TEXT_COLOR);
        	scoreList.getChildren().add(text);
    	}
    	VBox newScore = new VBox();
    	Label PlayerMessage = new Label("Enter your name to be added to the Highscore list.");
    	PlayerMessage.setTextFill(TEXT_COLOR);
    	newScore.getChildren().add(PlayerMessage);
    	newScore.getChildren().add(new TextField());
    	newScore.setAlignment(Pos.BOTTOM_CENTER);
    	pane.getChildren().addAll(scoreList, newScore);
    	scoreList.setAlignment(Pos.CENTER);
    	//pane = new BorderPane(scoreList, new Text("Scores:"), null, null, null);
    }
    
    public String toString(){
    	return name + " " + scoreValue.toString();
    }

	@Override
	public int compareTo(Highscore score) {
		return scoreValue.compareTo(score.scoreValue);
	}
}
