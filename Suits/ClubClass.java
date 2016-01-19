//ClubClass.java by Howard Zeng 2011.12.10
package Suits;

import hsa.Console;
import java.awt.*;

public class ClubClass extends SuitClass
{
    public ClubClass ()
    {
    }


    public ClubClass (int h, int x, int y, Color col)
    {
	setHeight (h);
	setCenter (x, y);
	setColor (col);
    }


    public void draw (Console c)
    {

	//  Draws a rectangle using the polygon method
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	iPointsX [0] = posX - iWidth / 6;
	iPointsY [0] = posY;
	iPointsX [1] = posX + iWidth / 6;
	iPointsY [1] = posY;
	iPointsX [2] = posX + iWidth / 6;
	iPointsY [2] = posY + iHeight / 2;
	iPointsX [3] = posX - iWidth / 6;
	iPointsY [3] = posY + iHeight / 2;


	//  Draws the shape
	c.setColor (cCol);
	c.fillPolygon (iPointsX, iPointsY, 4);
	c.fillOval (posX - (iWidth / 2), posY - (iHeight / 6), iWidth / 2, iHeight / 2);
	c.fillOval (posX - (iWidth / 4), posY - (iHeight / 2), iWidth / 2, iHeight / 2);
	c.fillOval (posX, posY - (iHeight / 6), iWidth / 2, iHeight / 2);
    }


    public void draw (Graphics g)
    {

	//  Draws a rectangle using the polygon method
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	iPointsX [0] = posX - iWidth / 6;
	iPointsY [0] = posY;
	iPointsX [1] = posX + iWidth / 6;
	iPointsY [1] = posY;
	iPointsX [2] = posX + iWidth / 6;
	iPointsY [2] = posY + iHeight / 2;
	iPointsX [3] = posX - iWidth / 6;
	iPointsY [3] = posY + iHeight / 2;


	//  Draws the shape
	g.setColor (cCol);
	g.fillPolygon (iPointsX, iPointsY, 4);
	g.fillOval (posX - (iWidth / 2), posY - (iHeight / 6), iWidth / 2, iHeight / 2);
	g.fillOval (posX - (iWidth / 4), posY - (iHeight / 2), iWidth / 2, iHeight / 2);
	g.fillOval (posX, posY - (iHeight / 6), iWidth / 2, iHeight / 2);
    }
}
