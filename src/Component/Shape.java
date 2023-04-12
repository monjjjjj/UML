package Component;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
	protected int x1, x2, y1, y2; 
	public boolean group_selected = false;
	
	public abstract void draw(Graphics g);
	
	public int getX1() {
		return x1;
	}
	public int getX2() {
		return x2;
	}
	public int getY1() {
		return y1;
	}
	public int getY2() {
		return y2;
	}
	
	// for line relocation
	public void resetLocation() {};
	
	// for object relocation
	public void resetLocation(int moveX, int moveY) {}
	
	public void changeName(String name) {}
	
	public void show(Graphics g) {}
	
	public String inside(Point p) { 
		return null; 
	}
	
	public Boolean isNearby(Point p) {
		return false;
	}
	
	//Basic object
	public Port getPort(int portIndex) {
		return null;
	}
	
	// group
	public void resetSelectedBasicObject() {}
	
	public Shape getSelectedBasicObject() {
		return null;
	}
	
	public Boolean isGroupSelected() {
		return group_selected;
	}
	
	public Boolean isGroupable() {
		return true;
	}
}
