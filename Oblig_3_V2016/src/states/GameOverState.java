package states;

import javafx.scene.input.MouseEvent;

public class GameOverState extends GameState {

	public GameOverState(){
		screenManager.setGameOverScreen();
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
		game.exit();
	};

	@Override
	public void tick(){
	}
}
