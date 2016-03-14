package breakout;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScreenManager implements Settings {
	private Scene scene;
	private VBox menuScreen;
	private StackPane gameScreen;
	private Pane playLayer; //Play is just part of the gameScreen, thus not named as a "screen"
	private Pane pauseLayer; //Pause is just part of the gameScreen, thus not named as a "screen"
	private Pane endScreen;
	private Pane victoryScreen;

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

		//Game Pane
		playLayer = new Pane();
		Rectangle shadow = new Rectangle(0, 0, WIDTH, HEIGHT); 
		Pane shadowLayer = new Pane(shadow);
		shadowLayer.setOpacity(0.7);
		pauseLayer = new StackPane(shadowLayer, pauseText);
		gameScreen = new StackPane(playLayer, pauseLayer);
		gameScreen.setBackground(BACKGROUND);

		//GameOver Pane
		endScreen = new StackPane(endText);
		endScreen.setBackground(BACKGROUND);
		
		//Victory Pane
		victoryScreen = new StackPane(victoryText);
		victoryScreen.setBackground(BACKGROUND);

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
	public void setPauseOpacity(double opacity){
		pauseLayer.setOpacity(opacity);
	}

	public void setPlayScreen(){
		scene.setRoot(gameScreen);
		setPauseOpacity(0);
	}
	public void setPauseScreen(){
		scene.setRoot(gameScreen);
		setPauseOpacity(1);
	}
	public void setMenuScreen(){
		scene.setRoot(menuScreen);
	}
	public void setGameOverScreen(){
		scene.setRoot(endScreen);
	}
	public void setVictoryScreen(){
		scene.setRoot(victoryScreen);
	}
}
