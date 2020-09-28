import Controller.MainMenuController;
import View.MainMenu;
/**
 * @author Jacob Miguel Dy
 * @author Azriel Ortega
 * That's Life MP Phase 1
 *
 */
public class Main {


	public static void main (String[] args) {
		
		
		MainMenu ui = new MainMenu ();
		MainMenuController main = new MainMenuController(ui);
	}
}