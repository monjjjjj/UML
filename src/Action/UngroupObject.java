package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interface.Canvas;

public class UngroupObject implements ActionListener{
	private Canvas canvas;
	public void actionPerformed(ActionEvent e) {
		canvas.removeGroup();
	}
}
