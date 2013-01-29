package de.samuel.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Editor extends JPanel implements MouseMotionListener
{
	private BufferedImage 	bufferedImage;
	private Graphics2D		graphics2D;
	
	public Editor()
	{
		bufferedImage 	= new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
		graphics2D 		= (Graphics2D)bufferedImage.getGraphics();
		
		addMouseMotionListener(this);
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
		graphics2D.drawRect(arg0.getX(), arg0.getY(), 1, 1);
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{}
}
