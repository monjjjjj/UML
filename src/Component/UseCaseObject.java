package Component;

import java.awt.Graphics;

import Models.CustomColor;

public class UseCaseObject extends BasicObject{
	public UseCaseObject(int x1, int y1) {
		resetPoints(x1, y1, 120, 90);
	} 
	
	@Override
	public void draw(Graphics g) {
		drawOval(g);
		drawString(g);
	}	
	
	private void drawOval(Graphics g) {			
		g.setColor(CustomColor.BACKGROUND_COLOR);
		g.fillOval(x1, y1, width, height);
		
        g.setColor(CustomColor.black);
        g.drawOval(x1, y1, width, height);
	}
	
	private void drawString(Graphics g) {
		int stringWidth = getStringWidth(g);
		int empty = (int) (Math.abs(x1 - x2) - stringWidth) / 2;		
		g.setFont(font);	
		g.drawString(objName, x1 + empty, y1 + 50);
	}
	
	private int getStringWidth(Graphics g) {
		return g.getFontMetrics(font).stringWidth(objName);
	}
}

