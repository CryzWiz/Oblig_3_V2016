package states;

import java.util.List;

import breakout.Level;
import breakout.MenuButton;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuState extends GameState {

	public MenuState(){
		screenManager.setMenuScreen();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		Pane menu = screenManager.getButtons();
		List<Node> buttons = menu.getChildren();
		double x = m.getSceneX() - menu.localToScene(menu.getBoundsInLocal()).getMinX();
		double y = m.getSceneY() - menu.localToScene(menu.getBoundsInLocal()).getMinY();
		
		((MenuButton)buttons.get(0)).normal();
		((MenuButton)buttons.get(1)).normal();
		((MenuButton)buttons.get(2)).normal();
		((MenuButton)buttons.get(3)).normal();
		((MenuButton)buttons.get(4)).normal();
		if(y >= 0 && y < menu.getHeight() && x >= 0 && x < menu.getWidth()){
			if(y < buttons.get(1).localToParent(0, 0).getY()){
				((MenuButton)buttons.get(0)).hover();
				if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
					Level.reset();
					ball.reset(); //Doesn't reset to toggled speed
					game.play(ball);
					brickManager.reset();
					timer.reset();
				}
			}
			else if(y < buttons.get(2).localToParent(0, 0).getY()){
				((MenuButton)buttons.get(1)).hover();
				if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
					ball.reset(); //Doesn't reset to toggled speed
					game.play(ball);
					brickManager.reset();
					timer.reset();
				}
			}
			else if(y < buttons.get(3).localToParent(0, 0).getY()){
				((MenuButton)buttons.get(2)).hover();
				if(m.getEventType() == MouseEvent.MOUSE_CLICKED)
					game.toggleSpeed(ball);
			}
			else if(y < buttons.get(4).localToParent(0, 0).getY()){
				((MenuButton)buttons.get(3)).hover();
				if(m.getEventType() == MouseEvent.MOUSE_CLICKED)
					game.toggleFriction(); //Doesn't set friction yet
			}
			else{
				((MenuButton)buttons.get(4)).hover();
				if(m.getEventType() == MouseEvent.MOUSE_CLICKED)
					game.exit();
			}
		}
	}

	@Override
	public void onEnterPress(){
		brickManager.reset();
		timer.reset();
		ball.reset();
		game.play(ball);
	}
	
	@Override
	public void onEscPress(){
		game.exit();
	}
}
