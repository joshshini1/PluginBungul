package JoshShini.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import JoshShini.Bukkit.InGame;


public class MyCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player)
		{
			if(label.equalsIgnoreCase("ready"))
			{
				Bukkit.broadcastMessage(Integer.toString(InGame.totalPlayer) );
				if (InGame.totalPlayer == 0) {
					InGame.player_1 = InGame.getPlayer((Player) sender);
					InGame.player_1.thisPlayer.sendMessage(ChatColor.AQUA +  "Waiting For Other Player");
					
					InGame.player_1.playerState = "ready";
					InGame.player_1.thisPlayer.teleport(new Location(InGame.player_1.thisPlayer.getWorld(),673,72,310));
					InGame.player_1.thisPlayer.getInventory().clear();
					InGame.totalPlayer++;
					InGame.player_1.thisPlayer.getInventory().addItem(new ItemStack(Material.EMERALD,40));
					
					return true;
				}
				else if (InGame.totalPlayer == 1) {
					if ((Player) sender == InGame.player_1.thisPlayer) {
						InGame.player_1.thisPlayer.sendMessage(ChatColor.AQUA +  "You are already in waiting list");
						return false;
					}
					InGame.player_2 = InGame.getPlayer((Player) sender);
					InGame.player_2.playerState = "ready";
					InGame.player_2.thisPlayer.sendMessage(ChatColor.AQUA +  "Preparing. . .");
					InGame.player_1.thisPlayer.sendMessage(ChatColor.AQUA +  "Preparing. . .");
					InGame.player_2.thisPlayer.teleport(new Location(InGame.player_2.thisPlayer.getWorld(),681,72,310));
					InGame.player_2.thisPlayer.getInventory().clear();
					InGame.totalPlayer++;
					InGame.player_2.thisPlayer.getInventory().addItem(new ItemStack(Material.EMERALD,40));
					toShop();
					return true;
				}
				
			}
			else if(label.equalsIgnoreCase("start")) {
				
				if(InGame.totalPlayer == 2) {
					if((Player) sender == InGame.player_1.thisPlayer && InGame.player_1.playerState != "start") {
						InGame.player_1.playerState = "start";
						//InGame.player_1.teleport(new Location(InGame.player_1.getWorld(),683,70,293));
						InGame.startPlayer++;		
					}
					else if((Player) sender == InGame.player_2.thisPlayer && InGame.player_2.playerState != "start") {
						InGame.player_2.playerState = "start";
						//InGame.player_2.teleport(new Location(InGame.player_2.getWorld(),684,70,278));
						InGame.startPlayer++;
					}
					if(InGame.startPlayer == 2) {
						InGame.reset();
						InGame.player_1.thisPlayer.teleport(new Location(InGame.player_1.thisPlayer.getWorld(),757,63,239));
						InGame.player_2.thisPlayer.teleport(new Location(InGame.player_2.thisPlayer.getWorld(),792,63,239));						
					}
					
					
				}
			}
			else if(label.equalsIgnoreCase("lobby")) {
				
				
				
			}
			
			else if(label.equalsIgnoreCase("spec")) {
				
				
			}
		}
		
		return false;
	}
	
	public void toShop() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InGame.player_1.playerState = "shop";
		InGame.player_2.playerState = "shop";
		InGame.player_1.thisPlayer.teleport(new Location(InGame.player_1.thisPlayer.getWorld(),691,69,268));
		InGame.player_2.thisPlayer.teleport(new Location(InGame.player_2.thisPlayer.getWorld(),691,69,268));
		InGame.reset();
		
	}
	
	

}
