//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
//i am a monkey
public class AlienHorde
{
	private List<Alien> aliens;

	public AlienHorde(int size)
	{
		aliens = new ArrayList<Alien>(size);
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for (Alien a : aliens) {
			a.draw(window);
		}
	}

	public void moveEmAll()
	{
		for (Alien a : aliens) {
			a.move("DOWN");
		}
	}

	public void removeDeadOnes(List<Ammo> shots)
	{//shots.get(i).getX() == aliens.get(j).getX() && shots.get(i).getY() == aliens.get(j).getY()
		for (int i=0; i<shots.size(); i++) {
			for (int j=0; j<aliens.size(); j++) {
				Ammo am = shots.get(i);
				Alien al = aliens.get(j);
				int xDiff = al.getX() - am.getX();
				int yDiff = al.getY() - am.getY();
				if (xDiff >= -1*al.getWidth() && xDiff <= 0 && yDiff >= -1*al.getHeight() && yDiff <= 0) {
					//System.out.println(shots.get(i).getY());
					//System.out.println(aliens.get(j).getY());
					shots.remove(i);
					aliens.remove(j);
					break;
				}
			}
		}
	}

	public String toString()
	{
		return aliens.toString();
	}
}
