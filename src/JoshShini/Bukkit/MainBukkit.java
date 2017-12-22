package JoshShini.Bukkit;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import JoshShini.Command.MyCommand;
import JoshShini.Event.BlockBreak;
import JoshShini.Event.PlayerEvent;

public class MainBukkit extends JavaPlugin{
	
	public void onEnable() {
		PluginDescriptionFile pdf = getDescription();
		
		registerCommands();
		registerEvent();
		
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdf.getName() + " has been enable (V." + pdf.getVersion()+")");
		
	}
	
	public void onDisable() {
		PluginDescriptionFile pdf = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdf.getName() + " has been enable (V." + pdf.getVersion()+")");
		
	}
	
	public void registerCommands() {
		getCommand("ready").setExecutor(new MyCommand());
		getCommand("start").setExecutor(new MyCommand());
		getCommand("lobby").setExecutor(new MyCommand());
		getCommand("spec").setExecutor(new MyCommand());
	}
	
	public void registerEvent() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PlayerEvent(), this);
	}
}
