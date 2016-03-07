package breakout;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BreakOut extends Application implements Settings{
	Pane playPane;

	@Override 
	public void start(Stage primaryStage) {
		ArrayList<Pane> panes = new ArrayList<>();
		playPane = new Pane();
		makePanes(panes);
		Scene scene = new Scene(panes.get(0), WIDTH, HEIGHT);
		GameManager gManager = new GameManager(
				scene,
				panes,
				new BrickManager(playPane),
				new Racket(playPane),
				new Ball(playPane),
				new Room(scene));

		scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				gManager.onMouseEvent(mouseEvent);
			}
		});
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent k){
				gManager.onKeyEvent(k);
			}
		});

		EventHandler<ActionEvent> eventHandler = e -> {
			gManager.tick();
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(MILLIS_PER_FRAME), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		primaryStage.setTitle("BreakOut"); 
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	private void makePanes(ArrayList<Pane> panes){
		//Texts
		Font font = Font.font(FONT_SIZE_GAMEOVER);
		Text endText = new Text("GAME OVER!");
		endText.setFill(TEXT_COLOR_GAMEOVER);
		endText.setFont(font);
		Text pauseText = new Text("Game paused!");
		pauseText.setFill(TEXT_COLOR_GAMEOVER);
		pauseText.setFont(font);
		Text menuText = new Text("Click to Start");
		menuText.setFill(TEXT_COLOR_GAMEOVER);
		menuText.setFont(font);

		//Game Pane
		Rectangle shade = new Rectangle(0, 0, WIDTH, HEIGHT); 
		Pane bg = new Pane(playPane);
		Pane mg = new Pane(shade);
		StackPane fg = new StackPane(mg, pauseText);
		mg.setOpacity(0.7);
		StackPane gamePane = new StackPane(bg, fg);
		gamePane.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));
		panes.add(gamePane);

		//Game Over Pane
		panes.add(new StackPane(endText));

		//Main Menu Pane
		Button newGame = new Button("New Game");
		newGame.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));
		VBox buttonBox = new VBox(menuText, newGame);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setStyle("-fx-background: #000000");
		panes.add(buttonBox);
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}

