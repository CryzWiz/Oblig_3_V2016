package states;

import javafx.scene.layout.Pane;

public class PauseState extends ScreenState {

	public PauseState(){
		root.setPane(panes.get(0));
			((Pane)panes.get(0).getChildren().get(1)).setOpacity(1);
	}

	@Override
	public void onSpacePressed(){
		root.newState(new PlayState());
	}
}
