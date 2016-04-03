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
			//ball.reset();
			game.play();
			brickManager.reset();
		}
	}
	
	@Override
	public void onEscPress(){
		game.menu();
	};

	@Override
	public void tick(){
		//root.newState(new PlayState());
	}
}
