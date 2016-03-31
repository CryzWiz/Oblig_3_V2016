package breakout;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import states.*;

public class GameManager implements Settings {
	private GameState state;
	private int speed;
	private boolean friction;

	public GameManager(ScreenManager sm) {
		GameState.setStatics(this, sm);
		state = new MenuState();
		speed = 1;
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
				case ENTER:
					state.onEnterPress();
					break;
				case UP:
					state.onUpPress();
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

	public boolean isFriction(){
		return friction;
	}

	public void tick(){
		state.tick();
	}
	
	public void play(){
		state = new PlayState();
	}
	public void play(Ball ball) {
		state = new PlayState();
		double dx, dy;
		switch(speed){
		case 0:
			dx = BALL_START_DX * 0.5;
			dy = BALL_START_DY * 0.5;
			break;
		case 2:
			dx = BALL_START_DX * 1.5;
			dy = BALL_START_DY * 1.5;
			break;
		default:
			dx = BALL_START_DX;
			dy = BALL_START_DY;
			break;
		}
		ball.setSpeedCoords(dx, dy);
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

	public void toggleSpeed(Ball ball){
		speed++;
		speed %= 3;
		String speedText = "";
		switch(speed){
		case 0:
			speedText = "Slow";
			break;
		case 1:
			speedText = "Normal";
			break;
		case 2:
			speedText = "Fast";
			break;
		}
		MenuButton.setText(2, "Ball Speed: " + speedText);
	}
	public void toggleFriction(){
		friction = !friction;
		MenuButton.setText(3, "Friction: " + (friction ? "ON" : "OFF"));
	}
	public void nextLevel() {
		state = new nextLevelState();
	}
	public void victory() {
		state = new VictoryState();
	}
	public void levelCleared() {
		state = new nextLevelState();
	}
	public void exit(){
		System.exit(0);
	}
}
