package Component;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

import Models.CustomColor;

public abstract class Line extends Shape{
	private Port[] ports = new Port[2];
	private String selectedFlag = null;
	
	public void setPorts(Port port_1, Port port_2) {
		this.ports[0] = port_1;
		this.ports[1] = port_2;
	}
	
	public void show(Graphics g) {
		g.setColor(CustomColor.Pink_Color);
		this.draw(g);
		g.setColor(CustomColor.white);
	}
	
	
	public void showPort(Graphics g, int center_x, int center_y, int offset) {
		int x = center_x - offset;
		int y = center_y - offset;
		int width = offset * 2;
		int	height = offset * 2;
		g.fillRect(x, y, width, height);
	}
	
	public void resetLocation() {
		this.x1 = (int) ports[0].getCenterX();
		this.y1 = (int) ports[0].getCenterY();
		this.x2 = (int) ports[1].getCenterX();
		this.y2 = (int) ports[1].getCenterY();
	}
	
	public void resetStartEnd(Point point) {
		if(selectedFlag == "start") {
			this.x1 = point.x;
			this.y1 = point.y;
		}
		else if (selectedFlag == "end"){
			this.x2 = point.x;
			this.y2 = point.y;
		}
	}
	
	public String inside(Point point) {
		int tolerance = 5;
		if(distance(point) < tolerance) {
			double distToStart = Math.sqrt(Math.pow((point.x - x1), 2) + Math.pow((point.y - y1), 2));
			double distToEnd = Math.sqrt(Math.pow((point.x - x2), 2) + Math.pow((point.y - y2), 2));
			if(distToStart < distToEnd) {
				selectedFlag = "start"; 
			}
			else {
				selectedFlag = "end";
			}
			return "insideLine";
		}
		else {
			return null;
		}
		
	}
	
	public void resetPort(Port port, Line line) {
		port.addLine(line);
		if(selectedFlag == "start") {
			this.ports[0].removeLine(line);
			this.ports[0] = port;
		}
		else if(selectedFlag == "end") {
			this.ports[1].removeLine(line);
			this.ports[1] = port;
		}
	}
	
	private double distance(Point point) {
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		return line.ptLineDist(point.getX(), point.getY());
	}
	
	@Override
	public Boolean isGroupable() {
		return false;
	}
}
