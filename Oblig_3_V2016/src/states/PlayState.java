package states;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import static breakout.Racket.Direction.*;

public class PlayState extends GameState {

	public PlayState(){
		screenManager.setPlayScreen();
		timer.play();
	}

	@Override
	public void onEnterPress(){
		game.pause();
	}
	
	@Override
	public void onEscPress(){
		game.menu();
	}
	
	@Override
	public void onSpacePress(){
		if(game.isFriction())
			racket.setSlippery(false);
	}

	@Override
	public void onSpaceRelease(){
		racket.setSlippery(true);
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_RELEASED){
			racket.setTilt(STILL);
		}
		if(m.getEventType() == MouseEvent.MOUSE_PRESSED){
			if(m.getButton() == MouseButton.PRIMARY){
				racket.setTilt(LEFT);
			}
			else if(m.getButton() == MouseButton.SECONDARY){
				racket.setTilt(RIGHT);
			}
		}
		if(m.getEventType() == MouseEvent.MOUSE_MOVED || m.getEventType() == MouseEvent.MOUSE_DRAGGED){
			racket.mouseMove(m.getX());
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
