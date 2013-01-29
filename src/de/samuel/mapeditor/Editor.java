package de.samuel.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Editor extends JPanel implements MouseMotionListener, MouseListener
{
	private BufferedImage 	bufferedImage;
	private Graphics2D		graphics2D;
	
	private int xMouseOld;
	private int yMouseOld;
	
	private Tool tool;
	
	public Editor()
	{
		bufferedImage 	= new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		graphics2D 		= (Graphics2D)bufferedImage.getGraphics();
		
		xMouseOld = -1;
		yMouseOld = -1;
		
		tool = Tool.getInstance();
		
		addMouseMotionListener(this);
		addMouseListener(this);
	}
 
	public void paintComponent(Graphics g)
	{
		/*
		BufferedImage img 	= new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D    g2dbi	= (Graphics2D)img.getGraphics();

	    g2dbi.setColor(Color.cyan);
	    g2dbi.drawLine(0, 0, 100, 100);

	    g2dbi.dispose();

	    g.drawImage(img, 0, 0, Color.black, null);*/		
		
		g.drawImage(bufferedImage, 0, 0, Color.black, null);	
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		if (!isLeftMouseButton(arg0))
			return;
		
		switch (tool.getToolNumber())
		{
			case Tool.FREEHAND:
				if (xMouseOld != -1 && yMouseOld != -1)
				{
					graphics2D.drawLine(xMouseOld, yMouseOld, arg0.getX(), arg0.getY());
				}
				else
				{
					graphics2D.drawRect(arg0.getX(), arg0.getY(), 1, 1);
				}
		
				this.repaint();
				
				xMouseOld = arg0.getX();
				yMouseOld = arg0.getY();
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{}

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
	{
		xMouseOld = -1;
		yMouseOld = -1;		
	}
	
	private boolean isLeftMouseButton(MouseEvent mouseEvent) 
	{
        int modifiers = mouseEvent.getModifiers();
        
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) 
        {
        	return true;
        }
        
        return false;
	}
}
