package states;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PlayState extends GameState {

	public PlayState(){
		screenManager.setPlayScreen();
		timer.play();
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
		if(m.getEventType() == MouseEvent.MOUSE_RELEASED){
			racket.setNoTilt();
		}
		if(m.getEventType() == MouseEvent.MOUSE_MOVED || m.getEventType() == MouseEvent.MOUSE_DRAGGED){
			racket.mouseMove(m.getX());
		}
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			if(m.getButton() == MouseButton.PRIMARY){
				racket.setLeftTilt();
			}
			else if(m.getButton() == MouseButton.SECONDARY){
				racket.setRightTilt();
			}
		}
	}

	@Override
	public void tick(){
		ball.tick();
		brickManager.tick(game, ball);
		racket.collision(ball);
		if(room.collision(ball)){
			game.gameOver();
		}
	}
}
