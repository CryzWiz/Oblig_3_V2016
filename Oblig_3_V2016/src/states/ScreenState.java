package states;

import java.util.ArrayList;

import breakout.Ball;
import breakout.BrickManager;
import breakout.Racket;
import breakout.Room;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class ScreenState {
  protected static StateUser root;
  protected static ArrayList<Pane> panes;
  protected static BrickManager bManager;
  protected static Racket racket;
  protected static Ball ball;
  protected static Room room;

  public ScreenState() {}
  
  public static void setStatics(StateUser root, ArrayList<Pane> p, BrickManager bm, Racket ra, Ball b, Room ro){
    ScreenState.root = root;
    panes = p;
    bManager = bm;
    racket = ra;
    ball = b;
    room = ro;
  }

  public void onEscPressed(){
    
  }
  
  public void onSpacePressed(){
    
  }
  
  public void onEnterPressed(){
    
  }
  
  public void onMouseEvent(MouseEvent m){
  }
  
  public void tick(){
    
  }
}
