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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Highscore extends StackPane implements Settings, Comparable<Highscore> {
	static ArrayList<Highscore> scores = new ArrayList<>();
	String name;
    Timer scoreValue;
    static TextField nameField = new TextField();
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
				String line = input.nextLine();
				String[] inputs = line.split("\\s", 2);
				Timer scoreValue = Timer.parse(inputs[0]);
				String name = inputs[1];
				new Highscore(scoreValue, name);
			}
			input.close();
		} catch (FileNotFoundException e) {
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
			e.printStackTrace();
		}
    }	
    public static void drawScores(Pane pane){
    	VBox holderLarge = new VBox();
    	GridPane scoreList = new GridPane();
    	for(int i = 0; i < scores.size(); i++){
    		Text nameText = new Text(scores.get(i).name);
    		Text scoreText = new Text(scores.get(i).scoreValue.toString());
    		nameText.setFont(FONT_TEXT);
    		nameText.setFill(TEXT_COLOR);
        	scoreText.setFont(FONT_TEXT);
        	scoreText.setFill(TEXT_COLOR);
    		scoreList.addRow(i, scoreText, nameText);
    	}
		scoreList.setHgap(10);
		scoreList.setAlignment(Pos.TOP_CENTER);
    	ScrollPane scoreholder = new ScrollPane();
    	/*scoreholder.setOnScroll(new EventHandler<ScrollEvent>() {
    		@Override
    		public void handle(ScrollEvent s){
       			//scoreholder.setVvalue(scoreholder.getVvalue() + s.getDeltaY());
       			scoreholder.setVvalue(50);
    		}
    	});*/
    	scoreList.setPrefSize(WIDTH, HEIGHT);
    	scoreholder.setContent(scoreList);
  
    	VBox newScore = new VBox();
    	//newScore.setBackground(BACKGROUND);
    	Label PlayerMessage = new Label("Enter your name to be added to the Highscore list.");
    	PlayerMessage.setTextFill(TEXT_COLOR);
    	PlayerMessage.setFont(FONT_TIMER);
    	newScore.getChildren().add(PlayerMessage);
    	nameField.setOnAction(null);
    	newScore.getChildren().add(nameField);
    	newScore.setAlignment(Pos.BOTTOM_CENTER);
    	scoreList.setBackground(BACKGROUND);
    	scoreholder.setBackground(BACKGROUND);
    	//newScore.setBackground(BACKGROUND);
    	holderLarge.getChildren().addAll(scoreholder,newScore);
    	pane.getChildren().addAll(holderLarge);
    	//scoreList.setAlignment(Pos.CENTER);
    	//pane = new BorderPane(scoreList, new Text("Scores:"), null, null, null);
    }
    
    public static void submit(Timer timer){
    	new Highscore(timer, nameField.getText());
    }
    
    public String toString(){
    	return scoreValue.toString() + " " + name;
    }

	@Override
	public int compareTo(Highscore score) {
		return scoreValue.compareTo(score.scoreValue);
	}
}
