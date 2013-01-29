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
	private int width = 640;
	private int height = 480;	
	
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
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		toolbar.setMinimumSize(minimumSize);
		editor.setMinimumSize(minimumSize);
		
		frame.add(splitPane);
	}
	
	public void show()
	{
		frame.setVisible(true);
	}
}
