import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class BreakOut extends Application{
  @Override 
  public void start(Stage primaryStage) {
    StackPane pane = new StackPane();
    
    

    
    Scene scene = new Scene(pane, 950, 750);
    scene.setFill(Color.BLACK);
    primaryStage.setTitle("BreakOut"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }




   /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
   public static void main(String[] args) {
   Application.launch(args);
   }

}
