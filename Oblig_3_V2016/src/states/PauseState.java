package states;

public class PauseState extends ScreenState {

	public PauseState() {}

	@Override
	public void onSpacePressed(){
		root.newState(new PlayState());
	}
}
