package Factory;

import Component.AssociationLine;
import Component.Line;

public class AssociationLineFactory extends LineFactory {


	@Override
	public Line createLine(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return new AssociationLine(x1, y1, x2, y2);
	}
	
}
