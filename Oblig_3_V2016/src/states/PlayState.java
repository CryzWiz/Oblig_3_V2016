package states;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import static breakout.Racket.Direction.*;

import breakout.Level;
import breakout.Settings;

public class PlayState extends GameState {

	public PlayState(){
		screenManager.setPlayScreen();
		timer.play();
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

	public void onUpPress(){
		ballManager.add(200, 30, Settings.BALL_START_DX, Settings.BALL_START_DY); //Only for testing
	};
	@Override
	public void onEnterPress(){
		gameManager.pause();
	}
	@Override
	public void onEscPress(){
		gameManager.menu();
	}
	@Override
	public void onSpacePress(){
		if(racket.isFriction())
			racket.setSlippery(false);
	}
	@Override
	public void onSpaceRelease(){
		racket.setSlippery(true);
	}

	@Override
	public void tick(){
		ballManager.tick();
		if (brickManager.levelCleared()){
			brickManager.setNextLevel();
			if(Level.getLevel() == Level.VICTORY)
				gameManager.victory();
			else
				gameManager.nextLevel();
		}
	}
}
