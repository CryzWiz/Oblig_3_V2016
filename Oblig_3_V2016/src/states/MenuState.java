package states;

import javafx.scene.input.MouseEvent;

public class MenuState extends ScreenState {

	public MenuState(){
		root.setPane(panes.get(2));
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			root.newState(new PlayState());
			ball.reset();
		}
	}

	@Override
	public void onEnterPressed(){
		root.newState(new PlayState());
		bManager.reset();
		ball.reset();
	}
}
