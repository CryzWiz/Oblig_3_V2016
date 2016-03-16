package states;

public class PauseState extends GameState {

	public PauseState(){
		screenManager.setPauseScreen();
		timer.pause();
	}

	@Override
	public void onSpacePress(){
		game.play();
	}
	
	@Override
	public void onEnterPress(){
		game.play();
	}
	
	@Override
	public void onEscPress(){
		game.menu();
	}
}
