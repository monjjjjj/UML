package Factory;

import Component.UseCaseObject;

public class UseCaseObjectFactory extends BasicObjectFactory{
	
	@Override
	public UseCaseObject createObject(int x, int y) {
		return new UseCaseObject(x, y);
	}

}
