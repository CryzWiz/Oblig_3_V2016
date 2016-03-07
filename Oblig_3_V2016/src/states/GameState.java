package states;

import breakout.Ball;
import breakout.BrickManager;
import breakout.Racket;
import breakout.Room;
import breakout.ScreenManager;
import javafx.scene.input.MouseEvent;

public abstract class GameState {
	protected static StateUser root;
	protected static ScreenManager sManager;
	protected static BrickManager bManager;
	protected static Racket racket;
	protected static Ball ball;
	protected static Room room;

	public GameState() {}

	public static void setStatics(StateUser root, ScreenManager sm){
		GameState.root = root;
		sManager = sm;
		bManager = new BrickManager(sm.getPlayLayer());
		racket = new Racket(sm.getPlayLayer());
		ball = new Ball(sm.getPlayLayer());
		room = new Room(sm.getScene());
	}
	
	public void onUpPressed(){
		
	}

	public void onEscPressed(){

	}

	public void onSpacePressed(){
		//Nothing
	}

	public void onEnterPressed(){

	}

	public void onMouseEvent(MouseEvent m){
	}

	public void tick(){

	}
}
