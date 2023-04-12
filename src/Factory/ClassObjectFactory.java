package Factory;

import Component.ClassObject;

public class ClassObjectFactory extends BasicObjectFactory{
	
	@Override
	public ClassObject createObject(int x, int y) {
		return new ClassObject(x, y);
	}

}
