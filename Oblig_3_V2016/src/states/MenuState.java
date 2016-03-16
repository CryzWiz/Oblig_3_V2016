package states;

import javafx.scene.input.MouseEvent;

public class MenuState extends GameState {

	public MenuState(){
		screenManager.setMenuScreen();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			game.play();
			brickManager.reset();
			ball.reset();
			timer.reset();
		}
	}

	@Override
	public void onEnterPress(){
		game.play();
		brickManager.reset();
		ball.reset();
		timer.reset();
	}
	
	@Override
	public void onEscPress(){
		game.exit();
	}
}
