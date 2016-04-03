package breakout;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static breakout.Level.*;

import breakout.MenuButton.Colors;

public class ScreenManager implements Settings {
	private Scene scene;
	private Pane menuScreen;
	private VBox buttons;
	private StackPane gameScreen;
	private Pane playLayer; //Play is just part of the gameScreen, thus not named as a "screen"
	private Pane pauseLayer; //Pause is just part of the gameScreen, thus not named as a "screen"
	private Pane victoryLayer; //Victory is just part of the gameScreen, thus not named as a "screen"
	private BorderPane timerLayer;
	private Pane endScreen;
	private Pane victoryScreen;
	private Text timerText;
	private Pane shadowLayer;
	private Pane nextLevelLayer;

	public Text lvl1Text;
	public Text lvl2Text;
	public Text lvl3Text;
	public Rectangle lvl1Box;
	public Rectangle lvl2Box;
	public Rectangle lvl3Box;
	public StackPane levelIcon1;
	public StackPane levelIcon2;
	public StackPane levelIcon3;
	public HBox lvls;

	public ScreenManager(){
		//Texts
		Text endText = new Text("GAME OVER!");
		endText.setFill(TEXT_COLOR);
		endText.setFont(FONT_TEXT);
		Text pauseText = new Text("Game paused!");
		pauseText.setFill(TEXT_COLOR);
		pauseText.setFont(FONT_TEXT);
		Text menuText = new Text("Breakout - The Game");
		menuText.setFill(TEXT_COLOR);
		menuText.setFont(FONT_TEXT);
		Text nextLevelText = new Text("Congratulations - Level cleared!");
		nextLevelText.setFill(TEXT_COLOR);
		nextLevelText.setFont(FONT_TEXT);
		timerText = new Text("00:00:00");
		timerText.setFont(FONT_TIMER);
		timerText.setFill(Color.WHITE);
	
		//lvl Icons
		//Icon 1
		lvl1Box = new Rectangle(LEVELICONS_WIDTH, LEVELICONS_HEIGHT);
		lvl1Box.setFill(Color.RED);
		lvl1Text = new Text(); 
		lvl1Text.setText("Level 1");
		lvl1Text.setFont(FONT_LVL_BUTTON);
		lvl1Text.setFill(BUTTON_LVL_TEXT_COLOR);
		levelIcon1 = new StackPane(lvl1Box,lvl1Text);
		levelIcon1.setAlignment(Pos.TOP_RIGHT);
		//Icon 2
		lvl2Box = new Rectangle(LEVELICONS_WIDTH, LEVELICONS_HEIGHT);
		lvl2Box.setFill(Color.PURPLE);
		lvl2Text = new Text();
		lvl2Text.setText("Level 2");
		lvl2Text.setFont(FONT_LVL_BUTTON);
		lvl2Text.setFill(BUTTON_LVL_TEXT_COLOR);
		levelIcon2 = new StackPane(lvl2Box,lvl2Text);
		levelIcon2.setAlignment(Pos.TOP_RIGHT);
		//Icon 3
		lvl3Box = new Rectangle(LEVELICONS_WIDTH, LEVELICONS_HEIGHT);
		lvl3Box.setFill(Color.BLUE);
		lvl3Text = new Text();
		lvl3Text.setText("Level 3");
		lvl3Text.setFont(FONT_LVL_BUTTON);
		lvl3Text.setFill(BUTTON_LVL_TEXT_COLOR);
		levelIcon3 = new StackPane(lvl3Box,lvl3Text);
		levelIcon3.setAlignment(Pos.TOP_RIGHT);
		lvls = new HBox(levelIcon1, levelIcon2, levelIcon3);
		
		//Game Pane + pause and victory
		nextLevelLayer = new StackPane(nextLevelText);
		playLayer = new Pane(lvls);
		timerLayer = new BorderPane();
		timerLayer.setBottom(timerText);
		Rectangle shadow = new Rectangle(WIDTH, HEIGHT); 
		shadowLayer = new StackPane(shadow);
		shadowLayer.setOpacity(SHADOW_OPACITY);
		pauseLayer = new StackPane(pauseText);
		victoryLayer = new StackPane();
		//Victoryscreen
		Highscore.drawScores(victoryLayer);
		gameScreen = new StackPane(playLayer, shadowLayer, timerLayer, pauseLayer, victoryLayer, nextLevelLayer);
		gameScreen.setBackground(BACKGROUND);

		
		//GameOver Pane
		endScreen = new StackPane(endText);
		endScreen.setBackground(BACKGROUND);

		//Main Menu Pane
		buttons = new VBox(
				new MenuButton("New Game!", Colors.GREEN),
				new MenuButton("Continue...", Colors.GREEN),
				new MenuButton("Ball Speed: Normal", Colors.YELLOW),
				new MenuButton("Friction: ON", Colors.BLUE),
				new MenuButton("Exit Game", Colors.RED)
				);
		//((MenuButton)buttons.getChildren().get(0)).setAlignment(Pos.CENTER);
		menuScreen = new BorderPane(buttons, menuText, null, null, null);
		menuScreen.setBackground(BACKGROUND);
		buttons.setMaxSize(BUTTON_WIDTH, 5*BUTTON_HEIGHT);
		buttons.setAlignment(Pos.CENTER);

		scene = new Scene(menuScreen, WIDTH, HEIGHT);
		updateLevel();
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
	public VBox getButtons(){
		return buttons;
	}
	public Text getTimerText(){
		return timerText;
	}

	public void updateLevel(){
		switch(getLevel()) {
		case ONE:
			lvl1Box.setOpacity(LEVELICONS_OPACITY_OFF);
			lvl2Box.setOpacity(LEVELICONS_OPACITY_ON);
			lvl3Box.setOpacity(LEVELICONS_OPACITY_ON);
			break;
		case TWO:
			lvl1Box.setOpacity(LEVELICONS_OPACITY_OFF);
			lvl2Box.setOpacity(LEVELICONS_OPACITY_OFF);
			lvl3Box.setOpacity(LEVELICONS_OPACITY_ON);
			break;
		case THREE:
			lvl1Box.setOpacity(LEVELICONS_OPACITY_OFF);
			lvl2Box.setOpacity(LEVELICONS_OPACITY_OFF);
			lvl3Box.setOpacity(LEVELICONS_OPACITY_OFF);
			break;
		case VICTORY:
			break;
		}
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
	public void setnextLevelOpacity(double opacity){
		nextLevelLayer.setOpacity(opacity);
	}

	public void setPlayScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0);
		setPauseOpacity(0);
		setVictoryOpacity(0);
		setnextLevelOpacity(0);
		timerLayer.setOpacity(1);
		BorderPane.setAlignment(timerText, Pos.BOTTOM_LEFT);
	}
	public void setPauseScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0.7);
		setPauseOpacity(1);
		setVictoryOpacity(0);
		BorderPane.setAlignment(timerText, Pos.CENTER);
	}
	public void setVictoryScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(1);
		setPauseOpacity(0);
		setVictoryOpacity(1);
		setnextLevelOpacity(0);
		timerLayer.setOpacity(0);
		BorderPane.setAlignment(timerText, Pos.TOP_CENTER);
	}
	public void setNextLevelScreen(){
		scene.setRoot(gameScreen);
		setShadowOpacity(0.7);
		setPauseOpacity(0);
		setVictoryOpacity(0);
		setnextLevelOpacity(1);
		BorderPane.setAlignment(timerText, Pos.TOP_CENTER);
	}
	public void setMenuScreen(){
		scene.setRoot(menuScreen);
	}
	public void setGameOverScreen(){
		scene.setRoot(endScreen);
	}
}
