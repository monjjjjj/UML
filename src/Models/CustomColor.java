package Models;

import java.awt.Color;

@SuppressWarnings("serial")
public class CustomColor extends Color {
	static int transparency = 85;
	public static final Color BACKGROUND_COLOR = new Color(255, 204, 204);
	public static final Color MY_COLOR = new Color(250, 111, 127);
	public static final Color Pink_Color = new Color(255, 102, 102);
	public static final Color Orange_Color = new Color(255, 153, 51);
	public static final Color Orange_Color_transparency = new Color(255, 153, 51, transparency);
	public static final Color Test_Color = new Color(0, 153, 0);
	public static final Color SUNSET_COLOR = new Color(205, 155, 155);
	
    public CustomColor(int r, int g, int b) {
        super(r, g, b, 255);
    }
}
