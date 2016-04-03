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
			gameManager.menu();
		}
	}
	
	@Override
	public void onSpacePress() {
		gameManager.menu();
	}
	@Override
	public void onEscPress(){
		gameManager.menu();
	}

	@Override
	public void tick(){
	}
}
