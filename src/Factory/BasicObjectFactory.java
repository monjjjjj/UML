package Factory;

import Component.BasicObject;

public abstract class BasicObjectFactory {
	public abstract BasicObject createObject(int x, int y);
}
