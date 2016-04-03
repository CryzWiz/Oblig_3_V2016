package states;

import breakout.Ball;
import breakout.BallManager;
import breakout.BrickManager;
import breakout.GameManager;
import breakout.Racket;
import breakout.Room;
import breakout.ScreenManager;
import breakout.Timer;
import javafx.scene.input.MouseEvent;

public abstract class GameState {
	protected static GameManager game;
	protected static ScreenManager screenManager;
	protected static BrickManager brickManager;
	protected static BallManager ballManager;
	protected static Racket racket;
	//protected static Ball ball;
	protected static Room room;
	protected static Timer timer;

	public GameState() {}

	public static void setStatics(GameManager root, ScreenManager sm){
		GameState.game = root;
		screenManager = sm;
		brickManager = new BrickManager(sm.getPlayLayer());
		racket = new Racket(sm.getPlayLayer());
		//ball = new Ball(sm.getPlayLayer());
		room = new Room(sm.getScene());
		timer = new Timer(sm.getTimerText());
		ballManager = new BallManager(game, brickManager, racket, room, sm.getPlayLayer());
	}
	
	public void onUpPress(){}
	public void onEscPress(){}
	public void onSpacePress(){}
	public void onEnterPress(){}
	
	public void onSpaceRelease(){}

	public void onMouseEvent(MouseEvent m){
	}

	public void tick(){}
}
