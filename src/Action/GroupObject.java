package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interface.Canvas;

public class GroupObject implements ActionListener{
	private Canvas canvas;
	public void actionPerformed(ActionEvent e) {
		canvas.groupShape();
	}
}
