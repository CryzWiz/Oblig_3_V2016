package states;

public class PauseState extends ScreenState {

  public PauseState() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void onSpacePressed(){
    manager.newState(new PlayState());
  }
}
