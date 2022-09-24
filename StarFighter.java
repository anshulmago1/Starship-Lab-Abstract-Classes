import javax.swing.JFrame;
import java.awt.*;

public class StarFighter extends JFrame
{
	public static int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public StarFighter()
	{
		super("STARFIGHTER");

		setSize(WIDTH, HEIGHT);

		OuterSpace theGame = new OuterSpace();
		((Component)theGame).setFocusable(true);

                getContentPane().add( theGame );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} 

	public static void main( String args[] )
	{
		StarFighter run = new StarFighter();
	}
}