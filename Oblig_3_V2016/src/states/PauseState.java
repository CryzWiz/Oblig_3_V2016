package states;

public class PauseState extends GameState {

	public PauseState(){
		sManager.setPauseScreen();
	}

	@Override
	public void onSpacePressed(){
		root.play();
	}
	
	@Override
	public void onEnterPressed(){
		root.play();
	}
	
	@Override
	public void onEscPressed(){
		root.menu();
	}
}
