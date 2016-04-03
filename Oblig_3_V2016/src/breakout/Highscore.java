package breakout;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Highscore extends StackPane implements Settings {
	static ArrayList<Highscore> scores = new ArrayList<>();
	String name;
    String scoreValue;
    //static Pane pane;
	
    public Highscore(String scoreValue, String name) {
    	this.scoreValue = scoreValue;
    	this.name = name;
    	scores.add(this);
	}
    
	public static ArrayList<Highscore> getScores(){
		return scores;
	}

	public static void loadScores(){
    	java.io.File file = new java.io.File(HIGHSCORE_FILE);
    	try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				String[] inputs = new String[3];
				inputs[0] = input.next();
				inputs[1] = input.next();
				inputs[2] = input.next();
				String name = inputs[0] + " " + inputs[1];
				String scoreValue = inputs[2];
				new Highscore(scoreValue, name);
				System.out.println(scores.get(0));
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
    public static void drawScores(ScrollPane pane){
    	TextArea scoreList = new TextArea();
    	for(Highscore highscore : scores){
    		Text text = new Text(highscore.toString());
        	text.setFont(FONT_TEXT);
        	text.setFill(TEXT_COLOR);
        	scoreList.appendText(text.toString());

    	}
    	VBox newScore = new VBox();
    	Label PlayerMessage = new Label("Enter your name to be added to the Highscore list.");
    	PlayerMessage.setTextFill(TEXT_COLOR);
    	newScore.getChildren().add(PlayerMessage);
    	newScore.getChildren().add(new TextField());
    	newScore.setAlignment(Pos.BOTTOM_CENTER);
    	ScrollPane scrollPane = new ScrollPane(scoreList);
    	//pane.getChildren().addAll(scoreList, newScore);
    	//scoreList.setAlignment(Pos.CENTER);
    	//pane = new BorderPane(scoreList, new Text("Scores:"), null, null, null);
    }
    
    public String toString(){
    	return name + " " + scoreValue;
    }
}
