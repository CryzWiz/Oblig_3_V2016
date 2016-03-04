package states;

public class PlayState extends ScreenState {

  public PlayState() {
    // TODO Auto-generated constructor stub
  }

  public void onSpacePressed(){
    manager.newState(new PauseState());
  }
}
