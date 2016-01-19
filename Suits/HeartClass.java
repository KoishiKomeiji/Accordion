//HeartClass.java by Howard Zeng 2011.12.10
package Suits;

import hsa.Console;
import java.awt.*;

public class HeartClass extends SuitClass
{
    public HeartClass ()
    {
    }


    public HeartClass (int h, int x, int y, Color col)
    {
	setHeight (h);
	setCenter (x, y);
	setColor (col);
    }


    public void draw (Console c)
    {
	int iPointsX[] = new int [3];
	int iPointsY[] = new int [3];

	// declare two integer vars for coordinates of UL corner of the left arc
	int iArcUpperLeftX = posX - iWidth / 2;
	int iArcUpperLeftY = posY - iHeight / 2 + 1;

	int iArcHeight = iHeight / 2;
	int iArcWidth = iWidth / 2;

	// calculate points on heart bottom & store in the arrays
	iPointsX [0] = posX - iWidth / 2;
	iPointsY [0] = posY - iHeight / 4;
	iPointsX [1] = posX;
	iPointsY [1] = posY + iHeight / 2;
	iPointsX [2] = posX + iWidth / 2;
	iPointsY [2] = posY - iHeight / 4;

	// draw the heart bottom using methods available from the Console object (c)
	c.setColour (cCol);
	c.fillPolygon (iPointsX, iPointsY, 3);

	// draw the left arc
	c.fillArc (iArcUpperLeftX, iArcUpperLeftY, iArcWidth, iArcHeight, 0, 180);
	// draw the right arc
	iArcUpperLeftX = posX;
	c.fillArc (iArcUpperLeftX, iArcUpperLeftY, iArcWidth, iArcHeight, 0, 180);
    }


    public void draw (Graphics g)
    {
	int iPointsX[] = new int [3];
	int iPointsY[] = new int [3];

	// declare two integer vars for coordinates of UL corner of the left arc
	int iArcUpperLeftX = posX - iWidth / 2;
	int iArcUpperLeftY = posY - iHeight / 2 + 1;

	int iArcHeight = iHeight / 2;
	int iArcWidth = iWidth / 2;

	// calculate points on heart bottom & store in the arrays
	iPointsX [0] = posX - iWidth / 2;
	iPointsY [0] = posY - iHeight / 4;
	iPointsX [1] = posX;
	iPointsY [1] = posY + iHeight / 2;
	iPointsX [2] = posX + iWidth / 2;
	iPointsY [2] = posY - iHeight / 4;

	// draw the heart bottom using methods available from the Console object (c)
	g.setColor (cCol);
	g.fillPolygon (iPointsX, iPointsY, 3);

	// draw the left arc
	g.fillArc (iArcUpperLeftX, iArcUpperLeftY, iArcWidth, iArcHeight, 0, 180);
	// draw the right arc
	iArcUpperLeftX = posX;
	g.fillArc (iArcUpperLeftX, iArcUpperLeftY, iArcWidth, iArcHeight, 0, 180);
    }
}
