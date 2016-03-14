package states;

import javafx.scene.input.MouseEvent;

public class PlayState extends GameState {

	public PlayState(){
		sManager.setPlayScreen();
		playTimer();
	}

	@Override
	public void onSpacePressed(){
		game.pause();
	}
	
	@Override
	public void onEscPressed(){
		game.menu();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_MOVED){
			racket.setpadX(m.getX());
		}
	}

	@Override
	public void tick(){
		ball.tick();
		bManager.tick(game, ball);
		if(room.collision(ball)){
			game.gameOver();
		}
	}
}
