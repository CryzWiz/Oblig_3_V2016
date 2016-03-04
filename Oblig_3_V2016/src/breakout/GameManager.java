package breakout;

import states.MenuState;
import states.ScreenState;

public class GameManager {
  ScreenState state;

  public GameManager() {
    state = new MenuState();
  }
  
  public void newState(ScreenState state){
    this.state = state;
  }

}
