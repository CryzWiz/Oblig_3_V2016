package states;

import javafx.scene.input.MouseEvent;

public class MenuState extends GameState {

	public MenuState(){
		sManager.setMenuScreen();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			game.play();
			bManager.reset();
			ball.reset();
			resetTimer();
		}
	}

	@Override
	public void onEnterPressed(){
		game.play();
		bManager.reset();
		ball.reset();
		resetTimer();
	}
	
	@Override
	public void onEscPressed(){
		game.exit();
	}
}
