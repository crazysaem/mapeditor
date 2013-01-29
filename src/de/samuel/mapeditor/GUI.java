package de.samuel.mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GUI
{
	private JFrame frame;
	private int width = 832;
	private int height = 600;	
	
	public GUI()
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		int xpos = d.width / 2 - width / 2;
		int ypos = d.height / 2 - height / 2;
		
		frame = new JFrame();
		frame.setBounds(xpos, ypos, width, height);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolbar toolbar = new Toolbar();
		toolbar.setBackground(Color.blue);
		
		Editor editor = new Editor();
		editor.setBackground(Color.red);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, toolbar, editor);
		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(64);
		splitPane.setDividerSize(0);

		toolbar.setMinimumSize	(new Dimension(64, 224));
		editor.setMinimumSize	(new Dimension(640, 480));
		
		frame.add(splitPane);
	}
	
	public void show()
	{
		frame.setVisible(true);
	}
}
