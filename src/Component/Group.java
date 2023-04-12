package Component;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Models.CustomColor;

public class Group extends Shape{
	private List<Shape> shapes = new ArrayList<Shape>();
	private Rectangle bounds = new Rectangle();
	private Shape selectedBasicObject = null;
	
	public static final String INSIDE_GROUP = "insideGroup";
			
	public void draw(Graphics g) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.draw(g);
		}
	}
	
	public void show(Graphics g) {
		int offset = 10;
		groupAreaColor(g, offset);
		if (selectedBasicObject != null) {
			selectedBasicObject.show(g);
		}
	}

	private void groupAreaColor(Graphics g, int offset) {
		g.setColor(CustomColor.Orange_Color_transparency);
		g.fillRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
		
		g.setColor(CustomColor.Orange_Color);
		g.drawRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
		
		g.setColor(CustomColor.white);
	}
	
	public void resetLocation(int moveX, int moveY) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.resetLocation(moveX, moveY);
		}
		resetBounds(moveX, moveY);
	}
	
	public String inside(Point point) {
		for(int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			String judgeInside = shape.inside(point);
			if (judgeInside != null) {
				selectedBasicObject = judgeInside == INSIDE_GROUP ? shape.getSelectedBasicObject() : shape;
				return INSIDE_GROUP;
			}
		}
		return null;
	}

	public void changeName(String name) {
		selectedBasicObject.changeName(name);
	}
	
	public void resetSelectedBasicObject() {
		selectedBasicObject = null;
	}
	
	public Shape getSelectedBasicObject() {
		return selectedBasicObject;
	}
	
	public void addShapes(Shape shape) {
		shapes.add(shape);
	}
	
	public List<Shape> getShapes() {
		return shapes;
	}
	
	public void setBounds() {
		/* find the left object and the right object, and set the bounds */
		Point leftUpPoint, rightBottomPoint;
		int leftX = Integer.MAX_VALUE, rightX = Integer.MIN_VALUE;
		int upY = Integer.MAX_VALUE, bottomY = Integer.MIN_VALUE;
		
		for (int i = 0; i < shapes.size(); i ++) {
			Shape shape = shapes.get(i);
			if (shape.getX1() < leftX) {
				leftX = shape.getX1();
			}
			if (shape.getX2() > rightX) {
				rightX = shape.getX2();
			}
			if (shape.getY1() < upY) {
				upY = shape.getY1();
			}
			if (shape.getY2() > bottomY) {
				bottomY = shape.getY2();
			}
			
		}
		
		leftUpPoint = new Point(leftX, upY);
		rightBottomPoint = new Point(rightX, bottomY);
		bounds.setRect(leftUpPoint.x, leftUpPoint.y, Math.abs(leftUpPoint.x - rightBottomPoint.x), Math.abs(leftUpPoint.y - rightBottomPoint.y));
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}
	
	
	protected void resetBounds(int moveX, int moveY) {
		bounds.setLocation(bounds.x + moveX, bounds.y + moveY);
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}
	

}
