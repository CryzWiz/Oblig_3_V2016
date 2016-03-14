package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer {
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
		long hours = totalSeconds / 3600;
		long minutes = (totalSeconds % 3600) / 60;
		long seconds = totalSeconds % 60;
		//screenManager.setTimerText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
		text.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
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
}
