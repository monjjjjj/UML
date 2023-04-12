package Interface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import Action.Action;
import Action.CreateBasicObjectAction;
import Action.CreateLineAction;
import Action.Select;
import Factory.AssociationLineFactory;
import Factory.ClassObjectFactory;
import Factory.CompositionLineFactory;
import Factory.GeneralizationLineFactory;
import Factory.UseCaseObjectFactory;
import Interface.ToolBtn;
import Models.CustomColor;


@SuppressWarnings({ "serial", "unused" })
public class ToolBar extends JToolBar{
	
	private int TOOL_NUM = 6;
	
	private ToolBtn clickedBtn = null;
	
	private Canvas canvas;
	
	public ToolBar() {
		canvas = Canvas.getInstance(); // Canvas is singleton
		setLayout(new GridLayout(TOOL_NUM, 1, 1, 3)); // (row, col, hgap, vgap)
		this.setBackground(CustomColor.BACKGROUND_COLOR);
		addBtnToToolbar();
	}

	private void addBtnToToolbar() {
		addBtn("select", "img/select.png", new Select());
		addBtn("associate", "img/associate.png", new CreateLineAction(new AssociationLineFactory()));
		addBtn("general", "img/general.png", new CreateLineAction(new GeneralizationLineFactory()));
		addBtn("composite", "img/composite.png", new CreateLineAction(new CompositionLineFactory()));
		addBtn("class", "img/class.png", new CreateBasicObjectAction(new ClassObjectFactory()));
		addBtn("usecase", "img/usecase.png", new CreateBasicObjectAction(new UseCaseObjectFactory()));
	}
	
	private ToolBtn addBtn(String toolName, String imgUrl, Action action) {
		ToolBtn.ClickHandler handler = (ToolBtn btn) -> { onToolBtnClicked(btn); };
		
		ImageIcon icon = new ImageIcon(imgUrl);
		ToolBtn btn = new ToolBtn(toolName, icon, handler, action);
		add(btn);
		
		return btn;
	}
	
	private void onToolBtnClicked(ToolBtn btn) {
		if (clickedBtn != null) {
			clickedBtn.setInActiveStyle();
		}
		btn.setActiveStyle();
		canvas.setCurrentAction(btn.getBtnAction());
		canvas.reset();
		canvas.repaint();
		clickedBtn = btn;
	}
}
