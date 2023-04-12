package Component;

import java.awt.Graphics;

public class CompositionLine extends Line{
	private int diamondW = 10, diamondH = 10;
	public CompositionLine(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy *dy);
		double x, xm = D - diamondW, xn = xm, ym = diamondH, yn = - diamondH;
		double sin = dy / D, cos = dx / D;
		
		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;
		
		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
		
		double xq = (diamondH * 2 / D) * x1 + ((D - diamondH * 2) / D) * x2;
		double yq = (diamondH * 2 / D) * y1 + ((D - diamondH * 2) / D) * y2;
		
		int[] x_points = {x2, (int) xm, (int) xq, (int) xn};
		int[] y_points = {y2, (int) ym, (int) yq, (int) yn};
		
		g.fillPolygon(x_points, y_points, 4);
		
	}

}
