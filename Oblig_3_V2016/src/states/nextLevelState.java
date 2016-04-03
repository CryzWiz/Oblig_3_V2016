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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {} //Should never be interrupted
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
