package JoshShini.Event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import JoshShini.Bukkit.InGame;
import JoshShini.Bukkit.MyPlayer;

public class PlayerEvent implements Listener{
	@EventHandler
	public void onPlayerDamaged(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			if ( player == InGame.player_1.thisPlayer || player == InGame.player_2.thisPlayer)
			{
				if ( InGame.player_1.playerState == "start" && InGame.player_2.playerState == "start") 
				{
					if(player.getHealth() < event.getDamage())
					{
						player.setGameMode(GameMode.SPECTATOR);
						Bukkit.broadcastMessage(player.getName() + " Has Been Defeated");
						event.setCancelled(true);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						InGame.player_1.playerState = "lobby";
						InGame.player_2.playerState = "lobby";
						InGame.player_1.thisPlayer.getInventory().clear();
						InGame.player_2.thisPlayer.getInventory().clear();
						InGame.reset();
						InGame.resetAll();
					}
				}
				else
				{
					event.setCancelled(true);
				}
			}
			else
			{
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		MyPlayer asd = new MyPlayer(event.getPlayer(),"lobby");
		asd.thisPlayer.teleport(InGame.spawn(asd.thisPlayer));
		InGame.allPlayer.add(new MyPlayer(event.getPlayer(),"lobby"));
		asd.thisPlayer.sendMessage(ChatColor.AQUA +  "Hello, "+asd.thisPlayer.getName()+"! you are player no."+InGame.allPlayer.size());
		
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		//Bukkit.broadcastMessage(event.getPlayer().getName()+"has disconnected!");
		
		for(int i = 0 ; i < InGame.allPlayer.size() ; i++) {
			if(event.getPlayer() == InGame.allPlayer.get(i).thisPlayer) {
				InGame.allPlayer.remove(i);
				return;
			}
		}
	}
	
}
