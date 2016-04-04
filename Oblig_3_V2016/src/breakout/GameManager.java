package breakout;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import states.*;

public class GameManager implements Settings {
	private GameState state;

	public GameManager(ScreenManager sm) {
		GameState.setStatics(this, sm);
		//state = new MenuState();
		state = new VictoryState();
	}

	public void onMouseEvent(MouseEvent m){
		state.onMouseEvent(m);
	}
	public void onKeyEvent(KeyEvent k){
		if(k.getEventType() == KeyEvent.KEY_PRESSED){
			switch(k.getCode()){
				case SPACE:
					state.onSpacePress();
					break;
				case ESCAPE:
					state.onEscPress();
					break;
				case TAB:
					state.onTabPress();
					break;
				case ENTER:
					state.onEnterPress();
					break;
			default:
				break;
			}
		}
		if(k.getEventType() == KeyEvent.KEY_RELEASED){
			switch(k.getCode()){
			case SPACE:
				state.onSpaceRelease();
				break;
			default:
				break;
			}
		}
	}

	public boolean constrictMouse(){
		return state instanceof PlayState;
	}

	public void tick(){
		state.tick();
	}
	public void exit(){
		System.exit(0);
	}

	//State changes
	public void play(){
		state = new PlayState();
	}
	public void pause() {
		state = new PauseState();
	}
	public void gameOver() {
		state = new GameOverState();
	}
	public void menu() {
		state = new MenuState();
	}
	public void nextLevel() {
		state = new nextLevelState();
	}
	public void victory() {
		state = new VictoryState();
	}
}
