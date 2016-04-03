package breakout;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    	GridPane holderLarge = new GridPane();
    	GridPane scoreList = new GridPane();
    	for(int i = 0; i < scores.size(); i++){
    		Text nameText = new Text(scores.get(i).name);
    		Text scoreText = new Text(scores.get(i).scoreValue.toString());
    		nameText.setFont(FONT_TEXT);
    		nameText.setFill(TEXT_COLOR);
        	scoreText.setFont(FONT_TEXT);
        	scoreText.setFill(TEXT_COLOR);
    		scoreList.addRow(i, nameText, scoreText);
    	}
    	ScrollPane scoreholder = new ScrollPane();
    	/*scoreholder.setOnScroll(s -> {
    		if(s.getDeltaY() > 0){
    			scoreholder.
    		}
    	});*/
    	scoreholder.setPrefSize(WIDTH, HEIGHT);
    	scoreholder.setContent(scoreList);
    	scoreList.setBackground(BACKGROUND);
    	scoreholder.setBackground(BACKGROUND);
    	
    	VBox newScore = new VBox();
    	//newScore.setBackground(BACKGROUND);
    	Label PlayerMessage = new Label("Enter your name to be added to the Highscore list.");
    	PlayerMessage.setTextFill(TEXT_COLOR);
    	newScore.getChildren().add(PlayerMessage);
    	newScore.getChildren().add(new TextField());
    	newScore.setAlignment(Pos.BOTTOM_CENTER);
    	holderLarge.getChildren().addAll(scoreholder, newScore);
    	pane.getChildren().addAll(holderLarge);
    	//scoreList.setAlignment(Pos.CENTER);
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
