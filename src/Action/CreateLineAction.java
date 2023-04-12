package Action;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import Component.Group;
import Component.Line;
import Component.Port;
import Component.Shape;
import Factory.LineFactory;

public class CreateLineAction extends Action{
	private List<Shape> shapes;
	private Point startPoint = null;
	private Point endPoint = null;
	private LineFactory factory;
	private Port portStart = null, portEnd = null;
	
	private final String FIRST = "first";
	private final String SECOND = "second";
	
	
	public CreateLineAction(LineFactory factory) {
		this.factory = factory;
	}
	
	public void mousePressed(MouseEvent e) {
		shapes = canvas.getShapeList();
		/* find the first object */
		startPoint = findConnectedObj(e.getPoint(), FIRST);
	}
	
	public void mouseDragged(MouseEvent e) {
		/*show dragged line*/
		if (startPoint != null) {
			Point endPoint = e.getPoint();
			Line line = factory.createLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			canvas.setTempLine(line);
			canvas.repaint();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (startPoint != null) {
			/* find the second object*/
			endPoint = findConnectedObj(e.getPoint(), SECOND);
			
			if (endPoint != null) {
				if (isStartPointAndEndPointDifferent()) addLineToCanvas();
			}
			
			/* reset */
			canvas.setTempLine(null);
			canvas.repaint();
			startPoint = null;
			
		}
	}

	private boolean isStartPointAndEndPointDifferent() {
		return startPoint.x != endPoint.x || startPoint.y != endPoint.y;
	}

	private void addLineToCanvas() {
		Line line = factory.createLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		canvas.addShape(line);
		
		/* add relative port to line */
		line.setPorts(portStart, portEnd);
		
		/* add the line */
		portStart.addLine(line);
		portEnd.addLine(line);
	}

	private Point findConnectedObj(Point point, String target) {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			Port port = getPort(shape, point);
			if (port != null) {	
				setLinePoint(target, port);
				Point portLocation = new Point();
				portLocation.setLocation(port.getCenterX(), port.getCenterY());
				return portLocation;
			}
		}	
		return null;
	}

	private void setLinePoint(String target, Port port) {
		/* be inside the basic object and get the location of relative port */
		switch (target) {
			case FIRST:
				portStart = port;
				break;
			case SECOND:
				portEnd = port;
				break;
		}
	}
	
	private Port getPort(Shape shape, Point point) {
		String judgeInside = shape.inside(point);
		if (judgeInside != null && judgeInside != Line.INSIDE_LINE) {
			/* check the shape inside the group */
			if (judgeInside == Group.INSIDE_GROUP) {
				shape = shape.getSelectedBasicObject();
				return shape.getPort(Integer.parseInt(shape.inside(point)));
			} else {
				return shape.getPort(Integer.parseInt(judgeInside));
			}
		}
		
		return null;
	}

}
