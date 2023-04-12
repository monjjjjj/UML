package Factory;

import Component.Line;

public abstract class LineFactory {
	public abstract Line createLine(int x1, int y1, int x2, int y2);

}
