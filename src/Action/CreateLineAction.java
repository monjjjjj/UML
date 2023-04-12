package Action;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import Component.Group;
import Component.Line;
import Component.Shape;
import Factory.LineFactory;

public class CreateLineAction extends Action{
	private List<Shape> shapes;
	private Point startPoint = null;
	private Point endPoint = null;
	private LineFactory factory;
	private int portIndex_1 = -1, portIndex_2 = -1;
	private Shape shape_1 = null, shape_2 = null;
	
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
		line.setPorts(shape_1.getPort(portIndex_1), shape_2.getPort(portIndex_2));
		
		/* add the line */
		shape_1.getPort(portIndex_1).addLine(line);
		shape_2.getPort(portIndex_2).addLine(line);
	}

	private Point findConnectedObj(Point point, String target) {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			Integer portIndex = getPortIndex(shape, point);
			if (portIndex != null) {	
				/* be inside the basic object and get the location of relative port */
				switch (target) {
					case FIRST:
						shape_1 = shape;
						portIndex_1 = portIndex;
						break;
					case SECOND:
						shape_2 = shape;
						portIndex_2 = portIndex;
						break;
				}
				Point portLocation = new Point();
				portLocation.setLocation(shape.getPort(portIndex).getCenterX(), shape.getPort(portIndex).getCenterY());
				return portLocation;
			}
		}	
		return null;
	}
	
	private Integer getPortIndex(Shape shape, Point point) {
		String judgeInside = shape.inside(point);
		if (judgeInside != null && judgeInside != "insideLine") {
			/* check the shape inside the group */
			if (judgeInside == Group.INSIDE_GROUP) {
				shape = shape.getSelectedBasicObject();
				return Integer.parseInt(shape.inside(point));
			} else {
				return Integer.parseInt(judgeInside);
			}
		}
		
		return null;
	}

}
