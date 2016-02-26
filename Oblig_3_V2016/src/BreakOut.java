import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class BreakOut extends Application{
  @Override 
  public void start(Stage primaryStage) {
	  Pane pane = new Pane();
	  
	  
	  Rectangle[][] rectangles = new Rectangle[10][10];
	  for(int y = 0; y < 10; y++){
		  for(int x = 0; x < 10; x++){
	    	rectangles[y][x]= new Rectangle(x*(100+3), y*(20+3), 100, 20);
	    	pane.getChildren().add(rectangles[y][x]);
		  }
	  }
	  for(int x = 0; x < 10; x++){
	    rectangles[0][x].setFill(Color.BLUE);
	    rectangles[1][x].setFill(Color.BLUE);
	    rectangles[2][x].setFill(Color.BLUE);
	    rectangles[3][x].setFill(Color.GREEN);
	    rectangles[4][x].setFill(Color.GREEN);
	    rectangles[5][x].setFill(Color.GREEN);
	    rectangles[6][x].setFill(Color.YELLOW);
	    rectangles[7][x].setFill(Color.YELLOW);
	    rectangles[8][x].setFill(Color.YELLOW);
	    rectangles[9][x].setFill(Color.RED);
	    
	    }

    
    Scene scene = new Scene(pane, 1030, 830);
    scene.setFill(Color.BLACK);
    primaryStage.setTitle("BreakOut"); 
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
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
