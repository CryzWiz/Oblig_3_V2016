package breakout;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ScreenManager implements Settings {
	private Scene scene;
	private VBox menuScreen;
	private StackPane gameScreen;
	private Pane playLayer; //Play is just part of the gameScreen, thus not named as a "screen"
	private Pane pauseLayer; //Pause is just part of the gameScreen, thus not named as a "screen"
	private Pane victoryLayer; //Victory is just part of the gameScreen, thus not named as a "screen"
	private BorderPane timerLayer;
	private Pane endScreen;
	private Pane victoryScreen;
	private Text timerText;
	private Pane shadowLayer;


	public ScreenManager(){
		//Texts
		Text endText = new Text("GAME OVER!");
		endText.setFill(TEXT_COLOR);
		endText.setFont(FONT_TEXT);
		Text pauseText = new Text("Game paused!");
		pauseText.setFill(TEXT_COLOR);
		pauseText.setFont(FONT_TEXT);
		Text menuText = new Text("Click to Start");
		menuText.setFill(TEXT_COLOR);
		menuText.setFont(FONT_TEXT);
		Text victoryText = new Text("Victory!");
		victoryText.setFill(TEXT_COLOR);
		victoryText.setFont(FONT_TEXT);
		timerText = new Text("00:00:00");
		timerText.setFont(FONT_TIMER);
		timerText.setFill(Color.WHITE);
	
		//lvl buttons
		 double Opacity = 0.2;
		 int level = BrickManager.Level.getvalue(); //Starting on lvl 0.
		 //Box 1
		 Rectangle lvl1Box = new Rectangle(0, 0, 65, 25);
		 lvl1Box.setFill(Color.RED);
		 Text lvl1Text = new Text(); 
		 lvl1Text.setText("lvl 1");
		 lvl1Text.setFont(Font.font(20));
		 lvl1Text.setFill(Color.BLACK);
		 StackPane levelBox1 = new StackPane(lvl1Box,lvl1Text);
		 levelBox1.setAlignment(Pos.TOP_RIGHT);
		 //Box 2
		 Rectangle lvl2Box = new Rectangle(0, 0, 65, 25);
		 lvl2Box.setFill(Color.PURPLE);
		 if(level <= 2){
			 lvl2Box.setOpacity(Opacity);
		 }
		 
		 Text lvl2Text = new Text();
		 lvl2Text.setText("lvl 2");
		 lvl2Text.setFont(Font.font(20));
		 lvl2Text.setFill(Color.BLACK);
		 StackPane levelBox2 = new StackPane(lvl2Box,lvl2Text);
		 levelBox2.setAlignment(Pos.TOP_RIGHT);
		 //Box 3
		 Rectangle lvl3Box = new Rectangle(0, 0, 65, 25);
		 lvl3Box.setFill(Color.BLUE);
		 if(level <= 3){
			 lvl3Box.setOpacity(Opacity);
		 }
		 Text lvl3Text = new Text();
		 lvl3Text.setText("lvl 3");
		 lvl3Text.setFont(Font.font(20));
		 lvl3Text.setFill(Color.BLACK);
		 StackPane levelBox3 = new StackPane(lvl3Box,lvl3Text);
		 levelBox3.setAlignment(Pos.TOP_RIGHT);
		 
		 HBox lvls = new HBox(levelBox1, levelBox2, levelBox3);
		//lvl buttons stops here. Should we put in its in own class? Should it be never-ending, or are 3 levels enough?
		//Still to do - Needs a function that removes/sets opacity when its round win / game over.
		
		//Game Pane + pause and victory
		playLayer = new Pane();
		timerLayer = new BorderPane();
		timerLayer.setBottom(timerText);
		Rectangle shadow = new Rectangle(0, 0, WIDTH, HEIGHT); 
		shadowLayer = new Pane(shadow);
		shadowLayer.setOpacity(0.7);
		pauseLayer = new StackPane(pauseText);
		victoryLayer = new StackPane(victoryText);
		//gameScreen = new StackPane(playLayer, shadowLayer, pauseLayer, victoryLayer, timerLayer);
		gameScreen = new StackPane(playLayer, timerLayer, shadowLayer, pauseLayer, victoryLayer, lvls);
		gameScreen.setBackground(BACKGROUND);
		
		//GameOver Pane
		endScreen = new StackPane(endText);
		endScreen.setBackground(BACKGROUND);

		//Main Menu Pane
		Button newGame = new Button("New Game");
		menuScreen = new VBox(menuText, newGame);
		menuScreen.setAlignment(Pos.CENTER);
		menuScreen.setBackground(BACKGROUND);

		scene = new Scene(menuScreen, WIDTH, HEIGHT);
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public Pane getPlayLayer(){
		return playLayer;
	}
	public Pane getGameScreen(){
		return gameScreen;
	}
	public Pane getMenuScreen(){
		return menuScreen;
	}
	public Pane getGameOverScreen(){
		return endScreen;
	}
	public Pane getVictoryScreen(){
		return victoryScreen;
	}
	public Text getTimerText(){
		return timerText;
	}
	public void setPauseOpacity(double opacity){
		pauseLayer.setOpacity(opacity);
	}
	public void setShadowOpacity(double opacity){
		shadowLayer.setOpacity(opacity);
	}
	public void setVictoryOpacity(double opacity){
		victoryLayer.setOpacity(opacity);
	}

	public void setPlayScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0);
		setPauseOpacity(0);
		setVictoryOpacity(0);
		BorderPane.setAlignment(timerText, Pos.BOTTOM_LEFT);
	}
	public void setPauseScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0.7);
		setPauseOpacity(1);
		setVictoryOpacity(0);
		//BorderPane.setAlignment(timerText, Pos.CENTER);
	}
	public void setVictoryScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0.7);
		setPauseOpacity(0);
		setVictoryOpacity(1);
		BorderPane.setAlignment(timerText, Pos.TOP_CENTER);
	}
	public void setMenuScreen(){
		scene.setRoot(menuScreen);
	}
	public void setGameOverScreen(){
		scene.setRoot(endScreen);
	}
}
