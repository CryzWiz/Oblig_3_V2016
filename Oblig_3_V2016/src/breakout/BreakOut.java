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
		ScreenManager screenManager = new ScreenManager();
		Scene scene = screenManager.getScene();
		GameManager gameManager = new GameManager(screenManager);
		Robot robot = new Robot();

		scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				int x = (int) Math.min(Math.max(mouseEvent.getScreenX(), scene.getX() + primaryStage.getX()), scene.getX() + primaryStage.getX() + WIDTH);
				int y = (int) Math.min(Math.max(mouseEvent.getScreenY(), scene.getY() + primaryStage.getY()), scene.getY() + primaryStage.getY() + HEIGHT);
				robot.mouseMove(x, y);
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
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}

