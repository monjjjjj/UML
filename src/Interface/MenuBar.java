package Interface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	private Canvas canvas;
	public MenuBar() {
		canvas = Canvas.getInstance();
		
		JMenu menu;
		JMenuItem item;
		
		//File part
		menu = new JMenu("File");
		add(menu);
		
		//Edit part
		menu = new JMenu("Edit");
		add(menu);
		
		item = new JMenuItem("Change Object name");
		menu.add(item);
		
		item.addActionListener(new ChangeNameListener());
		
		item = new JMenuItem("Group");
		menu.add(item);
		item.addActionListener(new GroupObjectListener());
		
		item = new JMenuItem("Ungroup");
		menu.add(item);
		item.addActionListener(new UngroupObjectListener());
		
	}
	
	private void changeName() {
		JFrame inputTextFrame = new JFrame("Change Object Name");
		inputTextFrame.setSize(400, 100);
		inputTextFrame.getContentPane().setLayout(new GridLayout(0, 1));
		
		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextField Text =  new JTextField("Object Name");
		panel.add(Text);
		inputTextFrame.getContentPane().add(panel);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton confirm = new JButton("OK");
		panel.add(confirm);
		
		JButton cancel = new JButton("Cancel");
		panel.add(cancel);
		
		inputTextFrame.getContentPane().add(panel);
		
		inputTextFrame.setLocationRelativeTo(null);
		inputTextFrame.setVisible(true);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.changeObjName(Text.getText());
				inputTextFrame.dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputTextFrame.dispose();
			}
		});
		
	}
	
	class ChangeNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			changeName();
		}
	}
	
	
	
	class GroupObjectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			canvas.groupShape();
			canvas.repaint();
		}
	}
	class UngroupObjectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			canvas.removeGroup();
			canvas.repaint();
		}
	}
}
