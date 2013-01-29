package de.samuel.mapeditor;

public class Tool 
{
	private static Tool tool = null;
	private int toolNumber = Tool.ARROW;
	
	public static final int ARROW = 0;
	public static final int LINE = 1;
	public static final int RECTANGLE = 2;
	public static final int FREEHAND = 3;
	public static final int CIRCLE = 4;
	public static final int ERASER = 5;
	public static final int OBJECT = 6;
	
	public int getToolNumber() {
		return toolNumber;
	}

	public void setToolNumber(int toolNumber) {
		this.toolNumber = toolNumber;
	}

	private Tool()
	{}
	
	public static Tool getInstance()
	{
		if(tool == null)
		{
			tool = new Tool();
		}
		
		return tool;
	}
}
