package de.samuel.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

public class Editor extends JPanel implements MouseMotionListener, MouseListener
{
	private static final int width = 800;
	private static final int height = 600;
	
	private BufferedImage 	biEditor;
	private Graphics2D		g2dEditor;
	
	private BufferedImage 	biBackup;
	private Graphics2D		g2dBackup;	
	
	private int xMouseOld;
	private int yMouseOld;
	
	private Tool tool;
	
	private int brushSize;
	
	public Editor()
	{
		biEditor 	= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2dEditor 	= (Graphics2D)biEditor.getGraphics();
		
		biBackup 	= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2dBackup 	= (Graphics2D)biBackup.getGraphics();
		
		xMouseOld = -1;
		yMouseOld = -1;
		
		tool = Tool.getInstance();
		
		brushSize = 6;
		
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
		
		g.drawImage(biEditor, 0, 0, Color.black, null);	
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		if (!isLeftMouseButton(arg0))
			return;
		
		switch (tool.getToolNumber())
		{
			case Tool.ERASER:
				drawFreeLine(arg0, Color.black);
			break;
			
			case Tool.FREEHAND:
				drawFreeLine(arg0, Color.white);
			break;
			
			case Tool.LINE:
				drawStraightLine(arg0);
			break;
		}
		
		this.repaint();
	}
	
	public void drawFreeLine(MouseEvent arg0, Color c)
	{		
		g2dEditor.setColor(c);		
		
		Vector2d vec = new Vector2d(xMouseOld, yMouseOld, arg0.getX(), arg0.getY());
		
		for(int i=0; i<=vec.getLength(); i++)
		{
			double x = xMouseOld + ((double)i/vec.getLength()) * vec.getxDir();
			double y = yMouseOld + ((double)i/vec.getLength()) * vec.getyDir();
			
			g2dEditor.fillRect((int)x - brushSize, (int)y  - brushSize, brushSize * 2, brushSize * 2);
		}
		
		xMouseOld = arg0.getX();
		yMouseOld = arg0.getY();
	}
	
	public void drawStraightLine(MouseEvent arg0)
	{
		g2dEditor.clearRect(0, 0, width, height);
		g2dEditor.drawImage(biBackup, 0, 0, new Color(0, 0, 0, 0), null);	
		
		BufferedImage 	biSstraightLine;
		Graphics2D		g2dSstraightLine;
				
		biSstraightLine 	= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2dSstraightLine 	= (Graphics2D)biSstraightLine.getGraphics();
		
		g2dSstraightLine.setBackground(new Color(0, 0, 0, 0));
		g2dSstraightLine.setColor(Color.white);
		
		Vector2d vec = new Vector2d(xMouseOld, yMouseOld, arg0.getX(), arg0.getY());
		
		for(int i=0; i<=vec.getLength(); i++)
		{
			double x = xMouseOld + ((double)i/vec.getLength()) * vec.getxDir();
			double y = yMouseOld + ((double)i/vec.getLength()) * vec.getyDir();
			
			g2dSstraightLine.fillRect((int)x - brushSize, (int)y  - brushSize, brushSize * 2, brushSize * 2);
		}
		
		g2dSstraightLine.dispose();
	
		g2dEditor.drawImage(biSstraightLine, 0, 0, new Color(0, 0, 0, 0), null);	
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		g2dEditor.clearRect(0, 0, width, height);
		g2dEditor.drawImage(biBackup, 0, 0, new Color(0, 0, 0, 0), null);	
		
		BufferedImage 	biSstraightLine;
		Graphics2D		g2dSstraightLine;
				
		biSstraightLine 	= new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2dSstraightLine 	= (Graphics2D)biSstraightLine.getGraphics();
		
		Color c = Color.white;
		
		if (tool.getToolNumber() == Tool.ERASER)
		{
			c = Color.red;
		}
		
		g2dSstraightLine.setColor(c);
		g2dSstraightLine.fillRect(arg0.getX() - brushSize, arg0.getY()  - brushSize, brushSize * 2, brushSize * 2);
		
		g2dSstraightLine.dispose();		
		g2dEditor.drawImage(biSstraightLine, 0, 0, new Color(0, 0, 0, 0), null);	
		
		this.repaint();
	}

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
	{
		xMouseOld = arg0.getX();
		yMouseOld = arg0.getY();
		
		if (tool.getToolNumber() == Tool.LINE)
		{
			g2dBackup.clearRect(0, 0, width, height);
			g2dBackup.drawImage(biEditor, 0, 0, new Color(0, 0, 0, 0), null);	
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		g2dBackup.clearRect(0, 0, width, height);
		g2dBackup.drawImage(biEditor, 0, 0, new Color(0, 0, 0, 0), null);
		
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
