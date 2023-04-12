package Component;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import Models.CustomColor;


public abstract class BasicObject extends Shape{
	private int offset = 5;
	protected String objName = "Object Name";
	protected int width, height; 
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
	protected Port[] ports = new Port[4];
	
	public abstract void draw(Graphics g);
	
	public void show(Graphics g) {
		for(int i = 0; i < ports.length; i++) {
			g.setColor(CustomColor.black);
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
	}
	
	public String inside(Point p) {
		Point center = new Point();
		center.x = (x1 + x2) / 2;
		center.y = (y1 + y2) / 2;
		Point[] points = {new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2)};
		
		for (int i = 0; i < points.length; i++) {
			Polygon  poly= new Polygon();
			int secondIndex = ((i + 1) % 4);
			poly.addPoint(points[i].x, points[i].y);
			poly.addPoint(points[secondIndex].x, points[secondIndex].y);
			poly.addPoint(center.x, center.y);
			
			if (poly.contains(p)) {
				return Integer.toString(i);
			}
		}
		return null;
	}
	
	public Boolean isNearby(Point p) {
		Polygon poly = new Polygon();
		poly.addPoint(x1 - 10, y1 - 10);
		poly.addPoint(x2 + 10, y1 - 10);
		poly.addPoint(x2 + 10, y2 + 10);
		poly.addPoint(x1 - 10, y2 + 10);
		
		return poly.contains(p);
	}
	
	
	public Port getPort(int portIndex) {
		return ports[portIndex];
	}
	
	public void resetLocation(int moveX, int moveY) {
		int x1 = this.x1 + moveX;
		int y1 = this.y1 + moveY;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		int [] x_point = {(x1 + x2) / 2, x2 + offset, (x1 + x2) / 2, x1 - offset};
		int [] y_point = {y1 - offset, (y1 + y2) / 2, y2 + offset, (y1 + y2) / 2};
		
		for(int i = 0; i < ports.length; i++) {
			ports[i].setPort(x_point[i], y_point[i], offset);
			ports[i].resetLines();
			
		}
	}
	
	public void changeName(String name) {
		this.objName = name;
	}
	
	protected void resetPoints(int x1, int y1, int width, int height) {
		this.width = width;
		this.height = height;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		createPorts();
	}
	
	protected void createPorts() {
		int[] x_point = {(x1 + x2) / 2, x2 + offset, (x1 + x2) / 2, x1 - offset};
		int[] y_point = {y1 - offset, (y1 + y2) / 2, y2 + offset, (y1 + y2) / 2};
		
		for(int i = 0; i < ports.length; i++) {
			Port port = new Port();
			port.setPort(x_point[i], y_point[i], offset);
			ports[i] = port;
		}
	}
}
