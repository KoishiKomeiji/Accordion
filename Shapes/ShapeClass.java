//ShapeClass.java by Howard Zeng 2011.12.10
package Shapes;

import java.awt.*;
import hsa.Console;


public abstract class ShapeClass
{

    protected Color cCol = Color.green;
    protected int iHeight = 60;
    protected int iWidth = 48;
    protected int posX = 300;
    protected int posY = 300;


    public void setWidth (int n)
    {
	iWidth = n;
    }


    public void setHeight (int n)
    {
	iHeight = n;
    }


    public void setCenter (int cx, int cy)
    {
	posX = cx;
	posY = cy;
    }


    public void setColor (Color col)
    {
	cCol = col;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCenterX ()
    {
	return posX;
    }


    public int getCenterY ()
    {
	return posY;
    }


    public Color getColor ()
    {
	return cCol;
    }



    public abstract void draw (Console c);



    public void erase (Console c)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (c);
	setColor (cOldColor);
    }


    public abstract void draw (Graphics g);


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }


    public void delay (int iDelayTime)  //In milliseconds
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	    // Do nothing while the (current + delay) is greater than iDelayTime
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }
}
