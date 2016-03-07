package breakout;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import states.*;

public class GameManager implements StateUser {
	GameState state;

	public GameManager(ScreenManager sm) {
		GameState.setStatics(this, sm);
		state = new MenuState();
	}

	public void onMouseEvent(MouseEvent m){
		state.onMouseEvent(m);
	}
	
	public void onKeyEvent(KeyEvent k){
		if(k.getEventType() == KeyEvent.KEY_PRESSED){
			switch(k.getCode()){
				case SPACE:
					state.onSpacePressed();
					break;
				case ESCAPE:
					state.onEscPressed();
					break;
				case ENTER:
					state.onEnterPressed();
					break;
				case UP:
					state.onUpPressed();
					break;
			default:
				break;
			}
		}
	}

	public void tick(){
		state.tick();
	}

	@Override
	public void play() {
		state = new PlayState();
	}

	@Override
	public void pause() {
		state = new PauseState();
	}

	@Override
	public void gameOver() {
		state = new GameOverState();
	}

	@Override
	public void menu() {
		state = new MenuState();
	}
}
