package states;

import javafx.scene.input.MouseEvent;

public class nextLevelState extends GameState {

	public nextLevelState(){
		screenManager.updateLevel();
		screenManager.setNextLevelScreen();
		timer.pause();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			timer.reset();
			ballManager.reset();
			brickManager.reset();
			gameManager.play();
		}
	}
	
	@Override
	public void onEscPress(){
		gameManager.menu();
	};
}
