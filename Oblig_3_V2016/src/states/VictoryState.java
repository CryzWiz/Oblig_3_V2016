package states;

import javafx.scene.input.MouseEvent;

public class VictoryState extends GameState {

	public VictoryState(){
		screenManager.setVictoryScreen();
		pauseTimer();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			resetTimer();
			game.play();
			ball.reset();
			brickManager.reset();
		}
	}
	
	@Override
	public void onEscPressed(){
		System.exit(0);
	};

	@Override
	public void tick(){
		//root.newState(new PlayState());
	}
}
