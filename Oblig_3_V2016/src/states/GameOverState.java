package states;

import javafx.scene.input.MouseEvent;

public class GameOverState extends GameState {

	public GameOverState(){
		screenManager.setGameOverScreen();
		timer.pause();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_PRESSED){
			game.menu();
		}
	}
	
	@Override
	public void onSpacePress() {
		game.menu();
	}
	
	@Override
	public void onEscPress(){
		game.menu();
	}

	@Override
	public void tick(){
	}
}
