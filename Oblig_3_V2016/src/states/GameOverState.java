package states;

import javafx.scene.input.MouseEvent;

public class GameOverState extends ScreenState {

	public GameOverState(){
		root.setPane(panes.get(1));
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED)
			root.newState(new PlayState());
	}

	@Override
	public void tick(){
		//root.newState(new PlayState());
	}
}
