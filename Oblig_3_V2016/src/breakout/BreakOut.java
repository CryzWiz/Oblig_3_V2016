package breakout;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BreakOut extends Application implements Settings{

	@Override 
	public void start(Stage primaryStage) {
		ArrayList<Pane> panes = new ArrayList<>();

		//Game Scene (Pane)
		panes.add(new Pane());
		Scene scene = new Scene(panes.get(0), WIDTH, HEIGHT);

		//Game Over Pane
		panes.add(new StackPane());
		Text gameOver = new Text("GAME OVER!");
		gameOver.setFill(TEXT_COLOR_GAMEOVER);
		gameOver.setFont(Font.font(FONT_SIZE_GAMEOVER));
		panes.get(1).getChildren().add(gameOver);

		//Main Menu Pane
		Text menuText = new Text("Click to Start");
		menuText.setFill(TEXT_COLOR_GAMEOVER);
		menuText.setFont(Font.font(FONT_SIZE_GAMEOVER));
		panes.add(new VBox());
		panes.get(2).getChildren().add(menuText);
		/*VBox box = new VBox(new Button("Start"));
		panes.add(box);
		box.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));*/

		GameManager gManager = new GameManager(
				scene,
				panes,
				new BrickManager(panes.get(0)),
				new Racket(panes.get(0)),
				new Ball(panes.get(0)),
				new Room(scene));

		scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				gManager.onMouseEvent(mouseEvent);
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

	public static void main(String[] args) {
		Application.launch(args);
	}

}

