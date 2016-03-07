package states;

import javafx.scene.input.MouseEvent;

public class GameOverState extends ScreenState {

	public GameOverState(){
		root.setPane(panes.get(1));
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			root.newState(new PlayState());
			ball.reset();
			bManager.reset();
		}
	}
	
	@Override
	public void onEscPressed(){
		System.exit(0);
	};

	@Override
	public void tick(){
		//root.newState(new PlayState());
	}
}
