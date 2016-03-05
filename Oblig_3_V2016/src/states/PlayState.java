package states;

import javafx.scene.input.MouseEvent;

public class PlayState extends ScreenState {

	public PlayState(){
		root.setPane(panes.get(0));
		ball.reset();
	}

	@Override
	public void onSpacePressed(){
		root.newState(new PauseState());
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
