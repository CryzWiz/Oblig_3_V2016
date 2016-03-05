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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BreakOut extends Application implements Settings{

  @Override 
  public void start(Stage primaryStage) {
    ArrayList<Pane> panes = new ArrayList<>();
    panes.add(new Pane());
    panes.add(new StackPane());
    Text gameOver = new Text("GAME OVER!");
    gameOver.setFill(TEXT_COLOR_GAMEOVER);
    gameOver.setFont(Font.font(FONT_SIZE_GAMEOVER));

    Scene scene = new Scene(panes.get(0), WIDTH, HEIGHT);
    panes.get(1).getChildren().add(gameOver);

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
