package breakout;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane implements Settings{
	public enum Colors{
		BLUE(Color.DARKBLUE, Color.BLUE, Color.LIGHTBLUE),
		RED(Color.DARKRED, Color.RED, Color.PINK),
		GREEN(Color.DARKGREEN, Color.GREEN, Color.LIGHTGREEN),
		YELLOW(Color.GOLDENROD, Color.GOLDENROD, Color.LIGHTYELLOW);
		
		Color stroke, fill, hover, text, textHover;
		
		private Colors(Color stroke, Color fill, Color hover){
			this(stroke, fill, hover, Color.WHITE, Color.BLACK);
		}
		private Colors(Color stroke, Color fill, Color hover, Color text, Color textHover){
			this.stroke = stroke;
			this.fill = fill;
			this.hover = hover;
			this.text = text;
			this.textHover = textHover;
		}
		
		public Color getStroke(){
			return stroke;
		}
		public Color getFill(){
			return fill;
		}
		public Color getHover(){
			return hover;
		}
		public Color getText(){
			return text;
		}
		public Color getTextHover(){
			return textHover;
		}
	};

	private static ArrayList<MenuButton> buttons = new ArrayList<>();
	
	private boolean clickable;
	private Colors color;
	private Rectangle box;
	private Text text;
	
	public MenuButton(String text, Colors color){
		this(text, color, BUTTON_WIDTH, BUTTON_HEIGHT, true);
	}
	public MenuButton(String text, Colors color, double w, double h, boolean clickable) {
		box = new Rectangle(w, h);
		this.clickable = clickable;
		this.color = color;
		this.text = new Text(text);
		this.text.setFill(color.getText());
		this.text.setFont(FONT_BUTTON);
		normal();
		setMaxWidth(w);
		setMaxHeight(h);
		getChildren().add(box);
		getChildren().add(this.text);
		buttons.add(this);
	}

	public static void setText(int buttonIndex, String text){
		buttons.get(buttonIndex).setText(text);
	}
	public void setText(String text){
		this.text.setText(text);
	}
	
	public void normal(){
		box.setFill(color.getFill());
		box.setStroke(color.getStroke());
		text.setFill(color.getText());
	}
	public void hover(){
		box.setFill(color.getHover());
		box.setStroke(color.getFill());
		text.setFill(color.getTextHover());
	}
	
	public boolean isClickable(){
		return clickable;
	}
}
