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
	public static Text lvl1Text;
	public static Text lvl2Text;
	public static Text lvl3Text;
	public static Rectangle lvl1Box;
	public static Rectangle lvl2Box;
	public static Rectangle lvl3Box;
	public static StackPane levelBox1;
	public static StackPane levelBox2;
	public static StackPane levelBox3;
	public static HBox lvls;
	
	
	
	public ScreenManager(){
		//Texts
		Font font = Font.font(FONT_SIZE_GAMEOVER);
		Text endText = new Text("GAME OVER!");
		endText.setFill(TEXT_COLOR);
		endText.setFont(font);
		Text pauseText = new Text("Game paused!");
		pauseText.setFill(TEXT_COLOR);
		pauseText.setFont(font);
		Text menuText = new Text("Click to Start");
		menuText.setFill(TEXT_COLOR);
		menuText.setFont(font);
		Text victoryText = new Text("Victory!");
		victoryText.setFill(TEXT_COLOR);
		victoryText.setFont(font);
		timerText = new Text("00:00:00");
		timerText.setFont(Font.font(30));
		timerText.setFill(Color.WHITE);
	
		//lvl buttons
		 
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
		
		 Text lvl2Text = new Text();
		 lvl2Text.setText("lvl 2");
		 lvl2Text.setFont(Font.font(20));
		 lvl2Text.setFill(Color.BLACK);
		 StackPane levelBox2 = new StackPane(lvl2Box,lvl2Text);
		 levelBox2.setAlignment(Pos.TOP_RIGHT);
		 //Box 3
		 Rectangle lvl3Box = new Rectangle(0, 0, 65, 25);
		 lvl3Box.setFill(Color.BLUE);
		 
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
	}
	public void setPauseScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0.7);
		setPauseOpacity(1);
		setVictoryOpacity(0);
	}
	public void setVictoryScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0.7);
		setPauseOpacity(0);
		setVictoryOpacity(1);
	}
	public void setMenuScreen(){
		scene.setRoot(menuScreen);
	}
	public void setGameOverScreen(){
		scene.setRoot(endScreen);
	}
	public static void setLevelIcon(){
		/*
		if(BrickManager.Level.getvalue() == 1){
			lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_ON);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
		}
		else if(BrickManager.Level.getvalue() == 2){
			lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
		}
		else if(BrickManager.Level.getvalue() == 3){
			lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_OFF);
		}
		else{
			lvl1Box.setOpacity(OPACITY_LEVELICONS_ON);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_ON);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
		}
		*/
		
		/*
		switch(BrickManager.Level.getvalue()){
		case 1:
			lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_ON);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
			
		case 2:
			lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
			
		case 3:
			lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_OFF);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_OFF);
			
		default:
			lvl1Box.setOpacity(OPACITY_LEVELICONS_ON);
			lvl2Box.setOpacity(OPACITY_LEVELICONS_ON);
			lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
			
		}
		*/
 	}
	
}
