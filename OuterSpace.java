//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	
   	private AlienHorde horde;
	private Bullets shots;

	private boolean shotShot;
	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other instance variables
		//Ship, Alien
		ship = new Ship(500, 200, 100, 80, 7);
		//alienOne = new Alien(250, 100, 2);
		//alienTwo = new Alien(400, 100, 2);
		horde = new AlienHorde(10);
		for (int i=0; i<10; i++) {
			horde.add(new Alien(ship.getWidth() + i* ship.getWidth(), 50,ship.getWidth()*3/4,ship.getWidth()*3/4, 1));
		}
		shots = new Bullets();
		shotShot = false;

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,StarFighter.WIDTH,StarFighter.HEIGHT);

		//add code to move Ship, Alien, etc.
		if (keys[0] == true) {
			ship.move("LEFT");
		} else if (keys[1] == true) {
			ship.move("RIGHT");
		} else if (keys[2] == true) {
			ship.move("UP");
		} else if (keys[3] == true) {
			ship.move("DOWN");
		} if (keys[4] == true && shotShot) {
			shots.add(new Ammo(ship.getX()+45, ship.getY()+10, 10));
			shotShot=false;
		}

		shots.moveEmAll();
		horde.moveEmAll();

		ship.draw ( graphToBack ) ;
		horde.drawEmAll( graphToBack );
		//alienOne.draw ( graphToBack ) ;
		//alienTwo.draw ( graphToBack ) ;
		shots.drawEmAll( graphToBack );

		//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
		horde.removeDeadOnes(shots.getList());

		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
			shotShot = true;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(10);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

