package states;

import javafx.scene.input.MouseEvent;

public class GameOverState extends GameState {

	public GameOverState(){
		sManager.setGameOverScreen();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			game.play();
			ball.reset();
			bManager.reset();
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
