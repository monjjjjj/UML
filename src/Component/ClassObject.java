package Component;

import java.awt.Graphics;

import Models.CustomColor;

public class ClassObject extends BasicObject {
	public ClassObject(int x1, int y1) {
		resetPoints(x1, y1, 100, 120);
	}
	
	@Override
	public void draw(Graphics g) {
		drawRect(g);
		drawDividinglines(g);
		drawString(g);
	}
	
	private void drawRect(Graphics g) {
		g.setColor(CustomColor.BACKGROUND_COLOR);
		g.fillRect(x1, y1, width, height);
		g.setColor(CustomColor.black);
		g.drawRect(x1, y1, width, height);
		
	}
	
	private void drawDividinglines(Graphics g) {
		int portion = height / 3;
		g.drawLine(x1, y1 + portion , x2, y1 + portion);
		g.drawLine(x1, y1 + portion * 2 , x2, y1 + portion * 2);
	}
	
	private void drawString(Graphics g) {
		int stringWidth = getStringWidth(g);
		int empty = (int) (Math.abs(x1 - x2) - stringWidth) / 2;
		g.setFont(font);
		g.drawString(objName, x1 + empty, y1 + 25);
		
	}
	
	private int getStringWidth(Graphics g) {
		return g.getFontMetrics(font).stringWidth(objName);
	}

}
