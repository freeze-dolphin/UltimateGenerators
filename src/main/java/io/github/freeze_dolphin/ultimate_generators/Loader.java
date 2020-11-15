package io.github.freeze_dolphin.ultimate_generators;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.github.freeze_dolphin.ultimate_generators.objects.basics.UGConfig;

public class Loader extends JavaPlugin {

	private static Plugin plug;
	private static UGConfig config;
	
	private static Logger logger;
	
	@Override
	public void onEnable() {

		plug = this;
		
		logger = getLogger();
		
		config = new UGConfig(this);
		if (!config.getFile().exists()) saveDefaultConfig();
		
		// load
		try {
			new UGItems();
			new GlobalVariables();
			new Implementor();
		} catch (Exception ex) {
			ex.printStackTrace();
			severe("Cannot initialize plugin 'UltimateGenerators', self-disabling...");
			this.setEnabled(false);
		}

		// initialize configurations
		/* (Deprecated)
		try {
			Class<?> ugItemClass = Class.forName("io.github.freeze_dolphin.ultimate_generators.lists.UGItems");
			for (Field f : ugItemClass.getFields()) {
				if (f.isAnnotationPresent(MachineItemStack.class)) {
					if (SlimefunItem.getByID(f.getName()) == null) {
						severe("An unexpected error occurred: Item '" + f.getName() + "' is not loaded!");
						continue;
					} else {
						getUGConfig().setMachineValue(f.getName(), "speed", 1);
					}
				} else {
					continue;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			severe("In-plugin file 'UGItems.class' is lost, self-disabling...");
			this.setEnabled(false);
		}
		 */

	}

	public static Plugin getImplement() { return plug; }

	public static UGConfig getUGConfig() { return config; }

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void warn(String msg) {
		logger.warning(msg);
	}

	public static void severe(String msg) {
		logger.severe(msg);
	}
	
	public static void debug(String msg) {
		if (config.getBoolean("debug")) logger.warning(ChatColor.translateAlternateColorCodes('&', "&f&l[Debug] &2Contents: &a" + msg));
	}
	
	public static void debug(int point, String msg) {
		if (config.getBoolean("debug")) logger.warning(ChatColor.translateAlternateColorCodes('&', "&f&l[Debug] &4Point: &c" + point + " &f| &2Contents: &a" + msg));
	}

}
