//DeckClass.java by Howard Zeng 2011.12.11
package Decks;

import java.awt.*;
import java.util.*;
import hsa.Console;
import Cards.*;
import Shapes.*;
import java.io.*;

public class DeckClass extends ShapeClass implements Cloneable
{

    protected Vector deck = new Vector (0, 1);
    protected String DefSize = "S"; //Small

    public DeckClass ()
    {
    }


    public DeckClass (String size, Color col)
    {
	DefSize = size;
	cCol = col;
    }


    public DeckClass (String DeckType, String size)
    {
	DefSize = size;
	if (DeckType == "Std")
	{
	    BuildStdDeck ();
	}
    }


    public Object clone ()
    {
	try
	{
	    return super.clone ();
	}
	catch (CloneNotSupportedException e)
	{
	    System.out.println ("Hi");
	    return null;
	}
    }


    public void BuildStdDeck ()
    {
	for (int i = 1 ; i < 5 ; i++)
	{
	    for (int j = 1 ; j < 14 ; j++)
	    {
		CardClass oCard = new CardClass (j, DefSize, i, false, posX, posY);
		deck.addElement (oCard);
	    }

	}
    }


    public void addCard (CardClass element, int pos)
    {
	if (deck.size () == 0)
	{
	    deck.addElement (element);
	}
	else if (pos > deck.size ())
	{
	    deck.insertElementAt (element, deck.size ());
	}
	else
	{
	    deck.insertElementAt (element, pos);
	}

    }


    public void removeCard (int pos)
    {
	if (deck.size () == 0) // If nothing exists
	{
	}
	else if (pos <= deck.size ())
	{
	    deck.removeElementAt (pos);
	}
	else if (pos >= deck.size ())
	{
	    deck.removeElementAt (deck.size () - 1);
	}
    }


    public int getDeckSize ()
    {
	return deck.size ();
    }


    // Gets the pointer to card and removes it from deck
    public CardClass dealCard (int pos)
    {
	if (pos >= deck.size ()) // Exceeds
	{
	    return null;
	}
	else
	{
	    return ((CardClass) deck.remove (pos));
	}
    }


    // Returns the pointer to card
    public CardClass getCard (int pos)
    {
	if (pos >= deck.size ()) // Exceeds
	{
	    return null;
	}
	else
	{
	    return ((CardClass) deck.elementAt (pos));
	}
    }


    public void shuffle ()
    {
	int n = deck.size ();
	Vector tDeck = (Vector) deck.clone ();
	deck.clear ();

	for (int i = 0 ; i < n ; i++)
	{
	    // To randomly insert card to vector by abusing the dynamic deck size
	    addCard ((CardClass) tDeck.elementAt (i), (int) (Math.random () * i));
	}
	// Apparently, the last card is always an ace so do this at the end to fix it
	CardClass oCard = dealCard (51);
	addCard ((CardClass) oCard, (int) (Math.random () * 51));
    }


    // Display the card face up and then set it back to face down
    public void draw (Console c)
    {
	if (deck.size () == 0)
	{
	    CardClass oCard = new CardClass ();
	    oCard.setSize (DefSize);
	    oCard.setCenter (posX, posY);
	    oCard.drawEmpty (c);
	}
	else
	{
	    CardClass oCard = (CardClass) deck.elementAt (0);
	    oCard.setCenter (posX, posY);
	    oCard.setFaceUp (true);
	    oCard.draw (c);
	    oCard.setFaceUp (false);
	}
    }


    public void draw (Graphics g)
    {
	if ((deck.size () == 0))
	{
	    CardClass oCard = new CardClass ();
	    oCard.setSize (DefSize);
	    oCard.setCenter (posX, posY);
	    oCard.drawEmpty (g);
	}
	else
	{
	    CardClass oCard = (CardClass) deck.elementAt (0);
	    oCard.setCenter (posX, posY);
	    oCard.setFaceUp (true);
	    oCard.draw (g);
	    oCard.setFaceUp (false);
	}
    }


    // To display the card at any position, AS IS
    public void draw (Console c, int pos)
    {
	if ((deck.size () == 0) || (deck.size () < pos)) // Exceeds bounds
	{
	    CardClass oCard = new CardClass ();
	    oCard.setSize (DefSize);
	    oCard.setCenter (posX, posY);
	    oCard.drawEmpty (c);
	}
	else if (pos >= 0 && pos < deck.size ())
	{
	    CardClass oCard = (CardClass) deck.elementAt (pos);
	    oCard.setCenter (posX, posY);
	    oCard.draw (c);
	}
    }


    public void draw (Graphics g, int pos)
    {
	if ((deck.size () == 0) || (deck.size () < pos)) // Exceeds bounds
	{
	    CardClass oCard = new CardClass ();
	    oCard.setSize (DefSize);
	    oCard.setCenter (posX, posY);
	    oCard.drawEmpty (g);
	}
	else if (pos >= 0 && pos < deck.size ())
	{
	    CardClass oCard = (CardClass) deck.elementAt (pos);
	    oCard.setCenter (posX, posY);
	    oCard.draw (g);
	}
    }


    // Display the card at any position face up then set back to face down after
    public void drawUp (Console c, int pos)
    {
	if ((deck.size () == 0) || (deck.size () < pos)) // Exceeds bounds
	{
	    CardClass oCard = new CardClass ();
	    oCard.setSize (DefSize);
	    oCard.setCenter (posX, posY);
	    oCard.drawEmpty (c);
	}
	else if (pos >= 0 && pos < deck.size ())
	{
	    CardClass oCard = (CardClass) deck.elementAt (pos);
	    oCard.setCenter (posX, posY);
	    oCard.setFaceUp (true);
	    oCard.draw (c);
	    oCard.setFaceUp (false);
	}
    }


    public void drawUp (Graphics g, int pos)
    {
	if ((deck.size () == 0) || (deck.size () < pos)) // Exceeds bounds
	{
	    CardClass oCard = new CardClass ();
	    oCard.setSize (DefSize);
	    oCard.setCenter (posX, posY);
	    oCard.drawEmpty (g);
	}
	else if (pos >= 0 && pos < deck.size ())
	{
	    CardClass oCard = (CardClass) deck.elementAt (pos);
	    oCard.setCenter (posX, posY);
	    oCard.setFaceUp (true);
	    oCard.draw (g);
	    oCard.setFaceUp (false);
	}
    }


    ///////////////////////////////////

    // Erase
    public void erase (Console c)
    {
	if (deck.size () == 0)
	{
	}
	else
	{
	    CardClass oCard = (CardClass) deck.elementAt (0);
	    Color cOldColor = oCard.getColor ();

	    oCard.setCenter (posX, posY);

	    oCard.setColor (Color.white);
	    oCard.draw (c); // Draw as is
	    oCard.setColor (cOldColor);
	}
    }


    public void erase (Graphics g)
    {
	if (deck.size () == 0)
	{
	}
	else
	{
	    CardClass oCard = (CardClass) deck.elementAt (0);
	    Color cOldColor = oCard.getColor ();

	    oCard.setCenter (posX, posY);

	    oCard.setColor (Color.white);
	    oCard.draw (g); // Draw as is
	    oCard.setColor (cOldColor);
	}
    }


    // Erase a certain position
    public void erase (Console c, int pos)
    {
	if (deck.size () == 0)
	{
	}
	else
	{
	    CardClass oCard = (CardClass) deck.elementAt (pos);
	    Color cOldColor = oCard.getColor ();

	    oCard.setCenter (posX, posY);

	    oCard.setColor (Color.white);
	    oCard.draw (c); // Draw as is
	    oCard.setColor (cOldColor);
	}
    }


    public void erase (Graphics g, int pos)
    {
	if (deck.size () == 0)
	{
	}
	else
	{
	    CardClass oCard = (CardClass) deck.elementAt (pos);
	    Color cOldColor = oCard.getColor ();

	    oCard.setCenter (posX, posY);

	    oCard.setColor (Color.white);
	    oCard.draw (g); // Draw as is
	    oCard.setColor (cOldColor);
	}
    }
}
