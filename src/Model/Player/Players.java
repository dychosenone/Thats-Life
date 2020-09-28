package Model.Player;

import java.util.ArrayList;

public class Players {
	
	public static final int MAXPLAYERS = 3;
	
	private ArrayList <Player> players;

	/**
	 * When the Model.Player.Players object is initialized, the constructor creates a collection of players.
	 */
	public Players (){
		players = new ArrayList<Player>();
	}
	
	/**
	 * Function adds players then sets the player's name and adds it to the ArrayList.
	 * @param name - name of the player
	 */
	public void AddPlayer (String name) {
		players.add(new Player (name));
	}
	
	/**
	 * The function returns if it has exceed the maximum number of players.
	 * @return TRUE - if there are 3 players, FALSE - there are 2 or less players
	 */
	
	public boolean isMaxPlayers () {
		return players.size() < MAXPLAYERS;
	}

	
	/**
	 * Function gets object of Player
	 * @param p - object of player
	 * @return Player - reference to player
	 */
	
	public Player getPlayer (Player p) {
		return players.get(players.indexOf(p));
	}
	
	/**
	 * Function replaces current player with another player
	 * @param currentPlayer - current player having his turn
	 * @return nextPlayer - next current player
	 */
	
	public Player getNextPlayer(Player currentPlayer) {
		Player nextPlayer;
		
		nextPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
		
		return nextPlayer;
	}
	
	/**
	 * Function clones ArrayList of Player
	 * @return clone - reference to the ArrayList of players
	 */
	
	public ArrayList<Player> getPlayers () {
		ArrayList <Player> clone = new ArrayList <Player> (players);
		
		return clone;
	}
	
	/**
	 * Function returns size of ArrayList of players
	 * @return size of ArrayList of players
	 */
	
	public int getSize () {
		return players.size();
	}
	
	
	/**
	 * Function converts attributes toString for checking
	 * @return text
	 */
	
	@Override
	public String toString() {
		int i;
		String text = "";
		
		for (i = 0; i < players.size(); i++) {
			text += players.get(i).toString();		
			}
		
		return text;
	}
}
