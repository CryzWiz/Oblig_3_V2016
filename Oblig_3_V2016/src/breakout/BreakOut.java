package breakout;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class BreakOut extends Application implements Settings{
	@Override 
	public void start(Stage primaryStage) throws AWTException {
		Highscore.loadScores();
		ScreenManager screenManager = new ScreenManager();
		Scene scene = screenManager.getScene();
		GameManager gameManager = new GameManager(screenManager);
		Robot robot = new Robot();

		scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if(gameManager.constrictMouse()){
					int x = (int) Math.min(Math.max(mouseEvent.getScreenX(), scene.getX() + primaryStage.getX() + PADDLE_WIDTH / 2), scene.getX() + primaryStage.getX() + WIDTH - PADDLE_WIDTH / 2);
					int y = (int) Math.min(Math.max(mouseEvent.getScreenY(), scene.getY() + primaryStage.getY() + 10), scene.getY() + primaryStage.getY() + HEIGHT - 10);
					robot.mouseMove(x, y);
				}
				gameManager.onMouseEvent(mouseEvent);
			}
		});
		scene.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent k){
				gameManager.onKeyEvent(k);
			}
		});

		EventHandler<ActionEvent> eventHandler = e -> {
			gameManager.tick();
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(MILLIS_PER_FRAME), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		primaryStage.setTitle("BreakOut"); 
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}

