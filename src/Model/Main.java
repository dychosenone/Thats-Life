package Model;
import Controller.Controller;
import View.GUI;
/**
 * @author Jacob Miguel Dy
 * @author Azriel Ortega
 * That's Life MP Phase 1
 *
 */
public class Main {


	public static void main (String[] args) {
		
		GameOfLife game = new GameOfLife ();

		GUI gui = new GUI();
		
		Controller cont = new Controller (gui, game);
		
		cont.startGame();		
	}
}