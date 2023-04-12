package Component;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Port extends Rectangle{
	private List<Line> lines = new ArrayList<Line>();
	
	public void setPort(int center_x, int center_y, int offset) {
		int x = center_x - offset;
		int y = center_y - offset;
		int width = offset * 2;
		int	height = offset * 2;
		setBounds(x, y, width, height);
	}
	
	public void addLine(Line line) {
		lines.add(line);
	}
	
	public void removeLine(Line line) {
		lines.remove(line);
	}
	
	public void resetLines() {
		for(int i = 0; i < lines.size(); i++){
			Line line = lines.get(i);
			line.resetLocation();
		}
	}
}

