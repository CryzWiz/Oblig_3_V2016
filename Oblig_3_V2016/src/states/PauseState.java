package states;

public class PauseState extends GameState {

	public PauseState(){
		screenManager.setPauseScreen();
		timer.pause();
	}

	@Override
	public void onSpacePress(){
		gameManager.play();
	}
	
	@Override
	public void onEnterPress(){
		gameManager.play();
	}
	
	@Override
	public void onEscPress(){
		gameManager.menu();
	}
}
