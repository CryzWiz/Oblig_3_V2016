package breakout;

import java.text.ParseException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer implements Comparable<Timer>{
	private Timeline timer;
	private long totalMillis;
	private Text text;

	public Timer(Text timerText) {
		text = timerText;
		timer = new Timeline(new KeyFrame(Duration.millis(1), e -> update()));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	public void update() {
		totalMillis++;
		if(text != null)
			text.setText(toString());
	}
	public void play() {
		timer.play();
	}
	public void pause() {
		timer.pause();
	}
	public void reset() {
		totalMillis = 0;
		update();
	}
	
	public String toString(){
		int seconds = (int)(totalMillis / 1000) % 60;
		int minutes = (int)(totalMillis / (1000 * 60));
		int millis = (int)(totalMillis % 1000);

		return String.format("%02d:%02d:%02d", minutes, seconds, millis);
	}
	
	public static Timer parse(String dateString) throws ParseException{
		Timer timer = new Timer(null);
		timer.pause();
		String[] stringParts = dateString.split(":");
		timer.totalMillis = Integer.parseInt(stringParts[0]) * 60000 + Integer.parseInt(stringParts[1]) * 1000 + Integer.parseInt(stringParts[2]);
		return timer;
	}

	@Override
	public int compareTo(Timer timer) {
		if(totalMillis < timer.totalMillis)
			return -1;
		else if(totalMillis > timer.totalMillis)
			return 1;
		return 0;
	}
}
