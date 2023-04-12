package Action;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import Component.Line;
import Component.Shape;

public class Select extends Action{
	
	private Point startPoint = null;
	private String judgeInside = null;
	private List<Shape> shapes;
	private Line selectedLine = null;
	
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		shapes = canvas.getShapeList();
		
		canvas.reset();
		
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			judgeInside = shape.inside(e.getPoint());
			if (judgeInside != null) {
				canvas.selectedObj = shape;
				break;
			}
		}
		canvas.repaint();
	}
	
	
	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - startPoint.x;
		int moveY = e.getY() - startPoint.y;
		if (canvas.selectedObj != null) {
			if (judgeInside == Line.INSIDE_LINE) {
				selectedLine = (Line) canvas.selectedObj;
				selectedLine.resetStartEnd(e.getPoint());
			}
			else {
				canvas.selectedObj.resetLocation(moveX, moveY);
			}
			startPoint.x = e.getX();
			startPoint.y = e.getY();
		}
		else {
			if (e.getX() > startPoint.x)
				canvas.selectedArea.setBounds(startPoint.x, startPoint.y, Math.abs(moveX), Math.abs(moveY));
			else 
				canvas.selectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));
		}
		canvas.repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
		if (canvas.selectedObj != null) {
			// move Line object
			if (judgeInside == Line.INSIDE_LINE) {
				selectedLine = (Line) canvas.selectedObj;
				reconnectLine(e.getPoint());
				
			}
		}
		else {
			canvas.selectedArea.setSize(Math.abs(e.getX() - startPoint.x), Math.abs(e.getY() - startPoint.y));
		}
		canvas.repaint();
	}


	private void reconnectLine(Point point) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			int portIndex;
			String judgeInside = shape.inside(point);
			if (judgeInside != null && judgeInside != Line.INSIDE_LINE) {
				/* if shape inside the group */
				if (judgeInside == "insideGroup") {
					shape = shape.getSelectedBasicObject();
					portIndex = Integer.parseInt(shape.inside(point));
				}
				else
					portIndex = Integer.parseInt(judgeInside);
				
				selectedLine.resetPort(shape.getPort(portIndex), selectedLine);
				selectedLine.resetLocation();
			}
		}
		
	}
	
}
