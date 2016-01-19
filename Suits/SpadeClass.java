//SpadeClass.java by Howard Zeng 2011.12.10
package Suits;

import hsa.Console;
import java.awt.*;

public class SpadeClass extends SuitClass
{

    public SpadeClass ()
    {
    }


    public SpadeClass (int h, int x, int y, Color col)
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

	//  Draws a triangle using the polygon method from console c
	int iPointsX1[] = new int [3];
	int iPointsY1[] = new int [3];

	iPointsX1 [0] = posX - iWidth / 2;
	iPointsY1 [0] = posY;
	iPointsX1 [1] = posX + iWidth / 2;
	iPointsY1 [1] = posY;
	iPointsX1 [2] = posX;
	iPointsY1 [2] = posY - iHeight / 2;

	//  Draws the shape
	c.setColor (cCol);
	c.fillPolygon (iPointsX, iPointsY, 4);
	c.fillPolygon (iPointsX1, iPointsY1, 3);
	c.fillArc (posX - (int) (iWidth / 2), posY - (int) (iHeight / 4), iWidth / 2, iHeight / 2, 180, 180);
	c.fillArc (posX, posY - (int) (iHeight / 4), iWidth / 2, iHeight / 2, 180, 180);

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

	//  Draws a triangle using the polygon method from console c
	int iPointsX1[] = new int [3];
	int iPointsY1[] = new int [3];

	iPointsX1 [0] = posX - iWidth / 2;
	iPointsY1 [0] = posY;
	iPointsX1 [1] = posX + iWidth / 2;
	iPointsY1 [1] = posY;
	iPointsX1 [2] = posX;
	iPointsY1 [2] = posY - iHeight / 2;

	//  Draws the shape
	g.setColor (cCol);
	g.fillPolygon (iPointsX, iPointsY, 4);
	g.fillPolygon (iPointsX1, iPointsY1, 3);
	g.fillArc (posX - (int) (iWidth / 2), posY - (int) (iHeight / 4), iWidth / 2, iHeight / 2, 180, 180);
	g.fillArc (posX, posY - (int) (iHeight / 4), iWidth / 2, iHeight / 2, 180, 180);

    }
}
