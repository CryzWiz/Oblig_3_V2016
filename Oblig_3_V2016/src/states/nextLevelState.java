package states;

import breakout.ScreenManager;
import javafx.scene.input.MouseEvent;

public class nextLevelState extends GameState {

	public nextLevelState(){
		ScreenManager.updateLevel();
		screenManager.setNextLevelScreen();
		timer.pause();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			timer.reset();
			game.play();
			ball.reset();
			brickManager.reset();
		}
	}
	
	@Override
	public void onEscPress(){
		System.exit(0);
	};

	@Override
	public void tick(){
		//root.newState(new PlayState());
	}
}
