//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private BufferedImage image;

	public Ship()
	{
		this(10,10,10,10,10);
	}

	public Ship(int x, int y) {
	   super(x, y);
	   speed = 5;
	   try {
		   image = ImageIO.read(new File("ship.jpg"));
	   } catch (Exception e) {
		   System.out.println("File not in path");
	   }
	}

	public Ship(int x, int y, int s)
	{
	   super(x, y);
	   speed = s;
		try {
			image = ImageIO.read(new File("ship.jpg"));
		} catch (Exception e) {
			System.out.println("File not in path");
		}
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			URL url = getClass().getResource("ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println();
			System.out.println("File not in path");
		}
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void move(String direction)
	{
		if (direction.equals("LEFT"))
			setX(getX()-5);
		else if (direction.equals("RIGHT")) {
			setX(getX()+5);
		} else if (direction.equals("UP")) {
			setY(getY()-5);
		} else if (direction.equals("DOWN")) {
			setY(getY()+5);
		}
	}

	public void draw( Graphics window )
	{
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
