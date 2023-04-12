package Factory;

import Component.GeneralizationLine;
import Component.Line;

public class GeneralizationLineFactory extends LineFactory{

	@Override
	public Line createLine(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return new GeneralizationLine(x1, y1, x2, y2);
	}

}
