package states;

import javafx.scene.layout.Pane;

public interface StateUser {
	void newState(ScreenState state);
	void setPane(Pane pane);
}
