package states;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PlayState extends ScreenState {

	public PlayState(){
		root.setPane(panes.get(0));
		((Pane)panes.get(0).getChildren().get(1)).setOpacity(0);
	}

	@Override
	public void onSpacePressed(){
		root.newState(new PauseState());
	}
	
	@Override
	public void onEscPressed(){
		root.newState(new MenuState());
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_MOVED){
			racket.setpadX(m.getX());
		}
	}

	@Override
	public void tick(){
		ball.tick();
		bManager.collision(ball);
		if(room.collision(ball)){
			root.newState(new GameOverState());
		}
	}
}
