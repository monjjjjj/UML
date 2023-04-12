package Interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{
	private ToolBar toolBar;
	private Canvas canvas;
	
	private MenuBar menuBar;
	
	public Window() {
		canvas = Canvas.getInstance();
		toolBar = new ToolBar();
		menuBar = new MenuBar();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(toolBar, BorderLayout.WEST);
		getContentPane().add(menuBar, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		Window mainWindow = new Window();
		mainWindow.setTitle("UML Editor");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1200, 700);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
		
	}
	

}
