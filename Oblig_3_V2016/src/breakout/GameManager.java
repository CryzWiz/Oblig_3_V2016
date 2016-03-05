package breakout;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import states.*;

public class GameManager implements StateUser {
  ScreenState state;
  Scene scene;

  public GameManager(Scene s, ArrayList<Pane> panes, BrickManager m, Racket ra, Ball b, Room ro) {
    ScreenState.setStatics(this, panes, m, ra, b, ro);
    scene = s;
    state = new PlayState();
  }
  
  public void newState(ScreenState state){
    this.state = state;
  }
  
  public void setPane(Pane pane){
    scene.setRoot(pane);
  }
  
  public void onMouseEvent(MouseEvent m){
    state.onMouseEvent(m);
  }
  
  public void tick(){
    state.tick();
  }
}
