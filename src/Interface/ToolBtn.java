package Interface;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Action.Action;
import Models.CustomColor;

@SuppressWarnings("serial")
public class ToolBtn extends JButton{
	private Action btnAction;
	
	public interface ClickHandler {
		void onClick(ToolBtn o);
	}
	
	public ToolBtn(String toolName, ImageIcon icon, ClickHandler handler, Action btnAction) {
		setState(toolName, icon);
		setInActiveStyle();
		setMouseListener(handler);
		this.btnAction = btnAction;
	}


	private void setState(String toolName, ImageIcon icon) {
		setToolTipText(toolName);
		setIcon(icon);
		setFocusable(false);
		setBorderPainted(true);
		setRolloverEnabled(true);
	}
	

	public void setInActiveStyle() {
		setBackground(CustomColor.SUNSET_COLOR);
	}
	
	public void setActiveStyle() {
		setBackground(CustomColor.black);
	}
	
	public Action getBtnAction() {
		return btnAction;
	}
	
	private void setMouseListener(ClickHandler handler) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handler.onClick((ToolBtn) e.getSource());
            }
        });
	}
	
}
