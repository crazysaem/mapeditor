package de.samuel.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements MouseListener
{
	private BufferedImage backGround;
	
	private Tool tool;
	
	public Toolbar()
	{
		File f = new File("src/data/toolbar.png");

		backGround = null;
		
		tool = Tool.getInstance();
		
		try 
		{
			backGround = ImageIO.read(f);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g)
	{		
		g.drawImage(backGround, 0, 0, Color.black, null);		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if (!isLeftMouseButton(arg0))
			return;
		
		if (isRowNumber(arg0, 3))
		{
			if (isColNumber(arg0, 0))
			{
				tool.setToolNumber(Tool.ARROW);
			}
			else
			{
				tool.setToolNumber(Tool.LINE);
			}
		}
		
		if (isRowNumber(arg0, 4))
		{
			if (isColNumber(arg0, 0))
			{
				tool.setToolNumber(Tool.RECTANGLE);
			}
			else
			{
				tool.setToolNumber(Tool.FREEHAND);
			}
		}
		
		if (isRowNumber(arg0, 5))
		{
			if (isColNumber(arg0, 0))
			{
				tool.setToolNumber(Tool.CIRCLE);
			}
			else
			{
				tool.setToolNumber(Tool.ERASER);
			}
		}
		
		if (isRowNumber(arg0, 6))
		{
			if (isColNumber(arg0, 0))
			{
				tool.setToolNumber(Tool.OBJECT);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{}

	@Override
	public void mousePressed(MouseEvent arg0)
	{}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{}
	
	private boolean isLeftMouseButton(MouseEvent mouseEvent) 
	{
        int modifiers = mouseEvent.getModifiers();
        
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) 
        {
        	return true;
        }
        
        return false;
	}
	
	private boolean isRowNumber(MouseEvent arg0, int rowNumber)
	{
		if(arg0.getY()>=rowNumber*32 && arg0.getY()<(rowNumber+1)*32)
			return true;
		
		return false;
	}
	
	private boolean isColNumber(MouseEvent arg0, int colNumber)
	{
		if (arg0.getX()>=colNumber && arg0.getX()<(colNumber+1)*32)
			return true;
		
		return false;
	}
}
