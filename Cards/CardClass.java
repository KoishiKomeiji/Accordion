//CardClass.java by Howard Zeng 2011.12.11
package Cards;

import java.awt.*;
import hsa.Console;
import Shapes.*;
import Suits.*;

public class CardClass extends ShapeClass
{
    protected String value = "A";
    protected String size = "S";
    protected String suit = "Spade";
    protected boolean faceUp = true;


    // Overloading EVERYTHING has never been more useful
    public CardClass ()
    {
	setSize ("S");
    }


    public CardClass (String value, String size, String suit, boolean faceUp, int x, int y)
    {
	setValue (value);
	setSize (size);
	setSuit (suit);
	setFaceUp (faceUp);
	setCenter (x, y);
    }


    public CardClass (int value, int size, int suit, boolean faceUp, int x, int y)
    {
	setValue (value);
	setSize (size);
	setSuit (suit);
	setFaceUp (faceUp);
	setCenter (x, y);
    }


    public CardClass (int value, String size, int suit, boolean faceUp, int x, int y)
    {
	setValue (value);
	setSize (size);
	setSuit (suit);
	setFaceUp (faceUp);
	setCenter (x, y);
    }


    public void setValue (String s)
    {
	value = s.toUpperCase ();
    }


    public void setValue (int n)
    {
	if (n > 1 && n <= 10)
	{
	    value = Integer.toString (n);
	}
	else if (n == 1)
	{
	    value = "A";
	}
	else if (n == 11)
	{
	    value = "J";
	}
	else if (n == 12)
	{
	    value = "Q";
	}
	else if (n == 13)
	{
	    value = "K";
	}
    }


    public String getValue ()
    {
	return value;
    }


    // You're not supposed to be explicitly setting the Height and Width
    public void setHeight (int n)
    {
    }


    public void setWidth (int n)
    {
    }


    public void setSize (String s)
    {
	size = s.toUpperCase ();
	if (size == "S")
	{
	    iHeight = 60;
	}
	else if (size == "M")
	{
	    iHeight = 80;
	}
	else if (size == "L")
	{
	    iHeight = 100;
	}
	else if (size == "XL")
	{
	    iHeight = 120;
	}
	iWidth = ((iHeight * 7) / 10);
    }


    public void setSize (int n)
    {
	if (n == 1)
	{
	    iHeight = 60;
	}
	else if (n == 2)
	{
	    iHeight = 80;
	}
	else if (n == 3)
	{
	    iHeight = 100;
	}
	else if (n == 4)
	{
	    iHeight = 120;
	}
	iWidth = ((iHeight * 7) / 10);
    }


    public String getSize ()
    {
	return size;
    }


    public void setSuit (String s)
    {
	suit = s;
	if (s == "Diamond" || s == "Heart")
	{
	    cCol = Color.red;
	}
	else
	{
	    cCol = Color.black;
	}
    }


    public void setSuit (int n)
    {
	if (n == 1)
	{
	    suit = "Diamond";
	    cCol = Color.red;
	}
	else if (n == 2)
	{
	    suit = "Club";
	    cCol = Color.black;
	}
	else if (n == 3)
	{
	    suit = "Heart";
	    cCol = Color.red;
	}
	else if (n == 4)
	{
	    suit = "Spade";
	    cCol = Color.black;
	}
    }



    public String getSuit ()
    {
	return suit;
    }


    public void setFaceUp (boolean b)
    {
	faceUp = b;
    }


    public boolean getFaceUp ()
    {
	return faceUp;
    }


    public void drawEmpty (Console c)
    {
	c.setColor (cCol);
	c.drawRect (posX - (iWidth / 2), posY - (iHeight / 2), iWidth, iHeight);
    }


    public void drawEmpty (Graphics g)
    {
	g.setColor (cCol);
	g.drawRect (posX - (iWidth / 2), posY - (iHeight / 2), iWidth, iHeight);
    }


    public void draw (Console c)
    {


	Font font = new Font ("SansSerif", Font.PLAIN, iHeight / 6);
	c.setFont (font);
	c.setColor (cCol);

	if (faceUp == false)
	{
	    c.fillRect (posX - (iWidth / 2), posY - (iHeight / 2), iWidth, iHeight);
	}


	else
	{
	    drawEmpty (c);

	    // Top Left Corner
	    // Sorry, but I'm a perfectionist -- exactly 46% of width is good here =)
	    c.drawString (value, posX - (iWidth * 23 / 50), posY - (iHeight / 3) + 1);

	    // Bottom Right Corner
	    if (value == "10")
	    { // Exactly 20% of width =)
		c.drawString (value, posX + (iWidth / 5), posY + (iHeight / 2) - 4);
	    }
	    else
	    { // And exactly 30% is good here too =)
		c.drawString (value, posX + (iWidth * 3 / 10), posY + (iHeight / 2) - 4);
	    }



	    if (suit == "Diamond")
	    {
		DiamondClass oDiamond = new DiamondClass (iHeight / 4, posX, posY, cCol);
		oDiamond.draw (c);
	    }
	    else if (suit == "Club")
	    {
		ClubClass oClub = new ClubClass (iHeight / 4, posX, posY, cCol);
		oClub.draw (c);
	    }
	    else if (suit == "Heart")
	    {
		HeartClass oHeart = new HeartClass (iHeight / 4, posX, posY, cCol);
		oHeart.draw (c);
	    }
	    else if (suit == "Spade")
	    {
		SpadeClass oSpade = new SpadeClass (iHeight / 4, posX, posY, cCol);
		oSpade.draw (c);
	    }
	}
    }


    public void draw (Graphics g)
    {


	Font font = new Font ("SansSerif", Font.PLAIN, iHeight / 6);
	g.setFont (font);


	if (faceUp == false)
	{
	    g.setColor (Color.green);
	    g.fillRect (posX - (iWidth / 2), posY - (iHeight / 2), iWidth, iHeight);
	}


	else
	{
	    g.setColor (Color.white);
	    g.fillRect (posX - (iWidth / 2), posY - (iHeight / 2), iWidth, iHeight);
	    g.setColor (cCol);
	    // Top Left Corner
	    // Sorry, but I'm a perfectionist -- exactly 46% of width is good here =)
	    g.drawString (value, posX - (iWidth * 23 / 50), posY - (iHeight / 3) + 1);

	    // Bottom Right Corner
	    if (value == "10")
	    { // Exactly 20% of width =)
		g.drawString (value, posX + (iWidth / 5), posY + (iHeight / 2) - 4);
	    }
	    else
	    { // And exactly 30% is good here too =)
		g.drawString (value, posX + (iWidth * 3 / 10), posY + (iHeight / 2) - 4);
	    }



	    if (suit == "Diamond")
	    {
		DiamondClass oDiamond = new DiamondClass (iHeight / 4, posX, posY, cCol);
		oDiamond.draw (g);
	    }
	    else if (suit == "Club")
	    {
		ClubClass oClub = new ClubClass (iHeight / 4, posX, posY, cCol);
		oClub.draw (g);
	    }
	    else if (suit == "Heart")
	    {
		HeartClass oHeart = new HeartClass (iHeight / 4, posX, posY, cCol);
		oHeart.draw (g);
	    }
	    else if (suit == "Spade")
	    {
		SpadeClass oSpade = new SpadeClass (iHeight / 4, posX, posY, cCol);
		oSpade.draw (g);
	    }
	    drawEmpty (g);
	}
    }
}


