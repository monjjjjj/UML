package Action;

import java.awt.event.MouseEvent;

import Component.BasicObject;
import Factory.BasicObjectFactory;

public class CreateBasicObjectAction extends Action{
	private BasicObjectFactory factory;
	
	public CreateBasicObjectAction(BasicObjectFactory factory) {
		this.factory = factory;
	}
	
	public void mousePressed(MouseEvent e) {
		BasicObject basicObj = factory.createObject(e.getPoint().x, e.getPoint().y);
		canvas.addShape(basicObj);
		canvas.repaint();
	}

}
