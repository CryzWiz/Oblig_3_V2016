package states;

import breakout.BallManager;
import breakout.BrickManager;
import breakout.GameManager;
import breakout.Racket;
import breakout.Room;
import breakout.ScreenManager;
import breakout.Timer;
import javafx.scene.input.MouseEvent;

public abstract class GameState {
	protected static GameManager gameManager;
	protected static ScreenManager screenManager;
	protected static BrickManager brickManager;
	protected static BallManager ballManager;
	protected static Racket racket;
	protected static Room room;
	protected static Timer timer;

	public GameState() {}

	public static void setStatics(GameManager root, ScreenManager sm){
		GameState.gameManager = root;
		screenManager = sm;
		brickManager = new BrickManager(sm.getPlayLayer());
		racket = new Racket(sm.getPlayLayer());
		room = new Room(sm.getScene());
		timer = new Timer(sm.getTimerText());
		ballManager = new BallManager(gameManager, brickManager, racket, room, sm.getPlayLayer());
	}

	public void onMouseEvent(MouseEvent m){
	}
	
	public void onEscPress(){}
	public void onSpacePress(){}
	public void onTabPress(){}
	
	public void onSpaceRelease(){}

	public void tick(){}
}
