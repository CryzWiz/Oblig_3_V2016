package breakout;

import java.text.ParseException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer implements Comparable<Timer>{
	private Timeline timer;
	private long totalSeconds;
	private Text text;

	public Timer(Text timerText) {
		text = timerText;
		timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> update()));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	public void update() {
		totalSeconds++;
		//screenManager.setTimerText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
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
		totalSeconds = 0;
		update();
	}
	
	public String toString(){
		long hours = totalSeconds / 3600;
		long minutes = (totalSeconds % 3600) / 60;
		long seconds = totalSeconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
	
	public static Timer parse(String dateString) throws ParseException{
		Timer timer = new Timer(null);
		timer.pause();
		String[] stringParts = dateString.split(":");
		timer.totalSeconds = Integer.parseInt(stringParts[0]) * 3600 + Integer.parseInt(stringParts[1]) * 60 + Integer.parseInt(stringParts[2]);
		//Date date = sdf.parse(dateString);
		//timer.totalSeconds = date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds();
		return timer;
	}

	@Override
	public int compareTo(Timer timer) {
		if(totalSeconds < timer.totalSeconds)
			return -1;
		else if(totalSeconds > timer.totalSeconds)
			return 1;
		return 0;
	}
}
