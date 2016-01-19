//DiamondClass.java by Howard Zeng 2011.12.10
package Suits;

import hsa.Console;
import java.awt.*;


public class DiamondClass extends SuitClass
{
    public DiamondClass ()
    {
    }


    public DiamondClass (int h, int x, int y, Color col)
    {
	setHeight (h);
	setCenter (x, y);
	setColor (col);
    }


    public void draw (Console c)
    {

	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = posX - iWidth / 2;
	iPointsY [0] = posY;
	iPointsX [1] = posX;
	iPointsY [1] = posY - iHeight / 2;
	iPointsX [2] = posX + iWidth / 2;
	iPointsY [2] = posY;
	iPointsX [3] = posX;
	iPointsY [3] = posY + iHeight / 2;

	c.setColor (cCol);
	c.fillPolygon (iPointsX, iPointsY, 4);
    }


    public void draw (Graphics g)
    {

	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = posX - iWidth / 2;
	iPointsY [0] = posY;
	iPointsX [1] = posX;
	iPointsY [1] = posY - iHeight / 2;
	iPointsX [2] = posX + iWidth / 2;
	iPointsY [2] = posY;
	iPointsX [3] = posX;
	iPointsY [3] = posY + iHeight / 2;

	g.setColor (cCol);
	g.fillPolygon (iPointsX, iPointsY, 4);
    }
}
