package states;

import breakout.Ball;
import breakout.BrickManager;
import breakout.GameManager;
import breakout.Racket;
import breakout.Room;
import breakout.ScreenManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public abstract class GameState {
	protected static GameManager game;
	protected static ScreenManager sManager;
	protected static BrickManager bManager;
	protected static Racket racket;
	protected static Ball ball;
	protected static Room room;
	private static Timeline timer;
	private static long totalSeconds;

	public GameState() {}

	public static void setStatics(GameManager root, ScreenManager sm){
		GameState.game = root;
		sManager = sm;
		bManager = new BrickManager(sm.getPlayLayer());
		racket = new Racket(sm.getPlayLayer());
		ball = new Ball(sm.getPlayLayer());
		room = new Room(sm.getScene());
		timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
		timer.setCycleCount(Timeline.INDEFINITE);
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
	public static void updateTimer() {
		totalSeconds++;
		long hours = totalSeconds / 3600;
		long minutes = (totalSeconds % 3600) / 60;
		long seconds = totalSeconds % 60;
		sManager.setTimerText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
	}
	public void playTimer() {
		timer.play();
	}
	public void pauseTimer() {
		timer.pause();
	}
	public void resetTimer() {
		totalSeconds = 0;
		updateTimer();
	}
	public void tick(){
		
	}
}
