package Interface;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Component.Group;
import Component.Port;
import Component.Shape;
import Action.Action;
import Models.CustomColor;

@SuppressWarnings("serial")
public class Canvas extends JPanel{
	private static Canvas instance = null; // for singleton
	private EventListener listener = null;
	private List<Shape> shapes = new ArrayList<Shape>();
	
	protected Action currentAction = null;
	
	public Rectangle selectedArea = new Rectangle();
	public Shape selectedObj = null;
	private Shape tempLine = null;
	
	private Canvas() {
		setBackground(CustomColor.BACKGROUND_COLOR);
	}
	
	public static Canvas getInstance() {
		if ( instance == null ) {
			instance = new Canvas();
		}
		return instance;
	}
	
	public void setCurrentAction(Action currentAction) {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = currentAction;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
		
	}
	
	public void reset() {
		if (selectedObj != null) {
			selectedObj.resetSelectedBasicObject();
			selectedObj = null;
		}
		resetSelectedArea();
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public List<Shape> getShapeList() {
		return this.shapes;
	}
	
	public void changeObjName(String name) {
		if(selectedObj != null) {
			selectedObj.changeName(name);
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		initPaint(g);
		drawShape(g);
		drawTempLine(g);
		drawSelectedObj(g);
		drawSelectedArea(g);
	}

	private void drawSelectedArea(Graphics g) {
		if (!selectedArea.isEmpty()) {
			g.setColor(CustomColor.Orange_Color_transparency);
			g.fillRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
			g.setColor(CustomColor.Orange_Color);
			g.drawRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
		}
	}

	private void drawSelectedObj(Graphics g) {
		if (this.selectedObj != null) {
			selectedObj.show(g);
		}
	}

	private void drawTempLine(Graphics g) {
		if (tempLine != null) {
			tempLine.draw(g);
		}
	}

	public void setTempLine(Shape tempLine) {
		this.tempLine = tempLine;
	}
	
	
	public void groupShape() {		
		Long totalSelected = getTotalSelected();
		if (totalSelected > 1) {
			Group group = new Group();
			for (int i = 0; i < shapes.size(); i++) {
				Shape shape = shapes.get(i);
				if (shape.isGroupSelected()) {
					group.addShapes(shape);
					shapes.remove(i);
					i--;
				}
			}
			group.setBounds();
			shapes.add(group);
			resetSelectedArea();
		} else {
			showGroupRangeInvalidWarning();
		}
	}
	
	/*
	public void groupShape() {
		Long totalSelected = getTotalSelected();
		if (totalSelected > 1) {
			Group group = new Group();
			int number = shapes.size();
			int i = number;;
			do {
				Shape shape = shapes.get(i);
				if (shape.isGroupSelected()) {
					group.addShapes(shape);
					shapes.remove(i);
					i--;
				}
			}while( i < number);
		} else {
			showGroupRangeInvalidWarning();
		}
	}
	*/

	private Long getTotalSelected() {
		Long totalSelected = (long) 0;
		for (Shape shape : shapes) {
			if (shape.isGroupSelected() && shape.isGroupable()) totalSelected += 1;
		}
		return totalSelected;
	}

	private void showGroupRangeInvalidWarning() {
		JFrame warningFrame = new JFrame("Warning");
		warningFrame.setSize(400, 100);
		warningFrame.getContentPane().setLayout(new GridLayout(0, 1));
		warningFrame.add(new JLabel("Please select 2 or more objects."), BorderLayout.CENTER);
		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		warningFrame.setLocationRelativeTo(null);
		warningFrame.setVisible(true);
	}

	public void removeGroup() {
		if (selectedObj != null) {
			if (selectedObj.getClass().getName() == "Component.Group") {
				Group group = (Group) selectedObj;
				List<Shape> groupShapes = group.getShapes();
				for(int i = 0; i < groupShapes.size(); i++){
					Shape shape = groupShapes.get(i);
					shapes.add(shape);
				}
				shapes.remove(selectedObj);
			}
			reset();
		}
	}
	
	private void initPaint(Graphics g) {
		Dimension dim = getSize();
		g.setColor(CustomColor.BACKGROUND_COLOR);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(CustomColor.white);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		
	}
	
	private void drawShape(Graphics g) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.draw(g);
			shape.group_selected = false;
			checkShapeSelectedByGroup(g, shape);
			checkTempLineNearby(g, shape);
		}
	}

	private void checkTempLineNearby(Graphics g, Shape shape) {
		if (tempLine != null) {
			Point startPoint = new Point(tempLine.getX1(), tempLine.getY1());
			Point endPoint = new Point(tempLine.getX2(), tempLine.getY2());
			if (shape.isNearby(startPoint) || shape.isNearby(endPoint)) {
				shape.show(g);
			}
		}
	}

	private void checkShapeSelectedByGroup(Graphics g, Shape shape) {
		if (!selectedArea.isEmpty() && checkSelectedArea(shape)) {
			shape.show(g);
			shape.group_selected = true;
		}
	}
	
	private boolean checkSelectedArea(Shape shape) {
		Point leftUpPoint = new Point(shape.getX1(), shape.getY1());
		Point rightBottomPoint = new Point(shape.getX2(), shape.getY2());
		if (selectedArea.contains(leftUpPoint) && selectedArea.contains(rightBottomPoint)) {
			return true;
		}
		return false;
	}
	
	private void resetSelectedArea() {
		selectedArea.setBounds(0, 0, 0, 0);
	}
}
