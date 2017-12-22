package JoshShini.Bukkit;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class InGame {
	
	public static int totalPlayer = 0;
	public static int startPlayer = 0;
	public static MyPlayer player_1 = new MyPlayer(null,null);
	public static MyPlayer player_2 = new MyPlayer(null,null);
	public static Player winner;
	
	public static Vector<MyPlayer> allPlayer = new Vector<>();
	
	//public static Location spawn = new Location(InGame.player_1.getWorld(),683,71,287);

	public static Location spawn (Player player) {
		return new Location(player.getWorld(),683,71,287);
	}
	
	public static void resetAll() {
		player_1.thisPlayer.setGameMode(GameMode.SURVIVAL);
		player_2.thisPlayer.setGameMode(GameMode.SURVIVAL);
		player_1.thisPlayer.teleport(spawn(player_1.thisPlayer));
		player_2.thisPlayer.teleport(spawn(player_2.thisPlayer));
		totalPlayer = 0;
		startPlayer = 0;
		player_1 = new MyPlayer(null,null);
		player_2 = new MyPlayer(null,null);
		winner = null;
	}
	
	public static void reset() {
		InGame.player_1.thisPlayer.setHealth(20);
		InGame.player_2.thisPlayer.setHealth(20);
		InGame.player_1.thisPlayer.setFoodLevel(20);
		InGame.player_2.thisPlayer.setFoodLevel(20);
		//InGame.player_1.thisPlayer.removePotionEffect()
		for (PotionEffect effect : player_1.thisPlayer.getActivePotionEffects())
			player_1.thisPlayer.removePotionEffect(effect.getType());
		for (PotionEffect effect : player_2.thisPlayer.getActivePotionEffects())
			player_2.thisPlayer.removePotionEffect(effect.getType());
		
	}
	
	public static MyPlayer getPlayer(Player player) {
		
		for( MyPlayer p : allPlayer) {
			
			if(p.thisPlayer == player) {
				Bukkit.broadcastMessage(p.thisPlayer.getName());
				return p;
			}
		}
		
		return null;
	}
	
	
}
