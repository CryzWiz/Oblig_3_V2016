package states;

import javafx.scene.input.MouseEvent;

public class MenuState extends GameState {

	public MenuState(){
		sManager.setMenuScreen();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			root.play();
			bManager.reset();
			ball.reset();
		}
	}

	@Override
	public void onEnterPressed(){
		root.play();
		bManager.reset();
		ball.reset();
	}
}
