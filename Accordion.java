// By Howard Zeng 2012.01.30
// If you want rules, run the game and it should be written at the bottom
import Cards.*;
import Decks.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class Accordion extends Applet
    implements MouseListener, MouseMotionListener
{


    // Applet Varaibles
    int mouseX, mouseY;  // the most recently recorded mouse coordinates
    boolean isMouseDraggingCard = false; // Flag for whenever user drags mouse

    // The object we will use to write with instead of the standard screen graphics
    Graphics bufferGraphics;
    // The image that will contain everything that has been drawn on bufferGraphics.
    Image offscreen;
    // For the background
    BufferedImage background = null;
    // To get the width and height of the applet.
    Dimension dim;

    // For the layout
    FlowLayout lm = new FlowLayout (FlowLayout.CENTER, 200, 0);
    Button buttonHint = new Button ("Hint");
    Button buttonNewGame = new Button ("New Game");
    TextField textBox = new TextField (10);



    // Game variables
    // Decks
    DeckClass FaceDownDeck; // Top left deck where all cards come from
    DeckClass DiscardDeck; // The bottom right discard deck
    Vector SpreadDecks; // The decks that contain face up cards given by the "FaceDownDeck"
    CardClass UserCard = null; // The card that the user clicks on
    static String univeralSize = "M";
    int CardsOut; // The number of face up cards excluding discard deck
    int CardHeight, CardWidth; // Used to get the card height
    int cardID;  // Used to find out what the card's last position was before the user's selection
    boolean gameOver;
    boolean gameWin;
    Color tColor1, tColor2; // For use with hints
    CardClass tmpCard1 = null; // For use with hints
    CardClass tmpCard2 = null; // For use with hints


    public void init ()
    {
	// Set size of applet
	setSize (1000, 650);
	// We'll ask the width and height of the applet by this
	dim = getSize ();

	// Set the offscreen canvas size
	offscreen = createImage (dim.width, dim.height);
	// by doing this everything that is drawn by bufferGraphics
	// will be written on the offscreen image.
	bufferGraphics = offscreen.getGraphics ();


	// Layout
	setLayout (lm);
	add (buttonNewGame);
	add (textBox);
	add (buttonHint);



	// To keep track of mouse
	addMouseListener (this);
	addMouseMotionListener (this);

	restart (); // To restart the game

	// To get the background image
	try
	{
	    background = ImageIO.read (new File ("backgroundc.jpg"));
	}
	catch (IOException e)
	{
	}
    }


    public boolean action (Event e, Object o)
    {
	if (e.target instanceof Button)
	{
	    if (e.target == buttonHint)
	    {
		if (tColor1 == null) // To prevent showing the same hint over and over
		{
		    boolean dummy = moveChecker (true);
		}
	    }
	    if (e.target == buttonNewGame)
	    {
		restart ();
		repaint ();
	    }
	}
	return true;
    }




    public void restart ()
    {
	// Game variables
	FaceDownDeck = new DeckClass ("Std", univeralSize); // The deck on the top left where everything comes from
	FaceDownDeck.shuffle ();
	DiscardDeck = new DeckClass ("", univeralSize); // The deck on the bottom right for the discarded cards
	SpreadDecks = new Vector (0, 1); // A spread out deck of 52 cards regardless of whether they exist or not

	// Instantiate SpreadDecks' decks
	for (int i = 0 ; i < 52 ; i++)
	{
	    DeckClass oDeck = new DeckClass ("", univeralSize);
	    SpreadDecks.addElement (oDeck);
	}
	CardsOut = 0; // There are no faceup cards

	// Make the cards
	CardHeight = (FaceDownDeck.getCard (0)).getHeight ();
	CardWidth = (FaceDownDeck.getCard (0)).getWidth ();
	FaceDownDeck.setCenter (50, 80);
	DiscardDeck.setCenter (((CardWidth + 34) * 10) + 50, ((CardHeight + 20) * 5) + 80);

	// For the text field
	textBox.setText ("Good luck!");

	// Initiate hints
	tColor1 = null;
	
	// To reset win/lose flags
	gameOver = false;
	gameWin = false;
    }


    // To check for any possible moves and show hint if nessesary
    public boolean moveChecker (boolean showHint)
    {
	// To check two cards
	for (int i = 0 ; i < 52 && (i + 1 <= CardsOut) ; i++) // Check all possible moves and stop if you go out of bounds
	{
	    tmpCard1 = (CardClass) (((DeckClass) SpreadDecks.elementAt (i)).getCard (0));
	    tmpCard2 = (CardClass) (((DeckClass) SpreadDecks.elementAt (i + 1)).getCard (0));

	    // In case the compared card doesn't exist
	    if (tmpCard2 != null)
	    {
		if ((tmpCard1.getValue () == tmpCard2.getValue ()) || (tmpCard1.getSuit () == tmpCard2.getSuit ())) // If a move is possible
		{
		    // If you wanted a hint signal it
		    if (showHint == true)
		    {
			tColor1 = tmpCard1.getColor ();
			tColor2 = tmpCard2.getColor ();
			tmpCard1.setColor (Color.blue);
			tmpCard2.setColor (Color.blue);
			repaint ();
		    }
		    return true;
		}
	    }

	    if (i + 3 <= CardsOut) // If a third card from the index exists
	    {
		tmpCard1 = (CardClass) (((DeckClass) SpreadDecks.elementAt (i)).getCard (0));
		tmpCard2 = (CardClass) (((DeckClass) SpreadDecks.elementAt (i + 3)).getCard (0));

		// In case the compared card doesn't exist
		if (tmpCard2 != null)
		{
		    if ((tmpCard1.getValue () == tmpCard2.getValue ()) || (tmpCard1.getSuit () == tmpCard2.getSuit ())) // If a move is possible
		    {
			// If you wanted a hint signal it
			if (showHint == true)
			{
			    tColor1 = tmpCard1.getColor ();
			    tColor2 = tmpCard2.getColor ();
			    tmpCard1.setColor (Color.blue);
			    tmpCard2.setColor (Color.blue);
			    repaint ();
			}
			return true;
		    }
		}
	    }
	}
	// No possible moves left
	return false;
    }


    public int pointInside (int px, int py)
    {
	CardClass oCard = null; // Temporary variable
	for (int i = 0 ; i < 53 && i <= CardsOut ; i++) // Check all of the cards out if they are clicked on
	{
	    if (i == 0) // If the FaceDownDeck is not empty
	    {
		oCard = FaceDownDeck.getCard (0); // Get the card info for the FaceDownDeck
	    }
	    else
	    {
		oCard = (CardClass) (((DeckClass) SpreadDecks.elementAt (i - 1)).getCard (0)); // Get the card info for the SpreadDecks
	    }
	    // If the card exists and the user clicks on the card, return which card the user clicked on
	    if (oCard != null)
	    { // If the mouse clicks on the card
		if (((oCard.getCenterX () - (oCard.getWidth () / 2)) < px) &&
			((oCard.getCenterX () + (oCard.getWidth () / 2)) > px) &&
			((oCard.getCenterY () - (oCard.getHeight () / 2)) < py) &&
			((oCard.getCenterY () + (oCard.getHeight () / 2)) > py)
			)
		{
		    return i - 1;
		}
	    }
	}
	return -2;
    }


    // To draw the decks
    public void drawDecks (Graphics g)
    {

	int locX, locY; // To specify the location of where the deck is drawn

	// To draw the decks all over the applet
	for (int i = 0 ; i < 5 ; i++) // 5 Rows
	{
	    for (int j = 0 ; (j < 11) && (i * 11) + j < 54 ; j++) // Exit when there are 11 cards on each row or 54 cards have been drawn
	    {
		if (i % 2 == 0) // To make the cards appear in a 'S' pattern by alternating the direction
		    // The extra numbers is used to space out the cards
		    {
			locX = (j * (CardWidth + 34)) + 50; // From left to right
		    }
		else
		{
		    locX = dim.width - (((j) * (CardWidth + 34)) + 50);  // From right to left
		}

		locY = (i * (CardHeight + 20)) + 80; // To calculate the row

		if (i == 0 && j == 0) // If it is the first card, draw the 'FaceDownDeck'
		{
		    FaceDownDeck.draw (g, 0);
		}
		else if ((i * 11 + j) == 53) // If it is the last card, draw the discard deck on the bottom right
		{
		    DiscardDeck.draw (g);
		}
		else // Else draw the spread out decks
		{
		    DeckClass oDeck = new DeckClass ();
		    oDeck = (DeckClass) SpreadDecks.elementAt ((i * 11) + j - 1); // Minus one to compensate for the 'FaceDownDeck'
		    oDeck.setCenter (locX, locY);
		    oDeck.draw (g, 0);
		}

	    }
	}
    }




    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
	mouseX = e.getX (); // X co-ordinate of mouse
	mouseY = e.getY (); // Y co-ordinate of mouse
	cardID = pointInside (mouseX, mouseY); // To find which card the user clicked on
	if (cardID == -1) // The FaceDownDeck is clicked on
	{
	    if (FaceDownDeck.getCard (0) != null)  // If the FaceDownDeck is not empty
	    {
		// Deal a card from the FaceDownDeck and transfer to the SpreadOutDeck
		((CardClass) FaceDownDeck.getCard (0)).setFaceUp (true);
		((DeckClass) SpreadDecks.elementAt (CardsOut)).addCard (FaceDownDeck.dealCard (0), 0);

		CardsOut++; // Increase the counter for the number of cards out
		repaint ();
	    }
	}
	else if ((cardID >= 0) && (cardID < 52)) // If you clicked on the any of the cards from the SpreadDecks
	{ // Allow for dragging of the card
	    UserCard = (CardClass) (((DeckClass) SpreadDecks.elementAt (cardID)).dealCard (0));
	    UserCard.setFaceUp (true);
	    isMouseDraggingCard = true; // Set dragging flag to true
	}
	e.consume (); // To prevent this event from running repeatedly unless another event runs instead of it
    }


    public void mouseReleased (MouseEvent e)
    {
	isMouseDraggingCard = false; // Set mouse dragging to false

	if (UserCard != null) // If the UserCard exists
	{
	    ((DeckClass) SpreadDecks.elementAt (cardID)).addCard (UserCard, 0); // Put the dragging card back into original deck

	    int cardPos = pointInside (UserCard.getCenterX (), UserCard.getCenterY ()); // Find out which card was dragged onto

	    if ((cardPos != -1) &&  // In case you dragged it on top of the FaceDownDeck
		    ((cardID - cardPos == 3) || (cardID - cardPos == 1))
		    ) // If the card is dragged onto a card 1 or 3 spaces before it given the it is not the FaceDownDeck
	    {
		CardClass tCard = (CardClass) (((DeckClass) SpreadDecks.elementAt (cardPos)).getCard (0)); // Get info for the card dragged onto

		// If the cards match in value of suit, send the card under to discard and replace with the card being dragged
		if ((tCard.getValue () == UserCard.getValue ()) || (tCard.getSuit () == UserCard.getSuit ()))
		{
		    // If you had a hint, revert cards to normal
		    if (tColor1 != null)
		    {
			tmpCard1.setColor (tColor1);
			tmpCard2.setColor (tColor2);
		    }
		    tColor1 = null;


		    // Place it at the top of the discard deck
		    DiscardDeck.addCard ((CardClass) (((DeckClass) SpreadDecks.elementAt (cardPos)).dealCard (0)), 0);

		    CardsOut--; // Decrease the counter for face up cards

		    // Then replace the card with the card that was being dragged
		    ((DeckClass) SpreadDecks.elementAt (cardPos)).addCard ((CardClass) (((DeckClass) SpreadDecks.elementAt (cardID)).dealCard (0)), 0);
		    SpreadDecks.removeElementAt (cardID);

		    // To reorder the 'decks'
		    // Great way of abusing the nature of vectors in java
		    DeckClass oDeck = new DeckClass ("", univeralSize);
		    SpreadDecks.insertElementAt (oDeck, SpreadDecks.size ());
		}

	    }
	    UserCard = null; // The dragging card does not exist anymore

	    // To check for winning and losing
	    if ((FaceDownDeck.getCard (0) == null) && (CardsOut <= 5)) // If FaceDownDeck is empty and cards out is 5 or less, you win
	    {
		gameWin = true;
		textBox.setText ("You win!");
	    }

	    if ((FaceDownDeck.getCard (0) == null) && (gameWin != true)) // If FaceDownDeck is empty and not moves are left, you lose
	    {
		if (moveChecker (false) == false) // If there are no matches, game over
		{
		    gameOver = true;
		    textBox.setText ("You lose!");
		}
	    }

	    repaint ();
	}

	e.consume (); // To prevent this event from running repeatedly unless another event runs instead of it
    }


    public void mouseMoved (MouseEvent e)
    {
	repaint ();
    }


    public void mouseDragged (MouseEvent e)
    {
	if (isMouseDraggingCard == true)
	{
	    // get the latest mouse position
	    int new_mouseX = e.getX ();
	    int new_mouseY = e.getY ();

	    // displace the box by the distance the mouse moved since the last event
	    UserCard.setCenter (UserCard.getCenterX () + new_mouseX - mouseX, UserCard.getCenterY () + new_mouseY - mouseY);
	    // update our data
	    mouseX = new_mouseX;
	    mouseY = new_mouseY;

	    repaint ();
	    e.consume (); // To prevent this event from running repeatedly unless another event runs instead of it
	}
    }


    public void paint (Graphics g)
    {
	// Wipe off everything that has been drawn before
	// Otherwise previous drawings would also be displayed.
	bufferGraphics.setColor (Color.white);
	bufferGraphics.drawImage (background, 0, 0, this);

	// to the offscreen image
	drawDecks (bufferGraphics);
	if (UserCard != null) // If a dragging card exists, draw
	{
	    UserCard.draw (bufferGraphics);
	}

	// My message to the player
	bufferGraphics.setColor (Color.white);
	bufferGraphics.drawString ("Accordion Rules: Click on the deck on the top left to deal a card", 24, 580);
	bufferGraphics.drawString ("Drag and drop a card of the same suit or value to a card 1 or 3 positions before it to send the card under to the discard pile", 24, 595);
	bufferGraphics.drawString ("You win when the top left deck is empty and there are 5 or less face up cards left (excluding discard)", 24, 610);
	bufferGraphics.drawString ("The game will tell you when you win or lose through the text box above, use hints if nessesary (It's probably going to make you lose)", 24, 625);
	bufferGraphics.drawString ("Programmed by Howard Zeng (2012.01.30)", 24, 640);
	g.drawImage (offscreen, 0, 0, this);
    }


    // Always required for good double-buffering.
    // This will cause the applet not to first wipe off
    // previous drawings but to immediately repaint.
    // the wiping off also causes flickering.
    // Update is called automatically when repaint() is called.
    public void update (Graphics g)
    {
	paint (g);
    }
}


